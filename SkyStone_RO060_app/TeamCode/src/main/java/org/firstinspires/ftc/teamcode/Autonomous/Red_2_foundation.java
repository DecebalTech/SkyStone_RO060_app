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

@Autonomous (name = "Red_2_Foundation", group = "RED")
public class Red_2_foundation extends LinearOpMode {

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
    static double compOffset =1.12; // multiplier

    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);
        voltComp = ((rb.controlHub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS)-13.7)*compOffset);


        //SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);

//        waitForStart();

        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scanFirstTwo(this);
            scanResult = (temp == -1 ? scanResult : temp);
            telemetry.addData("Result:", scanResult);
            telemetry.update();
        }

        scanner.stopTfod();

        //scanResult = scanner.scanWithAverage(this);

        telemetry.addData("scan result", scanResult);

        rb.movement.setPoseEstimate(new Pose2d(-28,-64,Math.PI));


//        scanResult=2;

        switch (scanResult) {
            case 0:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-43.3,-30.5-voltComp))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                               // .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(0,-36.5,Math.PI))
                                .splineTo(new Pose2d(40,-29-voltComp,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();


                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-36,Math.PI))
                                .splineTo(new Pose2d(-40,-36,Math.PI))
                                .build()

                );
                rb.prindereCub.SetDirection(PrindereCub.Direction.inAuto); // pornim intake-ul

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .splineTo(new Pose2d(-65,-20.5-voltComp,Math.PI*3/4))
/*                                .setReversed(true)
                                .lineTo(new Vector2d(-50,-21))*/

                                .build()

                );
              //  rb.movement.setPoseEstimate(new Pose2d(-62,-21,Math.PI));

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(-40,-34,Math.PI))
                                .splineTo(new Pose2d(0,-34,Math.PI))
                                .splineTo(new Pose2d(45,-34,Math.PI/2*3))
                                .build()
                );

                rb.prindereCub.SetDirection(PrindereCub.Direction.OUT); // pornim intake-ul
                sleep(t);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(1);
                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);

                if(rb.backDist.getDistanceCM()<35&&rb.backDist.getDistanceCM()>0){
                    sleep(t);
                    rb.movement.moveDist(4.7f,rb.backDist,1f,this);
                    sleep(1);

                }
                else{
                    rb.movement.followTrajectorySync(
                            rb.movement.trajectoryBuilder()
                                    .setReversed(true)
                                    .lineTo(new Vector2d(45,-26))
                                    .build()
                    );
                }

                rb.prindereCub.SetDirection(PrindereCub.Direction.STOP); // pornim intake-ul
                sleep(t);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);
                rb.movement.setPoseEstimate(new Pose2d(45,-31,Math.PI/-2));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(40,-55))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(43,-42,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .splineTo(new Pose2d(25,-36,Math.PI))
                                .splineTo(new Pose2d(4,-36,Math.PI))
                                .build()
                );
                break;

            case 2: //requires work
                telemetry.addLine("3 sau 6 pe zar skystone spre bridge");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-27,-30.5-voltComp))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .splineTo(new Pose2d(7,-35.5,Math.PI))
                                .splineTo(new Pose2d(37,-30,Math.PI))
                                .strafeTo(new Vector2d(38,-29.3-voltComp))
                                .setReversed(false)
                                .build()
                );
                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-35,Math.PI))
                                .splineTo(new Pose2d(-50,-32,Math.PI))
                                .build()

                );
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .strafeTo(new Vector2d(-52.5,-30-voltComp))
                                .build()
                );
                catchStones();
              //  rb.movement.setPoseEstimate(new Pose2d(-48,-31.3,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                               // .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(5,-36,Math.PI))
                                .splineTo(new Pose2d(55,-29-voltComp,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(53,-33))
                                .build()
                );


//////

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(1);
                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);

                if(rb.backDist.getDistanceCM()<30&&rb.backDist.getDistanceCM()>0){
                    sleep(t);
                    rb.movement.moveDist(4.7f,rb.backDist,1f,this);
                    sleep(1);

                }
                else{
                    rb.movement.followTrajectorySync(
                            rb.movement.trajectoryBuilder()
                                    .setReversed(true)
                                    .lineTo(new Vector2d(45,-31))
                                    .build()
                    );
                }
                rb.movement.setPoseEstimate(new Pose2d(45,-31,Math.PI/-2));
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);


                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(40,-55))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(43,-44,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                //.splineTo(new Pose2d(25,-42,Math.PI))
                                .strafeTo(new Vector2d(25,-37.5))
                                .splineTo(new Pose2d(8,-37.5,Math.PI))
                                .build()
                );

                break;

            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-35,-30.5-voltComp))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(7,-37.5,Math.PI))
                                .splineTo(new Pose2d(40,-31.5-voltComp,Math.PI))
                                .setReversed(false)
                                .build()
                );

                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-35,Math.PI))
                                .splineTo(new Pose2d(-62,-33,Math.PI))
                                .build()

                );
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .strafeTo(new Vector2d(-63,-30-voltComp))
                                .build()
                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(5,-38,Math.PI))
                                .splineTo(new Pose2d(55,-33-voltComp,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(50,-32.5))
                                .build()
                );


//////

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(1);
                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);

                if(rb.backDist.getDistanceCM()<30&&rb.backDist.getDistanceCM()>0){
                    sleep(t);
                    rb.movement.moveDist(4.7f,rb.backDist,1f,this);
                    sleep(1);

                }
                else{
                    rb.movement.followTrajectorySync(
                            rb.movement.trajectoryBuilder()
                                    .setReversed(true)
                                    .lineTo(new Vector2d(45,-31))
                                    .build()
                    );
                }
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);
                rb.movement.setPoseEstimate(new Pose2d(45,-31,Math.PI/-2));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(40,-55))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(43,-41.5,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                               // .splineTo(new Pose2d(25,-37,Math.PI))
                                .strafeTo(new Vector2d(25,-35))
                                .splineTo(new Pose2d(7,-35,Math.PI))
                                .build()
                );

                break;
        }
return ;
    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();

        //rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
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
        sleep(400);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
        sleep(400);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
        sleep(100);

    }
    public void releaseStones(){
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(200);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(50);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(100);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // incepem sa inchidem ghiara
        sleep(t);
    }
}