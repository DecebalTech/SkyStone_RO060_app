package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
@Disabled
@Deprecated
public class TestControl extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        while(!isStarted()) idle();

        while(opModeIsActive()) {
            if (gamepad1.x) {
                telemetry.addLine("Yeet");
            }
            if (gamepad1.y) telemetry.addLine("Haw");
            telemetry.update();

        }
    }
}
