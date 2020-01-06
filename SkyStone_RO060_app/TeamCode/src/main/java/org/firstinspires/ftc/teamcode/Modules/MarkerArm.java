package org.firstinspires.ftc.teamcode.Modules;

import android.inputmethodservice.ExtractEditText;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
class RunnableExtender implements Runnable {

    private Thread t;
    private String threadName;
    private Motor Extender;

    RunnableExtender(String _threadName, Motor _Extender) {
        threadName = _threadName;
        Extender = _Extender;
    }

    public void start() {
        if(t==null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    @Override
    public void run() {
        Extender.runToPosition();
        Extender.setTargetPosition(0);
        Extender.SetPower(1f);
        while(Extender.isBusy()) {

        }
        Extender.stopAndResetEncoder();
        Extender.runUsingEncoder();
    }
}
*/

public class MarkerArm {

    public Motor Rotation, Extender;
    private Servo_Pos MarkerGrab, MarkerPivot;
    private MagneticSwitch magneticSwitch;

    public enum ServoPositions {
        OPEN,
        CLOSED
    }
    private ServoPositions markerGrabPos;
    private float[] ServoPosValues = {.3f, 0.18f};
    private float markerPivotPos = 0f;

    private int ExtenderTurboCoef = 2;

    public void Init(String _RotationName, String _ExtenderName, String _MarkerGrabName, String _MarkerPivotName, String _MagneticSwitchName, HardwareMap hwm) {
        Rotation = new Motor();
        Extender = new Motor();
        MarkerGrab = new Servo_Pos();
        MarkerPivot = new Servo_Pos();
        magneticSwitch = new MagneticSwitch();

        Rotation.Init(_RotationName, hwm);
        if(Rotation.IsOn()) {
            Rotation.runUsingEncoder();
        }

        Extender.Init(_ExtenderName, hwm);
        if(Extender.IsOn()) {
            Extender.Brake();
            Extender.runUsingEncoder();
        }
        MarkerGrab.Init(_MarkerGrabName, hwm);
        if(MarkerGrab.IsOn())  {
            MarkerGrab.SetPosition(ServoPosValues[0]);
            markerGrabPos = ServoPositions.OPEN;
        }

        MarkerPivot.Init(_MarkerPivotName, hwm);
        if(MarkerPivot.IsOn()) {
            MarkerPivot.SetPosition(markerPivotPos);
        }

        magneticSwitch.Init(_MagneticSwitchName, hwm);
    }

    public boolean IsOn() {
        return Rotation.IsOn() && Extender.IsOn() && MarkerGrab.IsOn();
    }

    public void SetMarkerGrabPosition(ServoPositions _markerGrabPos) {
        markerGrabPos = _markerGrabPos;
        if(markerGrabPos == ServoPositions.CLOSED) MarkerGrab.SetPosition(ServoPosValues[1]);
        else if (markerGrabPos == ServoPositions.OPEN) MarkerGrab.SetPosition(ServoPosValues[0]);
    }

    public String UpdateMarkerArm(Gamepad gamepad2) {
        String s = "";

        if(Rotation.IsOn()) {
            float rotPow = gamepad2.left_stick_y;
            if( rotPow > 0) rotPow /= 1.2;//was 2   

            if(!Rotation.IsMoving()) Rotation.SetPower(rotPow);

            s += "MarkerArmRotation power: " + rotPow;

            if(gamepad2.dpad_down && !Rotation.IsMoving()) {
                Rotation.setTargetPosition(0);
                Rotation.runToPosition();
                Rotation.SetPower(.75f);
                Rotation.SetMoving(true);
            }
        }
        else s += "MarkerArmRotation not defined/connected.";
        s+= "\nMarkerRotationPosition: " + Rotation.getCurrentPosition();

        s+="\n";

        if(Extender.IsOn()) {
            float extPow = gamepad2.right_stick_y / ExtenderTurboCoef;
            Extender.SetPower(extPow);

            s+= "MarkerArmExtender power: " + extPow;
        }
        else s+= "MarkerArmExtender not defined/connected.";

        if(gamepad2.right_trigger>0) {
            ExtenderTurboCoef = 1;
        }
        else ExtenderTurboCoef = 2;
        s+="\nMarkerArmExtender Turbo: " + ExtenderTurboCoef;

        s+="\n";

        if(MarkerGrab.IsOn()) {
            if(gamepad2.left_bumper) {
                markerGrabPos = ServoPositions.OPEN;
            }
            else if (gamepad2.right_bumper) {
                markerGrabPos = ServoPositions.CLOSED;
            }

            SetMarkerGrabPosition(markerGrabPos);

            s+="MarkerGrab position: [" + markerGrabPos.toString() + "]";
        }
        else s+="MarkerGrab not defined/connected.";

        s+="\n";

        if(MarkerPivot.IsOn()) {
            double step = 0.01;
            if(gamepad2.y && markerPivotPos+step<.6f) {
                markerPivotPos+=step;
            }
            else if (gamepad2.a && markerPivotPos-step>0f) {
                markerPivotPos-=step;
            }

            s+="MarkerPivot position: [" + markerPivotPos + "]";
            try {
                MarkerPivot.SetPosition(markerPivotPos);
            }
            catch (Exception ex) {
                MarkerPivot.SwitchState(false);
            }
        }
        else s+="MarkerPivot not defined/connected.";

        if(magneticSwitch.IsOn()) {
            if(!magneticSwitch.getState() && Extender.getCurrentPosition() !=0) {
                Rotation.stopAndResetEncoder();
                Rotation.runUsingEncoder();
                Rotation.SetMoving(false);
                s += "\nHOOPAAAA";
            }
        }

        return s;
    }
}
