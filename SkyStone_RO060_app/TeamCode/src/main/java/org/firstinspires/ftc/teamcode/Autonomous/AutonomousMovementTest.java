package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Robot;
/*@Disabled
@Deprecated*/
@Autonomous

public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;
    private Auto_StoneArm stoneArm = new Auto_StoneArm();

    double PoseX=0,PoseY=0,catchTarget=0;

    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
         float stoneDist = 11f; // distanta fata de stone pentru al putea colecta
         float ap = 20f;  // distanta mai mare fata de stone pentru a putea pregati bratul
         float calibrateDist = 22; // distanta fata de stone pentru a "putea calibra robotul" sa treaca sigur sub bridge safe
         float wallDist = 25; // distanta fata de perete [frontdist] pentru a colecta al doi-lea stone ca sa fim siguri ca il prindem
         int t = 1;

        initRobot();

        while(!isStarted()) idle();

        while(opModeIsActive()) {
/*            rb.movement.setPoseEstimate(new Pose2d(-42,64.5,0));
            rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
            sleep(t);
            rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare*/
/*            sleep(t);
            telemetry.addData("right", rb.rightDist.getDistanceCM());
            telemetry.addData("front", rb.frontDist.getDistanceCM());
            telemetry.addData("back", rb.backDist.getDistanceCM());
            telemetry.update();*/
            sleep(t);/*

/*            rb.movement.followTrajectorySync(
                    rb.movement.trajectoryBuilder()
                            .setReversed(false)
                            .splineTo(new Pose2d(35,58,Math.PI))
                            .build()
            );*/
/*            rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
            sleep(450);
            rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
            sleep(450);*/
            stop();
        }
        while(!isStopRequested()) idle();
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
    int t=1;
    public void catchStones()  {
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
        sleep(t);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
        sleep(250);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
        sleep(400);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
        sleep(t);

    }
    public void releaseStones(){
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(450);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(200);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(150);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
        sleep(t);
    }
}
