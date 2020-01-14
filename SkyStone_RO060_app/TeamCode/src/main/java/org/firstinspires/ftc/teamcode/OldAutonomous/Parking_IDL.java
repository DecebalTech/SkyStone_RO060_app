package org.firstinspires.ftc.teamcode.OldAutonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
@Disabled
@Deprecated
@Autonomous (name="RED - STONES")
public class Parking_IDL extends LinearOpMode {

    public Robot rb;



    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the DecebalBot

        while(!isStarted()) {
            idle();
        }

        sleep(25000);

        rb.movement.moveCM((float)Math.PI, 100, 1f, this);
        sleep(50);
        rb.movement.moveCM((float)Math.PI/2, 100, 1f, this );

    }
}
