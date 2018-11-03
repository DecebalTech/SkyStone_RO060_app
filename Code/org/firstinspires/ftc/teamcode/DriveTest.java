package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.DriveTest;

@TeleOp

public class DriveTest extends LinearOpMode {
    
    @Override public void runOpMode() {
        
        DcMotor m1,m2;
        
        m1 = hardwareMap.dcMotor.get("moto1");
        m2 = hardwareMap.dcMotor.get("moto2");
        
        double movePower = 1, movePowerMin = 0.5, movePowerMax = 2.5;
        
        while(!isStarted()) {
            idle();
        }
        
        while(opModeIsActive()) {
            if(gamepad1.left_stick_y!=0){
                m1.setPower(gamepad1.left_stick_y*movePower);
                m2.setPower(-gamepad1.left_stick_y*movePower);
            }
            else if(gamepad1.left_stick_x!=0){
                m1.setPower(-gamepad1.left_stick_x*movePower);
                m2.setPower(-gamepad1.left_stick_x*movePower);
            }
        
            if(gamepad1.left_stick_x==0 && gamepad1.left_stick_y==0){
                    m1.setPower(0);
                    m2.setPower(0);
            }
        }
    }
}
