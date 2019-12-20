package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="RED STONES")
public class RED_STONES extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this);

        while(!isStarted()) {
            //scan stones
        }

        //... do autonomous based on the scan
    }
}
