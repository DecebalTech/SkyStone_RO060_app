package org.firstinspires.ftc.teamcode.OldAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "BLUE FOUNDATION_STONES - foundation, 2 stones, park")
@Disabled
public class BLUE_FOUNDATION_STONES extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException{
        int time=1,gtime=600;
        float ac=.45f;
        initRobot();

        while(!isStarted()) idle();
        rb.movement.moveCM((float)Math.PI+((float)Math.PI*(1-1/1.08f)), -118 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI+((float)Math.PI*(1-1/1.08f)), -20, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/3, -100 , 1f, this); //strafe cu placa
        sleep(time);
        rb.movement.rotate((float)Math.PI/1.77f, ac, this);
        //rb.movement.rotateIMU(-Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 30 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -110 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 30 , 0.8f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(Math.PI+Math.PI/28, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.95f, 200 , 1f, this); //130
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 55 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 26 , 0.45f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -55 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
        //rb.movement.rotate((float)Math.PI, 1f, this);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
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
        rb.movement.rotateIMUAbsolute(Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 110 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 60 , 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 25 , 0.7f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -45 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.8f, -230 , 1f, this);
        sleep(time);
        //de aici
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
        rb.prindereCub.SetDirection(PrindereCub.Direction.OUT);
        sleep(time);
        //pana aici
        rb.movement.moveCM((float)Math.PI/1.5f, -30 , 1f, this);
        //si aici
        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
