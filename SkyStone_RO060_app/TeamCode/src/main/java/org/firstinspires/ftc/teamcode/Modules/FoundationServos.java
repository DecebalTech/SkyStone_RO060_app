package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FoundationServos {
    private Servo_Pos FoundationLeft = new Servo_Pos(), FoundationRight = new Servo_Pos();

    private enum ServoPositions {
        UP,
        DOWN
    };

    private ServoPositions ServoPosition = ServoPositions.UP;
    private float[] ServoPositionsValues = {.8f, .15f};

    public void Init(String _FoundationLeftName, String _FoundationRightName, HardwareMap hwm) {
        FoundationLeft.Init(_FoundationLeftName, hwm);
        FoundationRight .Init(_FoundationRightName, hwm);
    }

    public void SetPosition(ServoPositions _pos) {
        ServoPosition = _pos;

        if(ServoPosition == ServoPositions.UP) {
            if(FoundationLeft.IsOn()) FoundationLeft.SetPosition(ServoPositionsValues[0]);
            if(FoundationRight.IsOn()) FoundationRight.SetPosition(ServoPositionsValues[0]);
        }
        else if (ServoPosition == ServoPositions.DOWN) {
            if(FoundationLeft.IsOn()) FoundationLeft.SetPosition(ServoPositionsValues[1]);
            if(FoundationRight.IsOn()) FoundationRight.SetPosition(ServoPositionsValues[1]);
        }
    }

    public String Update(Gamepad gamepad1) {
        if(gamepad1.dpad_up) {
            SetPosition(ServoPositions.UP);
        }
        else if(gamepad1.dpad_down) {
            SetPosition(ServoPositions.DOWN);
        }

        return "FoundationServos position: [" + ServoPosition.toString() + "]";
    }
}