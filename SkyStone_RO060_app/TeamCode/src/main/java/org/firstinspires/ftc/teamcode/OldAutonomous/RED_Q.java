package org.firstinspires.ftc.teamcode.OldAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED Q")
@Disabled
public class RED_Q extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException{
        int time=1,gtime=600;
        float ac=.45f;
        initRobot();

        while(!isStarted()) idle();
        sleep(12000);
        rb.movement.moveCM((float)Math.PI/2.05f,210,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI,-140,1f,this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2,35,0.5f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2,-35,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI,130,1f,this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(Math.PI,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI,-35,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI,10,1f,this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.95f,200,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2,-45,1f,this);
        sleep(time);

    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
