/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.ControlDrive;

@TeleOp

public class ControlDrive extends LinearOpMode {
    
    @Override public void runOpMode() {
        
        DcMotor m1,m2;
        
        m1 = hardwareMap.dcMotor.get("moto1");
        m2 = hardwareMap.dcMotor.get("moto2");
        
        
        double movePower = 1, movePowerMin = 0.5, movePowerMax = 2.5;
        boolean turboIsPressed = false;
        
        relicClaw.setPosition(1);
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            
            /* telemetry 
            telemetry.addLine("gamepad 1 | ")
                    .addData("l", gamepad1.left_bumper);
            telemetry.addLine("gamepad 1 | ")
                    .addData("r", gamepad1.right_bumper);
            telemetry.addLine("gamepad 2 | ")
                    .addData("l", gamepad2.left_bumper);
            telemetry.addLine("gamepad 2 | ")
                    .addData("r", gamepad2.right_bumper);
            telemetry.addLine("current thrust power: ").addData("power = ", movePower);
            telemetry.addLine("is relic claw open: |").addData("open = ", relic_claw_isOpen);
            telemetry.update();
            
            /* movement 
            if(gamepad1.left_stick_y!=0){
                m1.setPower(gamepad1.left_stick_y*movePower);
                m2.setPower(gamepad1.left_stick_y*movePower);
                m3.setPower(-gamepad1.left_stick_y*movePower);
                m4.setPower(-gamepad1.left_stick_y*movePower);
            }
            else if(gamepad1.left_stick_x!=0){
                m1.setPower(-gamepad1.left_stick_x*movePower);
                m2.setPower(-gamepad1.left_stick_x*movePower);
                m3.setPower(-gamepad1.left_stick_x*movePower);
                m4.setPower(-gamepad1.left_stick_x*movePower);
            }
            else if(gamepad1.right_stick_x!=0) {
                m1.setPower(-gamepad1.right_stick_x*movePower);
                m2.setPower(gamepad1.right_stick_x*movePower);
                m3.setPower(-gamepad1.right_stick_x*movePower);
                m4.setPower(gamepad1.right_stick_x*movePower);
            }
            if(gamepad1.left_stick_x==0 && gamepad1.left_stick_y==0 && gamepad1.right_stick_x==0){
                m1.setPower(0);
                m2.setPower(0);
                m3.setPower(0);
                m4.setPower(0);
            }
            
            //turbo
            if(gamepad1.left_bumper && !turboIsPressed) {
                movePower-=0.5;
                turboIsPressed = true;
                if(movePower<movePowerMin) movePower = movePowerMin;
            }
            if(gamepad1.right_bumper & !turboIsPressed) {
                movePower+=0.5;
                turboIsPressed=true;
                if(movePowerMax>2.5) movePower = movePowerMax;
            }
            if(!gamepad1.right_bumper && !gamepad1.left_bumper)
                turboIsPressed = false;
            
            /* glyph lift 
            if(gamepad2.left_stick_y!=0)
                liftDC.setPower(-gamepad2.left_stick_y * liftPower);
            else 
                liftDC.setPower(0);   
                
            /* glyph claws
            
            //upClaws
            if (gamepad2.left_bumper && !upClaw_isPressed)
            {
                upClaw_isPressed = true;
                if(upClaw_closed)
                {
                    upClaw_closed = false;
                    claw0.setPosition(0.3);
                    claw3.setPosition(0.6);
                }
                else
                {
                    upClaw_closed = true;
                    claw0.setPosition(0.6);
                    claw3.setPosition(0.3);
                }
            }
            if(!gamepad2.left_bumper)
                upClaw_isPressed = false;
            
            //downClaws
            if (gamepad2.right_bumper && !downClaw_isPressed) 
            {
                downClaw_isPressed = true;
                if(downClaw_closed)
                {
                    downClaw_closed = false;
                    claw1.setPosition(0.6);
                    claw2.setPosition(0.3);
                }
                else
                {
                    downClaw_closed = true;
                    claw1.setPosition(0.3);
                    claw2.setPosition(0.6);
                }
            }
            if(!gamepad2.right_bumper)
                downClaw_isPressed = false;
                
            /* relic arm 
            if(gamepad2.left_trigger>0)
                armDC.setPower(armPower * gamepad2.left_trigger);
            if(gamepad2.right_trigger>0)
                armDC.setPower(-armPower * gamepad2.right_trigger);
            if(gamepad2.left_trigger==0 && gamepad2.right_trigger==0)
                armDC.setPower(0);
                
            /* relic claw 
            if(gamepad2.a)
                relicHand.setPosition(1);
            if(gamepad2.b)
                relicHand.setPosition(0.5);
            if(gamepad2.y && !relic_claw_isPressed)
            {
                relic_claw_isPressed = true;
                if(relic_claw_isOpen)
                {
                    relic_claw_isOpen = false;
                    relicClaw.setPosition(1);
                }
                else
                {
                    relic_claw_isOpen = true;
                    relicClaw.setPosition(0);
                }
            }
            if(!gamepad2.y)
                relic_claw_isPressed = false;
            
        }
    }
}*/
