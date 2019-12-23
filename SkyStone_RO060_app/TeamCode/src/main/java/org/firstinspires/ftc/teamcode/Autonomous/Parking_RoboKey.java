package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="BLUE - STONES")
public class Parking_RoboKey extends LinearOpMode {

    public Robot rb;



    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot

        while(!isStarted()) {
            idle();
        }

        sleep(1000);

        rb.movement.moveCM(0, 100, 1f, this);
        sleep(50);
        rb.movement.moveCM((float)Math.PI/2, 100, 1f, this );

    }
}
