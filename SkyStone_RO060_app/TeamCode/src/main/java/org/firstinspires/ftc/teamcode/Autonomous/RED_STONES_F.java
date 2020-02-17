package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.IMU;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;
@Disabled
@Deprecated
@Autonomous (name = "RED_STONES_F", group = "RED")
public class RED_STONES_F extends LinearOpMode {

    private Robot rb = null;
    private final int time = 1;
    private Scanner scanner = new Scanner();
    private int scanResult = -1;
    private float stoneDist = 6f;
    private float ap = 10f;
    private float calibrateDist = 20f;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        initRobot(hardwareMap);

        FtcDashboard.getInstance().startCameraStream(scanner.getVuforia(), 0);

        while(!isStarted() && !isStopRequested()) {
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }

        telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());

        switch (scanResult) {
            case 2:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");

                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(time);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCM(3*(float)Math.PI/2,35,1f,this);
                sleep(time);
                rb.movement.rotateIMURelative(0, 1f, this);
                sleep(time);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //Catre bridge
                sleep(time);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.movement.moveCM(3*(float)Math.PI/2,130,1f,this); //Catre bridge
                sleep(time);
                break;
            case 0:
                telemetry.addLine("3 sau 6 pe zar skystone catre bridge");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(time);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2,25,1f,this);
                sleep(time);
                rb.movement.rotateIMURelative(0, 1f, this);
                sleep(time);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
//                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //Catre bridge
//                sleep(time);
//                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
//                sleep(time);
                rb.movement.moveCM(3*(float)Math.PI/2,105,1f,this); //Catre bridge
                sleep(time);

                break;
            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");

                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(time);

                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //Catre bridge
                sleep(time);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);
                sleep(time);
                rb.movement.moveCM(3*(float)Math.PI/2,75,1f,this); //Catre bridge


                break;
        }

        rb.movement.rotateIMUAbsolute(0, 0.4f, this);
        sleep(time);
        rb.movement.moveCM(3*(float)Math.PI/2,180,1f,this); // mergem in fata catre fundatie
        sleep(time);

        rb.movement.moveDist(5, rb.rightDist,1,this);  //
        // rb.movement.moveCM((float)Math.PI/3f,25,1f,this); //Catre fundatie

        sleep(time);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(400);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(200);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(time);

        rb.movement.moveCM(3*(float)Math.PI/2,40,1f,this); // mergem in fata catre fundatie
        sleep(time);

        rb.movement.rotateIMUAbsolute(0,1f,this);
        sleep(time);
        rb.movement.moveDist(1.5f, rb.rightDist,1,this);
        sleep(time);
        rb.movement.moveCM(0,10,0.5f,this); //Catre fundatie
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(450);
        rb.movement.moveCM((float) IMU.FORWARD_ANGLE, 20, 1f, this);
        sleep(time);

        rb.movement.moveCM((float)IMU.LEFTWARD_ANGLE, 20, 1f, this);
        sleep(time);

        rb.movement.moveCM(3*(float)Math.PI/4,120,.5f,this);
        sleep(time);

        rb.movement.rotate(-10*(float)Math.PI/15,1f,this);
        sleep(time);
        rb.movement.moveCM(0,50,1f,this);
        sleep(time);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(200);
        rb.movement.rotateIMUAbsolute(0,1f,this);
        sleep(time);
        rb.movement.moveCM(3*(float)Math.PI/2,30,1f,this);
        sleep(time);
/*        rb.movement.moveCM((float)Math.PI,20,1f,this);
        sleep(time);*/
        rb.movement.moveCM((float)Math.PI/2,130,1f,this);
        sleep(time);



        sleep(time);
        telemetry.update();






    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
    }
}
