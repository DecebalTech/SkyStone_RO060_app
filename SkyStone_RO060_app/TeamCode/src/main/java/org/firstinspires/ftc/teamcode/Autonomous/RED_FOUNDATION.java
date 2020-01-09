package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.MarkerArm;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.ServoBratePrindere;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED FOUNDATION")
public class RED_FOUNDATION extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException{
        int time=1,gtime=600;
        float ac=.45f;
        initRobot();

        while(!isStarted()) idle();
        rb.movement.moveCM((float)Math.PI/1.07f, -110 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.07f, -20 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.1f, 100 , 0.7f, this);
        sleep(time);
        rb.movement.rotate((float)Math.PI/-1.77f, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -75 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 30 , 0.8f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 40 , 1f, this);
        sleep(time);
        rb.movement.rotateIMU((float)Math.PI/2.1f, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 205 , 1f, this); //130
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -45 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 26 , 0.45f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 55 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
        rb.movement.rotate((float)Math.PI, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 140 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.OUT);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -95 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
        rb.movement.rotate((float)Math.PI, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 100 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -50 , 1f, this);
        sleep(time);
        rb.movement.rotate((float)Math.PI/24, ac, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 25 , 0.7f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 50 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -240 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2.5f, 30 , 1f, this);
        sleep(time);

    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}

