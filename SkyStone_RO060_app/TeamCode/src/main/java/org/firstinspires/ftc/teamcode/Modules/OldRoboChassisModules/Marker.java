package org.firstinspires.ftc.teamcode.Modules.OldRoboChassisModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Modules.Modul;

public class Marker implements Modul {

    private boolean State;
    private String Name;

    private final Float[] PositionValues = {0f, .5f};

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

    public String Move(Gamepad gamepad1) {
        if(gamepad1.dpad_up  && (ServoPosition != Position.DOWN)) {
            Sv.setPosition(PositionValues[0]);
            ServoPosition = Position.DOWN;
        }
        if(gamepad1.dpad_down && (ServoPosition != Position.UP)) {
            Sv.setPosition(PositionValues[1]);
            ServoPosition = Position.UP;
        }

        return ServoPosition.toString();
    }
}