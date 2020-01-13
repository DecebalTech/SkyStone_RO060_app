package org.firstinspires.ftc.teamcode.OldAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED FOUNDATION - foundation, park")
public class RED_FOUNDATION extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException{
        int time=1,gtime=600;
        float ac=.45f;
        initRobot();

        while(!isStarted()) idle();
        rb.movement.moveCM((float)Math.PI/1.09f, -110 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.09f, -20 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.1f, 90 , 0.7f, this); //strafe cu placa
        sleep(time);
        rb.movement.rotate((float)Math.PI/-1.77f, ac, this);
        //rb.movement.rotateIMU(-Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -75 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 30 , 0.8f, this);
        sleep(time);
        //rb.movement.moveCM((float)Math.PI/2, 25 , 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 90, 1f, this);
        sleep(time);
        rb.movement.moveCM(0, 10, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 115 , 1f, this); //130
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 30, 1f, this);
        sleep(100);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}

