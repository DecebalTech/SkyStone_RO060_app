package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class PlayerControl extends LinearOpMode {

    private Robot rb = new Robot(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        while(!isStarted()) idle();

        rb.LinearUpdate(gamepad1, gamepad2);
    }
}
