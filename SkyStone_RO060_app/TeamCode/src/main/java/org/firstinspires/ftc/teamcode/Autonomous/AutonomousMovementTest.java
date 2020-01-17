package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;
    private Auto_StoneArm stoneArm = new Auto_StoneArm();

    public void runOpMode() throws InterruptedException{
        initRobot();
        int time=1;
        while(!isStarted()) idle();

        telemetry.addData("TickPerCm", rb.movement.getTickPerCm());
        telemetry.update();

        sleep(5000);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
