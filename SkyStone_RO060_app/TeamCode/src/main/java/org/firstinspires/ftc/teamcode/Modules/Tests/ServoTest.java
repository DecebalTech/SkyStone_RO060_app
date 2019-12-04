package org.firstinspires.ftc.teamcode.Modules.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class ServoTest extends LinearOpMode {

   Servo cub = hardwareMap.servo.get("Cub");



    @Override
    public void runOpMode() throws InterruptedException {
        while (!isStarted())
            idle();
        while(opModeIsActive()) {
if (gamepad1.x)
            cub.setPosition(1);
           if(gamepad1.b)
            cub.setPosition(0);
    }
}}
