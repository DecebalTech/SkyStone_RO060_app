package org.firstinspires.ftc.teamcode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;
import org.openftc.revextensions2.ExpansionHubEx;

@Autonomous (name = "Blue_2_Foundation", group = "RED")
public class Blue_2_foundation extends LinearOpMode {

    /*
     */

    private Robot rb = null;
    private final int t = 1; // pauza intre executarea unei noi functii
    private Scanner scanner = new Scanner(); // definire vuforia
    private int scanResult = -1; // valoare initiala pentru rezultatul scanrii
    private float stoneDist = 10.2f; // distanta fata de stone pentru al putea colecta
    private float ap = 13;  // distanta mai mare fata de stone pentru a putea pregati bratul
    private float calibrateDist = 22; // distanta fata de stone pentru a "putea calibra robotul" sa treaca sigur sub bridge safe
    private float wallDist = 25; // distanta fata de perete [frontdist] pentru a colecta al doi-lea stone ca sa fim siguri ca il prindem
    private double voltComp =0;
    static double compOffset =1;

    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);



        //SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);

//        waitForStart();

        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }
        voltComp = (rb.controlHub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS)-14-compOffset);

        rb.movement.setPoseEstimate(new Pose2d(-42,64.5,0));


        scanResult=2;

        switch (scanResult) {
            case 2:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-40,30.6))
                                .build()

                );
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-40,30+voltComp))
                                .build()

                );

                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .splineTo(new Pose2d(0,35.3,0))
                                .splineTo(new Pose2d(7,35.3,0))
                                .splineTo(new Pose2d(39,29+voltComp,0))
                                .build()
                );
                releaseStones();
                ///
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(7,35.5,0))
                                .splineTo(new Pose2d(-63.5,32+voltComp,0))
                                .build()

                );
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .strafeTo(new Vector2d(-63.5,29))
                                .build()
                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .strafeTo(new Vector2d(-42,35.3))
                                .splineTo(new Pose2d(7,35.3,0))
                                .splineTo(new Pose2d(62,29,0))
                                .build()
                );

                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .lineTo(new Vector2d(50,29))
                                .build()
                );


//////
                rb.movement.setPoseEstimate(new Pose2d(50,29,0));

                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);
                rb.movement.setPoseEstimate(new Pose2d(50,29,Math.PI/2));

                rb.movement.moveDist(4.7f,rb.backDist,1f,this);
                sleep(1);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);
                rb.movement.setPoseEstimate(new Pose2d(55,28,Math.PI/2));

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(47,50))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(Math.PI,1f,this);
                sleep(t);
//                rb.movement.setPoseEstimate(new Pose2d(47,52,Math.PI/2));
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);

                rb.movement.setPoseEstimate(new Pose2d(47,47,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .splineTo(new Pose2d(25,43,Math.PI))
                                .splineTo(new Pose2d(8,43,Math.PI))
                                .build()
                );

                break;

            case 0: //requires work
                telemetry.addLine("3 sau 6 pe zar skystone catre perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-26.7,-31.3))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .splineTo(new Pose2d(7,-38,Math.PI))
                                .splineTo(new Pose2d(37,-31.5,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-36,Math.PI))
                                .splineTo(new Pose2d(-48,-30,Math.PI))
                                .build()

                );
                catchStones();
                rb.movement.setPoseEstimate(new Pose2d(-48,-31.3,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                               // .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(5,-38,Math.PI))
                                .splineTo(new Pose2d(60,-31.5,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .lineTo(new Vector2d(53,-31))
                                .build()
                );


//////

                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);
                rb.movement.moveDist(4.5f,rb.backDist,1f,this);
                sleep(1);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .lineTo(new Vector2d(45,-58))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(43,-46,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .splineTo(new Pose2d(25,-39,Math.PI))
                                .splineTo(new Pose2d(8,-39,Math.PI))
                                .build()
                );

                break;

            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-35,-31.3))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                               // .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(7,-38,Math.PI))
                                .splineTo(new Pose2d(37,-31.3+voltComp,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-36,Math.PI))
                                .splineTo(new Pose2d(-62.5,-29.5,Math.PI))
                                .build()

                );
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                         .strafeTo(new Vector2d(-62,-29))
                                .build()

                );
                catchStones();
                rb.movement.setPoseEstimate(new Pose2d(-62,-31.3,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(5,-37,Math.PI))
                                .splineTo(new Pose2d(60,-29.5+voltComp,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .lineTo(new Vector2d(53,-29.5))
                                .build()
                );


//////

                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);
                rb.movement.moveDist(5f,rb.backDist,1f,this);
                sleep(1);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .lineTo(new Vector2d(52,-50))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(52,-50,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .splineTo(new Pose2d(25,-37,Math.PI))
                                .splineTo(new Pose2d(2,-37,Math.PI))
                                .build()
                );

                break;
        }

    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();
/*
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);*/
    }
    public void catchStones()  {

/*        if(rb.rightDist.getDistanceCM()>32)
            return;
        sleep(t);*/

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
        sleep(t);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
        sleep(350);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
        sleep(350);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
        sleep(50);

    }
    public void releaseStones(){
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(350);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(100);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(50);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // incepem sa inchidem ghiara
        sleep(t);
    }
}
