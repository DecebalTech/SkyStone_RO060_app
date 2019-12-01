package org.firstinspires.ftc.teamcode.Modules;

import android.widget.Switch;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Cub implements Modul {

    private boolean State;
    private String Name;

    private final Float[] PositionValues = {0.98f, 0.65f};

    private enum Position {UP, DOWN};
    private Position ServoPosition = Position.DOWN;

    private Servo Sv;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            Sv = hwm.servo.get(Name);
            SwitchState(true);

            ServoPosition = Position.DOWN;
            Sv.setPosition(PositionValues[1]);
        }
        catch (Exception ex) {
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

    /*public String Move(Gamepad gamepad1) {
        if(gamepad1.x && ServoPosition != Position.DOWN) {
            Sv.setPosition(PositionValues[0]);
            ServoPosition = Position.DOWN;
        }
        if(gamepad1.b && ServoPosition != Position.UP) {
            Sv.setPosition(PositionValues[1]);
            ServoPosition = Position.UP;
        }

        return ServoPosition.toString();
    }
}
