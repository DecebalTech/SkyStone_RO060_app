package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestBratePrindere extends LinearOpMode{

    DcMotor GripMotorLeft;
    DcMotor GripMotorRight;
    Servo GripLeft;
    Servo GripRight;

    @Override
    public void runOpMode() throws InterruptedException {

        while (!isStarted()) idle();
        GripMotorLeft = hardwareMap.dcMotor.get("g1");
        GripMotorRight = hardwareMap.dcMotor.get("g2");
        GripLeft = hardwareMap.servo.get("s1");
        GripRight = hardwareMap.servo.get("s2");

        while (opModeIsActive()) {
            GripMotorLeft.setPower(gamepad1.left_stick_y);
            GripMotorRight.setDirection(DcMotor.Direction.REVERSE);
            GripMotorRight.setPower(gamepad1.left_stick_y);
            if(gamepad1.x){
                GripLeft.setPosition(0.42);
                GripRight.setPosition(0.3);
            }
            if(gamepad1.b) {
                GripLeft.setPosition(0.1);
                GripRight.setPosition(0.5);
            }
            telemetry.addData("Grip Left Position:", GripLeft.getPosition());
            telemetry.addData("Grip Right Position:", GripRight.getPosition());
            telemetry.update();
        }

    }
}
