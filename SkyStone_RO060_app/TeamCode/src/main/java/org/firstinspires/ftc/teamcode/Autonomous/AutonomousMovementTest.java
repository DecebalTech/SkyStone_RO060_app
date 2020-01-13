package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;
    private Auto_StoneArm stoneArm = new Auto_StoneArm();

    public void runOpMode() throws InterruptedException{
        initRobot();
        int time=300;
        while(!isStarted()) idle();

        /*while(opModeIsActive()){
            stoneArm.Update(gamepad1);
        }*/
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 100, 1f, this);
        sleep(time);
        rb.movement.rotateIMURelative(Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 100, 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
