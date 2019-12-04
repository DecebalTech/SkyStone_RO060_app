package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MarkerArm {

    private Motor Rotation, Extender;
    private Servo_Pos MarkerGrab;

    enum ServoPos {
        OPEN,
        CLOSED
    }
    private ServoPos servoPos;
    private float[] ServoPosValues = {.5f, 0};

    public void Init(String _RotationName, String _ExtenderName, String _MarkerGrabName, HardwareMap hwm) {
        Rotation = new Motor();
        Extender = new Motor();
        MarkerGrab = new Servo_Pos();

        Rotation.Init(_RotationName, hwm);
        Extender.Init(_ExtenderName, hwm);
        MarkerGrab.Init(_MarkerGrabName, hwm);
        if(MarkerGrab.IsOn())  {
            MarkerGrab.SetPosition(ServoPosValues[0]);
            servoPos = ServoPos.OPEN;
        }
    }

    public boolean IsOn() {
        return Rotation.IsOn() && Extender.IsOn() && MarkerGrab.IsOn();
    }

    public void SetServoPosition(ServoPos _servoPos) {
        servoPos = _servoPos;
        if(servoPos == ServoPos.CLOSED) MarkerGrab.SetPosition(ServoPosValues[1]);
        else if (servoPos == ServoPos.OPEN) MarkerGrab.SetPosition(ServoPosValues[0]);
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
            if(gamepad2.y) {
                servoPos = ServoPos.OPEN;
            }
            else if (gamepad2.a) {
                servoPos = ServoPos.CLOSED;
            }

            SetServoPosition(servoPos);

            s+="MarkerGrab position: [" + servoPos.toString() + "]";
        }
        else s+="MarkerGrab not defined/connected.";

        return s;
    }
}
