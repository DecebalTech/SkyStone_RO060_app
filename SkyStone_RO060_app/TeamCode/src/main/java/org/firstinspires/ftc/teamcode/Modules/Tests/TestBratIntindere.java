package org.firstinspires.ftc.teamcode.Modules.Tests;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
@Deprecated
public class TestBratIntindere extends LinearOpMode {

    private DcMotor PositionMotor, ExtendMotor;
    DcMotor GripMotorLeft;
    DcMotor GripMotorRight;
    Servo GripLeft;
    Servo GripRight;

    @Override
    public void runOpMode() throws InterruptedException{
        while (!isStarted()) idle();
        PositionMotor = hardwareMap.dcMotor.get("m1");
        ExtendMotor = hardwareMap.dcMotor.get("m2");
        GripMotorLeft = hardwareMap.dcMotor.get("g1");
        GripMotorRight = hardwareMap.dcMotor.get("g2");
        GripLeft = hardwareMap.servo.get("s1");
        GripRight = hardwareMap.servo.get("s2");
        while(opModeIsActive()){
            GripMotorRight.setDirection(DcMotor.Direction.REVERSE);
            ExtendMotor.setDirection(DcMotor.Direction.REVERSE);
            PositionMotor.setDirection(DcMotor.Direction.REVERSE);
            ExtendMotor.setPower(gamepad1.left_stick_y);
            PositionMotor.setPower(gamepad1.right_stick_y);

            if(gamepad1.left_trigger>=0.1 && gamepad1.right_trigger==0){
                GripMotorLeft.setPower(gamepad1.left_trigger);
                GripMotorRight.setPower(gamepad1.left_trigger);
            }
            if(gamepad1.right_trigger>=0.1 && gamepad1.left_trigger==0){
                GripMotorLeft.setPower(-gamepad1.right_trigger);
                GripMotorRight.setPower(-gamepad1.right_trigger);
            }
            if(gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0){
                GripMotorLeft.setPower(0);
                GripMotorRight.setPower(0);
            }

            if(gamepad1.x){
                GripLeft.setPosition(0.42);
                GripRight.setPosition(0.32);
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
