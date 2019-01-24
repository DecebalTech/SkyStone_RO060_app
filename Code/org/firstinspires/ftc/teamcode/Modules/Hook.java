package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Hook {

    public DcMotor hook;
    
    public double hookPower = 1;
    public int maxHookPos = -10650;
    private String hookName = "hook";
    
    public void updateHook(Gamepad gamepad1, Gamepad gamepad2)
    {
        
        //int hookCurrPos = hook.getCurrentPosition();
        
        if(gamepad1.dpad_up)
            hook.setPower(-hookPower);
        if(gamepad1.dpad_down)
            hook.setPower(hookPower);
        if(!gamepad1.dpad_up && !gamepad1.dpad_down)
            hook.setPower(0);
            
        /*if (gamepad1.left_trigger > 0.1){
            hook.setTargetPosition(maxHookPos);
            hook.setPower(hookPower);
            while (hook.isBusy()) {}
            hook.setPower(0);
            //tu me bois
            
        }
        if (gamepad1.right_trigger > 0.1){
            hook.setTargetPosition(-200);
            hook.setPower(hookPower * 2);
            while (hook.isBusy()) {}
            hook.setPower(0);
        }*/
            
    }
    
    public void initHook(HardwareMap hwm)
    {
        hook = hwm.dcMotor.get(hookName);
    }
    
    public void moveHookWithEncoders(int pos){
        //hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        hook.setTargetPosition(pos);
        hook.setPower(hookPower);
        while (hook.isBusy()) {}
        hook.setPower(0);
        
        //hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
