package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class PlayerControl extends LinearOpMode {

    private Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        while(!isStarted()) idle();

        while(opModeIsActive()) {
            rb.LinearUpdate(gamepad1, gamepad2, this);
            telemetry.update();
        }
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
