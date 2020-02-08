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
         float stoneDist = 11f; // distanta fata de stone pentru al putea colecta
         float ap = 20f;  // distanta mai mare fata de stone pentru a putea pregati bratul
         float calibrateDist = 22; // distanta fata de stone pentru a "putea calibra robotul" sa treaca sigur sub bridge safe
         float wallDist = 25; // distanta fata de perete [frontdist] pentru a colecta al doi-lea stone ca sa fim siguri ca il prindem
         int t = 1;

        initRobot();

        while(!isStarted()) idle();


        /*
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
        */

/*        rb.movement.moveCM_ramped(Math.PI, 200, .4f, this);
        sleep(100);
        rb.movement.moveCM_ramped(3*Math.PI/2, 150, 1f, this);
        sleep(100);
        rb.movement.moveCM_ramped(0, 50, .5f, this);
        sleep(100);
        rb.movement.moveCM_ramped(Math.PI/2, 100, .8f, this);
        sleep(100);
        rb.movement.moveCM_ramped(0, 30, .8f, this);*/

        //*auto initilization*//

        sleep(t);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO);
        sleep(t);

        //*auto initilization*//
        rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la stoneDist cm fata de skystone
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // lasam bratul jos
        sleep(t);
        rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stoneDist cm fata de skystone
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
        sleep(t);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // colectam skystone
        sleep(300);

        rb.movement.moveCM((float)Math.PI,15,.8f,this); // facem un strafe catre perete sa fim siguri ca nu lovim podul
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone
        sleep(t);
        rb.movement.rotateIMUAbsolute(0,1f,this);
        sleep(t);
        rb.movement.moveCM_ramped(3*(float)Math.PI/2,270,1f,this); //mergem sa colectam pucte :)
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // lasam bratul jos
        sleep(200);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // lasam bratul jos
        sleep(t);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // colectam skystone
        sleep(100);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // colectam skystone
        sleep(t);
        rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
        sleep(t);
        rb.movement.moveCM_ramped(3*(float)Math.PI/2,10,1f,this); //mergem sa colectam pucte :)
        sleep(t);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(t);
        rb.movement.moveCM_ramped(3*(float)Math.PI/2,10,1f,this); //mergem sa colectam pucte :)
        sleep(t);
        rb.movement.moveCM_ramped((float)Math.PI/2,70,1f,this); //mergem sa colectam pucte :)
        sleep(t);
        rb.movement.rotateIMUAbsolute(0,1f,this);
        sleep(t);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // lasam bratul jos
        sleep(t);
        rb.movement.moveCM_ramped((float)Math.PI/2,280,1f,this); //mergem sa colectam pucte :)
        sleep(t);

        while(!isStopRequested()) idle();
    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this, true);
    }
}
