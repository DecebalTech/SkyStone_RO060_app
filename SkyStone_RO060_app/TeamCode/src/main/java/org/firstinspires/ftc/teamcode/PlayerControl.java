package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.autoRotate;

@TeleOp
public class PlayerControl extends LinearOpMode {

    private Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        initRobot();
        while(!isStarted()) idle();
        sleep(1);
        rb.autoRotate.armSetPosition(autoRotate.armPositions.forward);
        sleep(1);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.Drive); // initializare
        sleep(1);
        while(opModeIsActive()) {
            rb.LinearUpdate(gamepad1, gamepad2, this);

            telemetry.update();
        }
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, false);
    }
}
