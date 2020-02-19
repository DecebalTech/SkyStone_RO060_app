package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class TestOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo test;
        boolean testState;

        test = hardwareMap.servo.get("FoundationLeft");
        testState = true;

        while (!isStarted()) idle();

        while(opModeIsActive()) {
            if(gamepad1.x && testState) test.setPosition(.3);
            if(gamepad1.b && testState) test.setPosition(.7);
            if(gamepad1.a && testState) {
                test = null;
                testState = false;
            }
            if(gamepad1.y && !testState) {
                test = hardwareMap.servo.get("FoundationLeft");
                testState = true;
            }
        };
    }
}
