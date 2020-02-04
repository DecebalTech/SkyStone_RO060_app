package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Robot;
/*@Disabled
@Deprecated*/
@Autonomous
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;
    private Auto_StoneArm stoneArm = new Auto_StoneArm();

    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        initRobot();

        int time=1;
         int scanResult = -1;
         float stoneDist = 8f;
         float ap = 10f;
         float calibrateDist = 20f;
        float wallDist = 20;
        while(!isStarted()) idle();
        telemetry.addData("Left", rb.odm.getLeftDist());
        telemetry.addData("Right", rb.odm.getRightDist());
        telemetry.addData("Back", rb.odm.getBackDist());
        telemetry.update();


        rb.movement.moveCM((float)Math.PI/2, 100, .5f, this);
        sleep(time);
        telemetry.addData("Left", rb.odm.getLeftDistCM());
        telemetry.addData("Right", rb.odm.getRightDistCM());
        telemetry.addData("Back", rb.odm.getBackDistCM());
        telemetry.update();

        while(!isStopRequested()) idle();
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
