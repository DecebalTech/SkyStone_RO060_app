package org.firstinspires.ftc.teamcode.OldAutonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="RED STONES WALL P")
@Disabled
public class RED_STONES_WALL_P extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this, true);
        int time=1;
        float ac=.5f;
        while(!isStarted()) {
            //scan stones
        }
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/(float)2.7, 100 , 1f, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/(float)2.7, 40 , ac, this);
        sleep(300);

        rb.movement.moveCM((float)Math.PI/2, -100 , 1f, this);
        sleep(time);

        rb.movement.rotate((float)Math.PI/-2, ac, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI, -50 , 0.5f, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);

        rb.movement.moveCM((float)Math.PI, 10 , 0.5f, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2, 120 , 0.3f, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.OUT);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/(float)2.3, -230 , 1f, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);

        rb.movement.rotate((float)Math.PI/2, ac, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2, 110 , .5f, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2, 15 , ac, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/-2, 120 , 1f, this);
        sleep(time);

        rb.movement.rotate((float)Math.PI/-2, ac, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI, -80 , 0.5f, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);

        rb.movement.moveCM((float)Math.PI, 10 , 0.5f, this);
        sleep(time);


        rb.movement.moveCM((float)Math.PI/2, 240 , 1f, this);
        sleep(time);


        rb.movement.moveCM((float)Math.PI/-2, 60 , 1f, this);
        sleep(time);

    }
}
/*
rb.movement.rotate((float)Math.PI/-8, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 5 , 0.5f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 50 , 0.75f, this);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/-2, 50 , 0.75f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -5 , 0.5f, this);
        sleep(time);
        rb.movement.rotate((float)Math.PI/8, ac, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/-2, 70, 0.75f, this);
        sleep(time);
 */