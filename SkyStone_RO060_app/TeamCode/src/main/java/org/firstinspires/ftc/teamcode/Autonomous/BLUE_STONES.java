package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.IMU;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "BLUE_STONES", group = "RED")
public class BLUE_STONES extends LinearOpMode {

    private Robot rb = null;
    private final int t = 1;
    private Scanner scanner = new Scanner();
    private int scanResult = -1;
    private float stoneDist = 11f;
    private float ap = 20f;
    private float calibrateDist = 25;
    private float wallDist = 25;


    @Override
    public void runOpMode() throws InterruptedException {
       // telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        initRobot(hardwareMap);

        //FtcDashboard.getInstance().startCameraStream(scanner.getVuforia(), 0);

        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
            telemetry.addData("Scan Result", scanResult);
        }

        telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
        scanResult = -1;
        switch (scanResult) {
            case 2:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI,15,1,this); //Catre bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,40,0.5f,this); //Catre bridge
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);
                rb.movement.moveDist(15, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);

                rb.movement.moveCM((float)Math.PI/2,160,1f,this); //Catre bridge


                break;
/*
            case 0:
                telemetry.addLine("1 sau 4 pe zar skystone catre perete");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,60,1f,this);//40
                sleep(t);
                rb.movement.rotateIMURelative(0, 1f, this);
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //Catre bridge
                sleep(t);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,190,1f,this); //Catre bridge
                sleep(t);


                break;*/
            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,20,1f,this); //Catre bridge
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.moveCM((float)Math.PI/2,40,1f,this); //Catre bridge
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveDist(17, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);

                rb.movement.moveCM((float)Math.PI/2,120,1f,this); //Catre bridge


                break;
        }

        rb.movement.rotateIMUAbsolute(0, 0.4f, this);
        sleep(t);



        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(400);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(t);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(100);

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
        sleep(t);

        rb.movement.moveCM(3*(float)Math.PI/2,45,1f,this); // mergem dupa alt skystone
        sleep(t);
        rb.movement.rotateIMUAbsolute(0,1f,this);
        sleep(t);  


        switch (scanResult) {
            case 2:
                telemetry.addLine("1 sau 4 pe zar skystone la bridge");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,185,1f,this); //Catre bridge
                sleep(t);
                rb.movement.moveDist(7, rb.backDist,1,this);  // mergem la 9 cm fata de skystone
                //rb.movement.moveCM((float)IMU.BACKWARD_ANGLE, 30, .3f, this);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,25,1f,this); //Catre bridge
                sleep(t);
                rb.movement.moveDist(15, rb.rightDist,1,this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,285,1f,this); //Catre bridge
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // initializare
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
                sleep(200);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
                sleep(200);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,170,1f,this); //Catre bridge
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // incepem sa inchidem ghiara
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,1,this); //Catre bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,140,1f,this); //Catre bridge
                sleep(t);
                rb.movement.rotate((float)Math.PI/2,1f,this);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // incepem sa inchidem ghiara
                sleep(t);
                rb.movement.rotate(-1*(float)Math.PI/2,1f,this);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,40,1f,this); //Catre bridge
                sleep(t);
                break;

 /*           case 0:
                telemetry.addLine("1 sau 4 pe zar skystone catre perete");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,200,1f,this); //Catre bridge
                sleep(t);
                rb.movement.moveDist(wallDist, rb.frontDist,1,this);
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);
                sleep(t);
                rb.movement.moveCM(0,70,1f,this); //Catre bridge
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,35,.4f,this); //Catre bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI,60,0.6f,this); //Catre bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,35,.4f,this); //Catre bridge
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
                sleep(t);
                rb.movement.rotateIMUAbsolute(Math.PI, 0.7f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,215,1f,this); //Catre bridge
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.OUT);
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,50,1f,this); //Catre bridge
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,.3f,this); //Catre bridge
                sleep(t);

                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                break;*/
            default:

                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,230,1f,this); //Catre bridge
                sleep(t);

                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);
                rb.movement.moveDist(25, rb.backDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,25,1f,this); //Catre bridge
                sleep(t);
                rb.movement.moveDist(15, rb.rightDist,1,this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,265,1f,this); //Catre bridge
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // initializare
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
                sleep(200);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
                sleep(200);

                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,170,1f,this); //Catre bridge
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // incepem sa inchidem ghiara
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la 9 cm fata de skystone
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,1,this); //Catre bridge
                sleep(t);

                rb.movement.moveCM((float)Math.PI/2,140,1f,this); //Catre bridge
                sleep(t);
                rb.movement.rotate((float)Math.PI/2,1f,this);
                sleep(t);

                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // incepem sa inchidem ghiara
                sleep(t);

                rb.movement.rotate(-1*(float)Math.PI/2,1f,this);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,40,1f,this); //Catre bridge
                sleep(t);

                break;
        }

        sleep(t);
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
