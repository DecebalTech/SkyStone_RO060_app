package org.firstinspires.ftc.teamcode.Modules.OldRoboChassisModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Motor;

import static java.lang.Math.*;

public class TwoWheel_Movement {

    private Motor frontLeft = new Motor(), frontRight = new Motor();
    private static String Names[] = {"frontLeft", "frontRight"};
    private float turbo=0.5f;

    public void Init(HardwareMap hwm) {
        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);

        if(AreWheelsActive()) {
            frontRight.InvertDirection();
        }
    }
    public void updateTurbo(Gamepad gamepad1)
    {
        if(gamepad1.left_trigger>0.1) SetTurbo(0.15f);
        else if(gamepad1.right_trigger>0.1) SetTurbo(1);
        else SetTurbo(0.5f);
    }
  public String Move(Gamepad gamepad1) {
        updateTurbo(gamepad1);

        if(pow(gamepad1.right_stick_x, 2) > 0.1) {
            frontLeft.SetPower(-gamepad1.right_stick_x*turbo);
            frontRight.SetPower(gamepad1.right_stick_x*turbo);
        }
        else if(pow(gamepad1.left_stick_y, 2) > 0.1) {
            frontLeft.SetPower(gamepad1.left_stick_y*turbo);
            frontRight.SetPower(gamepad1.left_stick_y*turbo);
  }
        else StopMotors();

        return "Power: N/A" ;
    }

    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn();
    }

    public void StopMotors() {
        frontLeft.SetPower(0f);
        frontRight.SetPower(0f);
    }

    public void SetTurbo(float t){ turbo =t;}

    public double GetTurbo()
    {
       return turbo;
    }

    public void RunToPosition()
    {
        frontLeft.runToPosition();
        frontRight.runToPosition();
    }

    public void SetTargetPosition(int pos){
        frontLeft.setTargetPosition(pos);
        frontRight.setTargetPosition(pos);
    }

    public void SetPower(float power){
        frontLeft.SetPower(power);
        frontRight.SetPower(power);
    }

    public void StopAndResetEncoder(){
        frontLeft.stopAndResetEncoder();
        frontRight.stopAndResetEncoder();
    }

    public void moveForwards(float power, int pos){
        StopAndResetEncoder();
        SetTargetPosition(pos);
        RunToPosition();
        frontLeft.SetPower(power);
        frontRight.SetPower(-power);
    }

    public void rotate(float power, int pos){
        StopAndResetEncoder();
        SetTargetPosition(pos);
        RunToPosition();
        frontRight.SetPower(power);
        frontLeft.SetPower(0f);
    }
}

