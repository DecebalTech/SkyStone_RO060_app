package org.firstinspires.ftc.teamcode.Modules.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Test1 extends LinearOpMode {
    DcMotor ms;
    DcMotor md;

    @Override
    public void runOpMode() throws InterruptedException {

        while (!isStarted()) idle();
        ms = hardwareMap.dcMotor.get("m2");
        md = hardwareMap.dcMotor.get("m1");

        while (opModeIsActive()) {
            ms.setPower(gamepad1.left_stick_y); /// ala din stanga
            md.setPower(gamepad1.right_stick_y); ///ala din dreapta
        }
    }
}
