package org.firstinspires.ftc.teamcode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveREV;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;

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
        rb.movement.setPoseEstimate(new Pose2d(-28,-64,Math.PI));


        scanResult=2;

        switch (scanResult) {
            case 2:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-43.3,-31.3))
                                .build()

                );
                catchStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                               // .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(5,-38,Math.PI))
                                .splineTo(new Pose2d(37,-30.5,Math.PI))
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
                                .splineTo(new Pose2d(-65.5,-21,Math.PI*3/4))
/*                                .lineTo(new Vector2d(-50,-21))*/

                                .build()

                );
              //  rb.movement.setPoseEstimate(new Pose2d(-62,-21,Math.PI));

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(true)
                                .splineTo(new Pose2d(-40,-36,Math.PI))
                                .splineTo(new Pose2d(25,-36,Math.PI))
                                .splineTo(new Pose2d(45,-34,Math.PI/2*3))
                                .build()
                );

                rb.prindereCub.SetDirection(PrindereCub.Direction.OUT); // pornim intake-ul
                sleep(t);
                rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
                sleep(1);
                rb.movement.moveDist(4.5f,rb.backDist,1f,this);
                sleep(1);
                rb.prindereCub.SetDirection(PrindereCub.Direction.STOP); // pornim intake-ul
                sleep(t);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(450);

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .lineTo(new Vector2d(40,-58))
                                .build()
                );
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(t);
                rb.movement.setPoseEstimate(new Pose2d(43,-38,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .setReversed(false)
                                .splineTo(new Pose2d(25,-36,Math.PI))
                                .splineTo(new Pose2d(4,-36,Math.PI))
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
                                .splineTo(new Pose2d(37,-31.3,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();

                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()

                                .splineTo(new Pose2d(5,-36,Math.PI))
                                .splineTo(new Pose2d(-62,-29.5,Math.PI))
                                .build()

                );
                catchStones();
                rb.movement.setPoseEstimate(new Pose2d(-62,-31.3,Math.PI));
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .strafeTo(new Vector2d(-22,-38))
                                .setReversed(true)
                                .splineTo(new Pose2d(5,-37,Math.PI))
                                .splineTo(new Pose2d(60,-27,Math.PI))
                                .setReversed(false)
                                .build()
                );
                releaseStones();
                rb.movement.followTrajectorySync(
                        rb.movement.trajectoryBuilder()
                                .lineTo(new Vector2d(53,-27))
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

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
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
        sleep(1);

    }
    public void releaseStones(){
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(200);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(200);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(100);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
        sleep(t);
    }
}
