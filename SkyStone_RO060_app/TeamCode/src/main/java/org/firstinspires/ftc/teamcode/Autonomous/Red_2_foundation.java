package org.firstinspires.ftc.teamcode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.heading.SplineInterpolator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Modules.autoRotate;
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
    static double compOffset =1.13; // multiplier

    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);
        sleep(1);
        voltComp = ((rb.controlHub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS)-13.7)*compOffset);


        //SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);

//        waitForStart();

        //ElapsedTime elapsedTime = new ElapsedTime();

        while(!isStarted() && !isStopRequested()) {
          //  initRobot(hardwareMap);
            if(isStopRequested()) break;
            int temp = scanner.scanFirstTwo(this);
            scanResult = (temp == -1 ? scanResult : temp);
            telemetry.addData("Result:", scanResult);
            telemetry.update();

/*            RESET TFOD EVERY N SECS IT SCANS -1
            if(elapsedTime.seconds()==5 && temp==-1 && !isStopRequested()) {
                scanner.setCropping(new Scanner.ClippingMargins(640, 0, 0, 0));
                sleep(100);
                scanner.setCropping(scanner.TWO_STONES_MARGINS);
                elapsedTime.reset();
            }*/
        }

        scanner.stopTfod();

        //scanResult = scanner.scanWithAverage(this);

        telemetry.addData("scan result", scanResult);

        rb.movement.setPoseEstimate(new Pose2d(-28,-64,Math.PI));


       scanResult=0;
        rb.autoRotate.armSetPosition(autoRotate.armPositions.forward);
        sleep(1);
        switch (scanResult) {
            case 0:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-45,-30-voltComp))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .splineTo(new Pose2d(7,-35,Math.PI))
                                .splineTo(new Pose2d(43+voltComp,-30.3,Math.PI))

                                .build()
                );
                rb.autoRotate.armSetPosition(autoRotate.armPositions.side);
                sleep(50);
                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-33,Math.PI))
                                .splineTo(new Pose2d(-57,-33,Math.PI),new SplineInterpolator(Math.PI,Math.PI*3/2))
                                .setReversed(true)
                                .lineTo(new Vector2d(-57,-23+voltComp))
                                .strafeTo(new Vector2d(-54,-21+voltComp))
                                .build()

                );

                //rb.movement.setPoseEstimate(new Pose2d(-53,-34,(float)Math.PI*3/2));


                rb.autoRotate.armSetPosition(autoRotate.armPositions.side);
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
                sleep(t);
                SpecialcatchStones();
                rb.autoRotate.armSetPosition(autoRotate.armPositions.forward);
                sleep(t);
              //  rb.movement.setPoseEstimate(new Pose2d(-47,-25,(float)Math.PI*3/2));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .splineTo(new Pose2d(-30,-35,0))
                                .splineTo(new Pose2d(40,-35,0),new SplineInterpolator(0,Math.PI/2))
                              ////
                              /*  .setReversed(false)
                                .splineTo(new Pose2d(40,-30.5,Math.PI))*/
                               // .splineTo(new Pose2d(43.25,-30.5,0))

                                .build()
                );
//rb.movement.rotateIMUAbsolute(0,1f,this);
               // rb.movement.setPoseEstimate(new Pose2d(0,-35,Math.PI));
/*
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(40,-30.5,Math.PI))

                                //.strafeTo(new Vector2d(39,-28.5))
                                .build()
                );
*/

                rb.autoRotate.armSetPosition(autoRotate.armPositions.side);
                sleep(150);
                releaseStones();

             //   rb.movement.setPoseEstimate(new Pose2d(-15,-40,(float)Math.PI*3/2));
/*                rb.prindereCub.SetDirection(PrindereCub.Direction.IN); // pornim intake-ul

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .splineTo(new Pose2d(-65,-20-voltComp,Math.PI*3/4))
*//*                                .setReversed(true)
                                .lineTo(new Vector2d(-50,-21))*//*

                                .build()

                );*/

              //  rb.movement.setPoseEstimate(new Pose2d(-62,-21,Math.PI));
                //.splineTo(new Pose2d(25,41,Math.PI)),new SplineInterpolator((x,y));
/*
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.inAuto);
                sleep(t);
                rb.movement.rotateIMUAbsolute(Math.PI,1f,this);
                sleep(t);

                rb.movement.setPoseEstimate(new Pose2d(-65,-20-voltComp,0));

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                             //   .splineTo(new Pose2d(-60,-28,Math.PI),new SplineInterpolator(Math.PI,0)) ////// ASTA este cea mai mare smecherie bro
                                .splineTo(new Pose2d(0,-32,0))
                                .splineTo(new Pose2d(45,-32,Math.PI/2*3))
                                .build()
                );
*/

           /*     rb.prindereCub.SetDirection(PrindereCub.Direction.OUT); // pornim intake-ul
                sleep(t);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(1);
                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);

                if(rb.backDist.getDistanceCM()<35&&rb.backDist.getDistanceCM()>0){
                    sleep(t);
                    rb.movement.moveDist(4.7f,rb.backDist,1f,this);
                    sleep(1);

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
                    rb.movement.setPoseEstimate(new Pose2d(43,-38,Math.PI));

                }
                else{
                    rb.movement.followTrajectorySync(
                            rb.movement.trajectoryBuilder()
                                    .setReversed(true)
                                    .lineTo(new Vector2d(45,-24.5))
                                    .build()
                    );
                    rb.prindereCub.SetDirection(PrindereCub.Direction.STOP); // pornim intake-ul
                    sleep(t);
                    rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                    sleep(450);
                    rb.movement.setPoseEstimate(new Pose2d(45,-31,Math.PI/-2));
                    rb.movement.followTrajectorySync(
                            rb.movement.trajectoryBuilder()
                                    .setReversed(false)
                                    .strafeTo(new Vector2d(40,-58))
                                    .build()
                    );
                    rb.movement.rotateIMUAbsolute(0,1f,this);
                    sleep(t);

                    rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                    sleep(t);
                    rb.movement.setPoseEstimate(new Pose2d(43,-38,Math.PI));
                }


                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(40,-33))
                                .splineTo(new Pose2d(25,-33,Math.PI))
                                .splineTo(new Pose2d(4,-33,Math.PI))
                                .build()
                );*/
                break;

            case 2: //requires work
                telemetry.addLine("3 sau 6 pe zar skystone spre bridge");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-29,-31-voltComp))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                //.splineTo(new Pose2d(-22,-38,Math.PI))
                                .splineTo(new Pose2d(7,-35.5,Math.PI))
                                .splineTo(new Pose2d(43,-30,Math.PI))

                                .build()
                );
                rb.autoRotate.armSetPosition(autoRotate.armPositions.side);
                sleep(100);
                releaseStones();

     rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-33,Math.PI))
                                .splineTo(new Pose2d(-50,-32,Math.PI))
                                .build()

                );
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .strafeTo(new Vector2d(-54,-29.7-voltComp))
                                .build()
                );
                catchStones();

              //  rb.movement.setPoseEstimate(new Pose2d(-48,-31.3,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(7,-35,Math.PI))
                                .splineTo(new Pose2d(36.05,-30,Math.PI))


                                .build()
                );
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(1);
                rb.autoRotate.armSetPosition(autoRotate.armPositions.side);
                sleep(100);

                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(55,-29))
                                .build()
                );


                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);


                // !!!!!!!!! MOVEDIST SHALL BE DEPRECATED [ NOT RELIABLE !!!!! }
/*                if(rb.backDist.getDistanceCM()<30&&rb.backDist.getDistanceCM()>0){
                    sleep(t);
                    rb.movement.moveDist(4.7f,rb.backDist,1f,this);
                    sleep(1);
                    rb.movement.setPoseEstimate(new Pose2d(45,-31,Math.PI/-2));

                }
                else{
                    rb.movement.followTrajectorySync(
                            rb.movement.trajectoryBuilder()
                                    .setReversed(true)
                                    .lineTo(new Vector2d(45,-24))
                                    .build()
                    );
                }*/
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .lineTo(new Vector2d(55,-22))
                                .build()
                );
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);


                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .splineTo(new Pose2d(40,-50,(float)Math.PI/1.25f))
                                .build()
                );
 /*               rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(1);*/

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(47,-60,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                //.splineTo(new Pose2d(25,-42,Math.PI))
                                .strafeTo(new Vector2d(38,-33))
                                .splineTo(new Pose2d(8,-34,Math.PI))
                                .build()
                );
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(t);
                break;

            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
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
                                .splineTo(new Pose2d(0,-36,Math.PI))
                                .splineTo(new Pose2d(7,-36,Math.PI))
                                .splineTo(new Pose2d(37,-30,Math.PI))
                                .strafeTo(new Vector2d(38,-28.8-voltComp))
                                .setReversed(false)
                                .build()
                );

                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-35,Math.PI))
                                .splineTo(new Pose2d(-62,-35,Math.PI))
                                .build()

                );
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
                sleep(t);
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .strafeTo(new Vector2d(-62.5,-29-voltComp))
                                .build()
                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(7,-35,Math.PI))
                                .splineTo(new Pose2d(38,-30,Math.PI))
                                .strafeTo(new Vector2d(39,-28.5-voltComp))
                                .setReversed(false)
                                .build()
                );

                releaseStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(49,-31))
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
                                    .lineTo(new Vector2d(45,-25))
                                    .build()
                    );
                    rb.movement.setPoseEstimate(new Pose2d(49,-31,Math.PI/2));
                }
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);
                rb.movement.setPoseEstimate(new Pose2d(45,-31,Math.PI/-2));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .strafeTo(new Vector2d(38,-60))
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
                                .strafeTo(new Vector2d(35,-35))
                                .splineTo(new Pose2d(7,-35,Math.PI))
                                .build()
                );

                break;
        }
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CLOSED); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(1);
return ;
    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();
        //rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
    }
    public void catchFirstStone()  {

/*        if(rb.rightDist.getDistanceCM()>32)
            return;
        sleep(t);*/

/*        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
        sleep(t);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
        sleep(200);

 */
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
        sleep(450);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
        sleep(50);

    }
    public void catchStones()  {

/*        if(rb.rightDist.getDistanceCM()>32)
            return;
        sleep(t);*/
sleep(1);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
        sleep(t);
       // rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
        sleep(70);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
        sleep(120);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
        sleep(1);

    }
    public void SpecialcatchStones()  {

/*        if(rb.rightDist.getDistanceCM()>32)
            return;
        sleep(t);*/
        sleep(1);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
        sleep(t);
        // rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.autoCatch); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
        sleep(300);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
        sleep(120);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
        sleep(1);

    }
    public void releaseStones(){
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(100);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(1);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(200);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(1);

        rb.autoRotate.armSetPosition(autoRotate.armPositions.forward);
        sleep(1);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
        sleep(t);
        sleep(1);
    }
}