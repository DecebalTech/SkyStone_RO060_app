package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="Testare Servo (matura; cuva)", group="Teste")

public class TestServo extends LinearOpMode{

    public CRServo matura1, matura2;
    public Servo cuva;
    
    @Override public void runOpMode(){
    
        matura1 = hardwareMap.crservo.get("matura1");
        matura2 = hardwareMap.crservo.get("matura2");
        cuva = hardwareMap.servo.get("cuva");
        
        while(!isStarted()){
                idle();
            }
            
        while (opModeIsActive()){
            if (gamepad1.right_bumper){
                matura1.setPower(1);
                matura2.setPower(-1);
            }
            if (gamepad1.left_bumper){
                matura1.setPower(-1);
                matura2.setPower(1);
            }
            if (!gamepad1.right_bumper && !gamepad1.left_bumper){
                matura1.setPower(0);
                matura2.setPower(0);
            }
            if (gamepad1.x){
                cuva.setPosition(1);
            }
            if(gamepad1.y){
                cuva.setPosition(0.4);
            }
        }
    }
    
    
}
