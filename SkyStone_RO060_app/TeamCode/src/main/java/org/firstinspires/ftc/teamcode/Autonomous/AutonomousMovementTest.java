package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
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



        while(opModeIsActive()) {
            telemetry.addData("Right Dist:", rb.rightDist.getDistanceCM());
            telemetry.addData("Front Dist:", rb.frontDist.getDistanceCM());
            telemetry.addData("Back Dist:", rb.backDist.getDistanceCM());
            telemetry.update();
        }
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
