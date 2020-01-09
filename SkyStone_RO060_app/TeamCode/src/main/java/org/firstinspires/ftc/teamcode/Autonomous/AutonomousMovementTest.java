package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
@Disabled
@Deprecated
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;

    public void runOpMode() throws InterruptedException{
        initRobot();
int time=300;
        while(!isStarted()) idle();
        sleep(time);
        rb.movement.rotateIMUAbsolute((float)Math.PI/2, 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute((float)Math.PI, 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute((float)Math.PI*3/2f, 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
