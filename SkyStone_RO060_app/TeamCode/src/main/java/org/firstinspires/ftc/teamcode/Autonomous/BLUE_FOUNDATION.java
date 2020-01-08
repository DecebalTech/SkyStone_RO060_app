package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.MarkerArm;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.ServoBratePrindere;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "BLUE FOUNDATION")
public class BLUE_FOUNDATION extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot
        int time=1,gtime=600;
        float ac=.65f;
        while(!isStarted()) {
            idle();
        }
        rb.movement.moveCM((float)Math.PI/13, 120 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/16, 20, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/7, -100 , 1f, this);
        sleep(time);
        rb.movement.rotate((float)Math.PI/2*1.3f, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -75 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 15 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 30 , 1f, this);
        sleep(time);
        rb.movement.rotate((float)Math.PI/2.1f, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -55 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 220 , 1f, this); //130
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 45 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 26 , 0.45f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -45 , 1f, this);
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
        rb.movement.moveCM((float)Math.PI/2, 110 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 50 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 25 , 0.7f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -45 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -230 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.8f, 30 , 1f, this);
        sleep(time);
       /*



*/
    }
}