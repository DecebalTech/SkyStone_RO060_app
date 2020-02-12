package org.firstinspires.ftc.teamcode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveREV;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(group = "drive")
public class ExempluAutonomie extends LinearOpMode {

    /*
        Ideea este să declari
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);
        și să utilizezi drive-ul de aici.

        Iar pentru alte chestii de pe robot, declari un robot ca în celelalte autonomii.
     */

    private Robot rb = null;
    private final int t = 1; // pauza intre executarea unei noi functii
    private Scanner scanner = new Scanner(); // definire vuforia
    private int scanResult = -1; // valoare initiala pentru rezultatul scanrii
    private float stoneDist = 11f; // distanta fata de stone pentru al putea colecta
    private float ap = 20f;  // distanta mai mare fata de stone pentru a putea pregati bratul
    private float calibrateDist = 22; // distanta fata de stone pentru a "putea calibra robotul" sa treaca sigur sub bridge safe
    private float wallDist = 25; // distanta fata de perete [frontdist] pentru a colecta al doi-lea stone ca sa fim siguri ca il prindem


    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);


        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }


        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);

        waitForStart();

        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }
        drive.setPoseEstimate(new Pose2d(-36,-66,Math.PI));
        drive.followTrajectorySync(

                drive.trajectoryBuilder()
                        .strafeTo(new Vector2d(-47,-30))

                        .build()

        );
        sleep(t);

        sleep(t);
        drive.followTrajectorySync(

                drive.trajectoryBuilder()
                        .strafeTo(new Vector2d(-25,-50))
                        .setReversed(true)
                        .splineTo(new Pose2d(40,-50))
                        .setReversed(false)
                        .build()
        );
        

    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
    }
}
