package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ServoBratePrindere implements Modul {

    private boolean State;
    private String Name;

    private final Float[] PositionValues = {1f, 0.5f};

    private enum Position {OPEN, CLOSED}
    private Position ServoPosition = Position.CLOSED;

    private Servo GripLeft, GripRight;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            GripLeft = hwm.servo.get(Name);
            GripRight = hwm.servo.get(Name);
            SwitchState(true);

            ServoPosition = Position.CLOSED;
            GripLeft.setPosition(PositionValues[1]);
            GripRight.setPosition(PositionValues[1]);
        } catch (Exception ex) {
            SwitchState(false);
        }
    }

    @Override
    public boolean IsOn() {
        return State;
    }

    @Override
    public void Kill() {
        SwitchState(false);
    }

    @Override
    public void SetName(String _Name) {
        Name = _Name;
    }

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
    }

    public String Move(Gamepad gamepad1) {
        if(gamepad1.x && ServoPosition != Position.CLOSED) {
            GripLeft.setPosition(PositionValues[0]);
            GripRight.setPosition(PositionValues[0]);
            ServoPosition = Position.OPEN;
        }
        if(gamepad1.b && ServoPosition != Position.OPEN) {
            GripLeft.setPosition(PositionValues[1]);
            GripRight.setPosition(PositionValues[1]);
            ServoPosition = Position.OPEN;
        }

        return ServoPosition.toString();
    }
}
