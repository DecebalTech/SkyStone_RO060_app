package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ServoBratePrindere {

    private boolean State;
    private String NameLeft, NameRight;
    private Servo GripLeft, GripRight;

    private final Float[] PositionValues = {1f, 0.75f};

    private enum Position {
        CLOSED, OPEN
    }
    private Position ServoPosition = Position.CLOSED;


    public void Init(String _NameLeft, String _NameRight, HardwareMap hwm) {
        SetName(_NameLeft, _NameRight);
        try {
            GripLeft = hwm.servo.get(NameLeft);
            GripRight = hwm.servo.get(NameRight);
            State = true;
            SetPos(Position.CLOSED);
        }
        catch (Exception ex) {
            State = false;
        }
    }

    public boolean IsOn() {
        return State;
    }

    public void Kill() {
        SwitchState(false);
    }

    public void SetName(String _NameLeft, String _NameRight) {
        NameLeft = _NameLeft;
        NameRight = _NameRight;
    }

    public void SwitchState(boolean _State) {
        State = _State;
    }

    public void SetPos(Position _pos) {
        if(_pos == Position.CLOSED) {
            GripLeft.setPosition(PositionValues[0]);
            GripRight.setPosition(1 - PositionValues[0]);
            ServoPosition = Position.CLOSED;
        }
        else if(_pos == Position.OPEN) {
            GripLeft.setPosition(PositionValues[1]);
            GripRight.setPosition(1 - PositionValues[1]);
            ServoPosition = Position.OPEN;
        }
    }

    public String Move(Gamepad gamepad1) {
        /*
        if(gamepad1.left_bumper)
            SetPos(Position.CLOSED);
        else if(gamepad1.right_bumper)
            SetPos(Position.OPEN);
            
         */
        return "ServoBratePrindere Position: [" + ServoPosition.toString() + "]";
    }
}
