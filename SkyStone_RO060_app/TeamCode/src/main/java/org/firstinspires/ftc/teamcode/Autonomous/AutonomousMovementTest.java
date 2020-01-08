package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
@Disabled
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;

    public void runOpMode() throws InterruptedException{
        initRobot();

        while(!isStarted()) idle();
        rb.movement.rotateIMU(-Math.PI/2, 1f, this);
        sleep(1000);
        rb.movement.rotateIMU(Math.PI/4, 1f, this);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
