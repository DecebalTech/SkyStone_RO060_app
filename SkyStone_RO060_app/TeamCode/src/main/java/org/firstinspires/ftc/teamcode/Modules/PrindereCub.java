package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PrindereCub implements Modul {

    private DcMotor GripMotorLeft, GripMotorRight;
    private boolean State;
    private String Name;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            GripMotorLeft = hwm.dcMotor.get(Name);
            GripMotorLeft = hwm.dcMotor.get(Name);
            State = true;
        }
        catch (Exception ex) {
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

    public String Move(Gamepad gamepad1){
        if(gamepad1.a && )
    }
}
