package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "BLUE Parking C- park near Center")
public class BLUE_Parking_C extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException{
        int time=1,gtime=600;
        float ac=.45f;
        initRobot();

        while(!isStarted()) idle();
        rb.movement.moveCM((float)Math.PI,85,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2.2f, -80 , 1f, this);
        sleep(time);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
