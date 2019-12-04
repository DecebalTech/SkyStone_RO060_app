package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Motor implements Modul {

    private boolean State;
    private String Name;
    private DcMotor Motor = null;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            Motor = hwm.dcMotor.get(Name);
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
    public void SetName(String _Name) {Name = _Name;}

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
    }

    public void InvertDirection() {
        Motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void SetPower(Float Power) {
        Motor.setPower(Power);
    }
    public void runToPosition()
    {
        Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setTargetPosition(int pos){
        Motor.setTargetPosition(pos);
    }
    public void runUsingEncoder()
    {
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void stopAndResetEncoder(){
        Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

}