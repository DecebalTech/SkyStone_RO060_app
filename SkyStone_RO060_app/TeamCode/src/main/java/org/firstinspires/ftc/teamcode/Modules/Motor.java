package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.openftc.revextensions2.RevBulkData;

import static org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.DriveConstants.getMotorVelocityF;

public class Motor implements Modul {

    private boolean State, Moving;
    private String Name;
    private DcMotorEx Motor = null;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            Motor = hwm.get(DcMotorEx.class, Name);
            State = true;
            Moving = false;
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

    public boolean IsMoving() {return Moving;}
    public void SetMoving(boolean _Moving) {Moving = _Moving;}

    public void InvertDirection() {
        Motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void Brake() {Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}
    public void Float() {Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);}

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
    public void stopAndResetEncoder(){ Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); }
    public int getCurrentPosition() {return Motor.getCurrentPosition();}
    public int getCurrentPosition(RevBulkData bulkData) {return bulkData.getMotorCurrentPosition(Motor);}
    public boolean isBusy() {return Motor.isBusy(); }
    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients) {
        Motor.setPIDFCoefficients(runMode, coefficients);
    }
    public PIDFCoefficients getPIDFCoefficients(DcMotor.RunMode runMode) {
        return Motor.getPIDFCoefficients(runMode);
    }

}
