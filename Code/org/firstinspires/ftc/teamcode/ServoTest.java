package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp

public class ServoTest extends LinearOpMode
{

    CRServo radu;

    @Override public void runOpMode() {

        radu = hardwareMap.crservo.get("radu");

        while(!isStarted())
        {
            idle();
        }
        while (opModeIsActive()) {
            // check to see if we need to move the servo.
            if(gamepad1.x) {
                radu.setPower(-10);
                telemetry.addLine("Inainte");
            }
            if(gamepad1.a) {
                radu.setPower(0);
                telemetry.addLine("STOP");
            }
            if(gamepad1.b) {
                radu.setPower(10);
                telemetry.addLine("Inapoi");
            }
            telemetry.update();

        }
    }

}
