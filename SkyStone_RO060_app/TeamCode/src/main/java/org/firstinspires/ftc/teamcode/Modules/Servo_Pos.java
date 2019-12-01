package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Servo_Pos implements Modul {

    private Servo Sv;
    private boolean State;
    private String Name;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            Sv = hwm.servo.get(Name);
            State = true;
        }
        catch (Exception e) {
            State = false;
        }
    }

    @Override
    public boolean IsOn() {
        return State;
    }

    @Override
    public void Kill() {
        State = false;
    }

    @Override
    public void SetName(String _Name) {
        Name = _Name;
    }

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
    }
}
