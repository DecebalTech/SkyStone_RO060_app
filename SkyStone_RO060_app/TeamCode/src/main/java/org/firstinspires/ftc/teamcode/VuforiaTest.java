package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Scanner;

@TeleOp
public class VuforiaTest extends LinearOpMode {

    Scanner sc;

    @Override
    public void runOpMode() throws InterruptedException {
        sc = new Scanner();

        sc.Init("webcam", hardwareMap);

        sc.initTfod(hardwareMap);
        sc.activateTfod();
        waitForStart();

        while(opModeIsActive()) {
            sc.scan(this);
        }

    }
}
