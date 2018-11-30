package org.firstinspires.ftc.teamcode.Alpha;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp

public class Testing extends LinearOpMode {

    DcMotor tetrix;
    
    public void forwardRearM(double val){
        tetrix.setPower(val);
        
    }
    
    @Override
    public void runOpMode() {
        
        tetrix = hardwareMap.dcMotor.get("motor1");
        
        
        while(!opModeIsActive())
        {
            idle();
        }
        while(opModeIsActive())
        {
            if(gamepad1.left_stick_y>0.1){
                forwardRearM(gamepad1.left_stick_y);
            }
            else{
                forwardRearM(0);
            }
        }
    }
}
