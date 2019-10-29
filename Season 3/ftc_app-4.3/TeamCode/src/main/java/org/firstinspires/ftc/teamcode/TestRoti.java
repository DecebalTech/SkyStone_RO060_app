package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Testare Roti (encoderTicks / s)", group="Teste")

public class TestRoti extends LinearOpMode {
    
    Movement mov = new Movement();
    DcMotor tester;
    double power = 1;
    
    @Override public void runOpMode() {
        
        initRobot();
        
        telemetry.addLine("dpad_up - stangaFata \ndpad_right - dreaptaFata \ndpad_down - stangaSpate \ndpad_left - dreaptaSpate ");
        telemetry.addLine("x - 10 s \ny - 15 s \na - 20 s \nb - 30 s ");
        telemetry.update();
        
        while(!isStarted())
        {
            idle();
        }
        
        while(opModeIsActive())
        {
            if(gamepad1.dpad_up)
                tester = mov.wheels.stangaFata;
            if(gamepad1.dpad_right)
                tester = mov.wheels.dreaptaFata;
            if(gamepad1.dpad_down)
                tester = mov.wheels.stangaSpate;
            if(gamepad1.dpad_left)
                tester = mov.wheels.dreaptaSpate;
                
            if(gamepad1.x)
            {
                telemetry.addLine("6 s");
                telemetry.update();
                mov.wheels.stopAndResetEncoder();
                mov.wheels.runUsingEncoder();
                tester.setPower(power);
                sleep(6000);
                tester.setPower(0);
                telemetry.addData("Rot", tester.getCurrentPosition());
                telemetry.update();
            }
            if(gamepad1.y)
            {
                telemetry.addLine("15 s");
                telemetry.update();
                mov.wheels.stopAndResetEncoder();
                mov.wheels.runUsingEncoder();
                tester.setPower(power);
                sleep(15000);
                tester.setPower(0);
                telemetry.addData("Rot", tester.getCurrentPosition());
                telemetry.update();
            }
            if(gamepad1.a)
            {
                telemetry.addLine("20 s");
                telemetry.update();
                mov.wheels.stopAndResetEncoder();
                mov.wheels.runUsingEncoder();
                tester.setPower(power);
                sleep(20000);
                tester.setPower(0);
                telemetry.addData("Rot", tester.getCurrentPosition());
                telemetry.update();
            }
            if(gamepad1.b)
            {
                telemetry.addLine("30 s");
                telemetry.update();
                mov.wheels.stopAndResetEncoder();
                mov.wheels.runUsingEncoder();
                tester.setPower(power);
                sleep(30000);
                tester.setPower(0);
                telemetry.addData("Rot", tester.getCurrentPosition());
                telemetry.update();
            }
            /*
        
            if(gamepad1.x)
                tester = mov.stangaFata;
            else if (gamepad1.y)
                tester = mov.dreaptaFata;
            else if (gamepad1.a)
                tester = mov.stangaSpate;
            else if (gamepad1.b)
                tester = mov.dreaptaSpate;
                
            double power = gamepad1.left_stick_y;
            if(gamepad1.left_stick_y!=0)
                tester.setPower(power);
            else mov.stop();
            
            telemetry.addData("Putere:", power);
            telemetry.update();
            
            if(gamepad1.dpad_up)
                mov.resetEncoders();
            */
            
        }
    }
    
    public void initRobot() {
            mov.initMovement(hardwareMap);
            mov.wheels.runUsingEncoder();
    }
}
