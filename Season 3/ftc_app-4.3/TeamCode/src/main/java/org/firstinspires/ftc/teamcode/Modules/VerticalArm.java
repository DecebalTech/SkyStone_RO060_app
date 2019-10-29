package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class VerticalArm {

    public DcMotor arm;
    
    public double armPower = 1;
    public int maxArmPosition, halfPos = 2000;
    public boolean moving = false;
    private String armName = "verticalArm";
    
    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        if(gamepad2.right_stick_y!=0 && !moving)
            arm.setPower(gamepad2.right_stick_y * armPower);
        else if (!moving) arm.setPower(0);
        
        
        if(gamepad2.dpad_down)
            down();
            
        if(gamepad2.dpad_up) {
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        
        if(arm.getCurrentPosition()>=0 && moving) {
            arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            arm.setPower(0);
            moving = false;
        }
            
    }
    
    public void initVerticalArm(HardwareMap hwm)
    {
        arm = hwm.dcMotor.get(armName);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //arm.setDirection(DcMotorSimple.Direction.REVERSE);
        
        
    }
    
    public void down() 
    {
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setTargetPosition(0);
        arm.setPower(0.9);
        moving = true;
    }
    
    public int getPosition() 
    {
        return arm.getCurrentPosition();
    }
}
