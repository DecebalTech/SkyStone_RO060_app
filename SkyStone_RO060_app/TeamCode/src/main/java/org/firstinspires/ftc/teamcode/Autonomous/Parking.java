package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class Parking extends LinearOpMode {

    public Robot rb;



    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot

        while(!isStarted()) {
            idle();
        }

        rb.movement.stopAndResetEncoder();
        rb.movement.runUsingEncoder();

        rb.movement.move(90, 100, this);
        sleep(100);
        rb.movement.moveForwards(100, this);
        sleep(100);

    }
}
