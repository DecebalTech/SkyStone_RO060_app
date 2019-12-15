package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class Parking_Left extends LinearOpMode {

    public Robot rb;



    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot

        while(!isStarted()) {
            idle();
        }

        rb.movement.move((float)Math.PI/3, 100, 1f, this);
        sleep(100);
        rb.movement.move((float)Math.PI, 50, 1f, this);
        sleep(100);

    }
}