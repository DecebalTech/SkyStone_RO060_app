package org.firstinspires.ftc.teamcode.Autonomous;

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
        initRobot();
        int time=1;
         int scanResult = -1;
         float stoneDist = 8f;
         float ap = 10f;
         float calibrateDist = 20f;
        while(!isStarted()) idle();

//        telemetry.addData("TickPerCm", rb.movement.getTickPerCm());
//        telemetry.update();
//
//
//        telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
//        sleep(time);
//
//        rb.movement.rotate(-10*(float)Math.PI/13,1f,this);
//        sleep(time);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(500);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(500);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(1000);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(1500);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(2500);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(4000);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
