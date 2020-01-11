package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED Parking W- park near wall")
public class RED_Parking_W extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException{
        int time=1,gtime=600;
        float ac=.45f;
        initRobot();

        while(!isStarted()) idle();
        rb.movement.moveCM((float)Math.PI/1.8f, -70 , 1f, this);
        sleep(time);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}