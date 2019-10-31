package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Brat implements Modul {

    private boolean State;
    private String Name;

    private DcMotor Motor;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        Motor = hwm.dcMotor.get(Name);
        SwitchState(true);
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
