package org.firstinspires.ftc.teamcode.OldAutonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
@Disabled
@Deprecated
public class Parking_Left extends LinearOpMode {

    public Robot rb;



    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this, true); //Initialization of the DecebalBot

        while(!isStarted()) {
            idle();
        }

        rb.movement.moveCM((float)Math.PI/3, 100, 1f, this);
        sleep(100);
        rb.movement.moveCM((float)Math.PI, 50, 1f, this);
        sleep(100);

    }
}
