package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED FOUNDATION")
public class RED_FOUNDATION extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot

        while(!isStarted()) {
            idle();
        }

        rb.movement.move(0f, 100, 1f, this);
        sleep(100);
        rb.movement.rotate((float)Math.PI, 1f, this);
        sleep(100);

    }
}
