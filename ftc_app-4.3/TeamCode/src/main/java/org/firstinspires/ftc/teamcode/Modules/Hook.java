package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Hook {

    public DcMotor hook;
    
    public double hookPower = 1;
    public int maxHookPos = -13300;
    private String hookName = "hook";
    
    public void updateHook(Gamepad gamepad1, Gamepad gamepad2)
    {
        if(gamepad1.dpad_up)
            hook.setPower(-hookPower);
        if(gamepad1.dpad_down)
            hook.setPower(hookPower);
        if(!gamepad1.dpad_up && !gamepad1.dpad_down)
            hook.setPower(0);
    }
    
    public void initHook(HardwareMap hwm)
    {
        hook = hwm.dcMotor.get(hookName);
        hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hook.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    
    public void moveHookWithEncoders(int pos){
        hook.setTargetPosition(pos);
        hook.setPower(hookPower);
        while (hook.isBusy()) {}
        hook.setPower(0);
    }
    
    public void up() {
        hook.setTargetPosition(maxHookPos);
        hook.setPower(hookPower);
        while (hook.isBusy()) {}
        hook.setPower(0);
    }
    
    public void down() {
        hook.setTargetPosition(0);
        hook.setPower(hookPower);
        while (hook.isBusy()) {}
        hook.setPower(0);
    }
}
