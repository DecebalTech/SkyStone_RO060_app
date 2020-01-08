package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
@Disabled
public class AutonomousMovement extends LinearOpMode {

    private Robot rb;

    public void runOpMode() throws InterruptedException{

        while(!isStarted()){

        }

        initRobot();
        /*
        rb.movement.moveForwards(-0.5f, 500); // se indeparteaza de perete
        sleep(1000);
        rb.movement.rotate(0.5f, -1000); // se roteste catre locul de parcare
        sleep(1000);
        rb.movement.moveForwards(0.5f,500); // se parcheaza
        sleep(5000);
        */

    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}