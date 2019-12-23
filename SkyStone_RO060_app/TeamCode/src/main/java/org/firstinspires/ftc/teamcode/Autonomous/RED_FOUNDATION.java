package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED FOUNDATION")
public class RED_FOUNDATION extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot

        while(!isStarted()) {
            idle();
        }

        rb.movement.moveCM((float)Math.PI/2, 80, 1f, this);
        sleep(1000);
        rb.movement.rotate((float)Math.PI, 1f, this);
        sleep(1000);
        //rb.movement.moveCM((float)Math.PI, 42, 1f, this);
        rb.movement.moveTICKS((float)Math.PI, 1110, 1f, this);
        sleep(1000);
        rb.movement.moveCM((3 * (float) Math.PI) / 2, 20, 1f, this);
        sleep(1000);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(1000);

        rb.movement.moveCM((float)Math.PI/2, 80, 1f, this);
        sleep(1000);
        rb.movement.rotate(-5* (float)Math.PI/6, 1f, this);
        sleep(1000);
        rb.movement.moveCM((3 * (float) Math.PI) / 2, 50, 1f, this);
        sleep(1000);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(1000);

        rb.movement.moveCM((float)Math.PI/2, 100, 1f, this);
        sleep(1000);


    }
}
