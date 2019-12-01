package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;

    public void runOpMode() throws InterruptedException{
        initRobot();
        rb.movement.moveForwards(0.3f, 100);
        sleep(5000);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap);
    }
}
