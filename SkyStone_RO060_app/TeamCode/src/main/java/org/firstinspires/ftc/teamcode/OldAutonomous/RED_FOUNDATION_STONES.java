package org.firstinspires.ftc.teamcode.OldAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED FOUNDATION - foundation, 2 stones, park")
@Disabled
public class RED_FOUNDATION_STONES extends LinearOpMode {

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
        rb.movement.moveCM((float)Math.PI/2, 20 , 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.95f, 200 , 1f, this); //130
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -65 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 26 , 0.45f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 65 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
        //rb.movement.rotate((float)Math.PI, 1f, this);
        rb.movement.rotateIMURelative(Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 140 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.OUT);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -95 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
        //rb.movement.rotate((float)Math.PI, 1f, this);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 110 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -60 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 25 , 0.7f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 50 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -230 , 1f, this);
        sleep(time);
        //de aici
        rb.movement.rotateIMUAbsolute(Math.PI, 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.OUT);
        sleep(time*10);
        //pana aici
        rb.movement.moveCM((float)Math.PI/2.5f, -40 , 1f, this);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP); //si asta
        sleep(time);

    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}

