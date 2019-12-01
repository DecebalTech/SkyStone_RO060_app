package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
        rb = new Robot(hardwareMap);
    }
}
