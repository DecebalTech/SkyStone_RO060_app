package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class VerticalArm {

    public DcMotor arm;
    
    public double armPower = 2.5;
    
    private String armName = "verticalArm";
    
    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        if(gamepad2.left_stick_y!=0)
            arm.setPower(gamepad2.left_stick_y * armPower);
        else arm.setPower(0);
            
    }
    
    public void initVerticalArm(HardwareMap hwm)
    {
        arm = hwm.dcMotor.get(armName);
    }
}
