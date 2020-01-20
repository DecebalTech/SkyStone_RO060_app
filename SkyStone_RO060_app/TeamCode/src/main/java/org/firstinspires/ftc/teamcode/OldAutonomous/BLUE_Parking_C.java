package org.firstinspires.ftc.teamcode.OldAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
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
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(time);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // deschidem servoul de prindere
        sleep(time);
        rb.movement.moveCM((float)Math.PI,-105,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 80 , 1f, this);
        sleep(time);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
