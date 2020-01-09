package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous

public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;

    public void runOpMode() throws InterruptedException{
        initRobot();

        while(!isStarted()) idle();
        rb.movement.rotateIMURelative(-Math.PI/2, 1f, this);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
