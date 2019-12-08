package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MarkerArm {

    private Motor Rotation, Extender;
    private Servo_Pos MarkerGrab, MarkerPivot;

    enum ServoPositions {
        OPEN,
        CLOSED
    }
    private ServoPositions markerGrabPos;
    private float[] ServoPosValues = {.5f, 0};
    private float markerPivotPos = 0f;

    public void Init(String _RotationName, String _ExtenderName, String _MarkerGrabName, String _MarkerPivotName, HardwareMap hwm) {
        Rotation = new Motor();
        Extender = new Motor();
        MarkerGrab = new Servo_Pos();
        MarkerPivot = new Servo_Pos();

        Rotation.Init(_RotationName, hwm);
        Extender.Init(_ExtenderName, hwm);
        if(Extender.IsOn()) {
            Extender.Brake();
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
            if( rotPow > 0) rotPow /= 2;

            Rotation.SetPower(rotPow);

            s += "MarkerArmRotation power: " + rotPow;
        }
        else s += "MarkerArmRotation not defined/connected.";

        s+="\n";

        if(Extender.IsOn()) {
            float extPow = gamepad2.right_stick_y / 2;
            Extender.SetPower(extPow);

            s+= "MarkerArmExtender power: " + extPow;
        }
        else s+= "MarkerArmExtender not defined/connected.";

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
            if(gamepad2.y && markerPivotPos+.005<.6f) {
                markerPivotPos+=.005;
            }
            else if (gamepad2.a && markerPivotPos-.005>0f) {
                markerPivotPos-=.005;
            }

            s+="MarkerPivot position: [" + markerPivotPos + "]";
            try {
                MarkerPivot.SetPosition(markerPivotPos);
            }
            catch (Exception ex) {
                MarkerPivot.SwitchState(false);
            }
        }
        else s+="MarkerPIvot not defined/connected.";


        return s;
    }
}
