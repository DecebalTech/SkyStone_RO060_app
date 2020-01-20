package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Robot;
@Disabled
@Deprecated
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



        rb.movement.moveCM((float)Math.PI,-40,1f,this);
        sleep(time);
        rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
