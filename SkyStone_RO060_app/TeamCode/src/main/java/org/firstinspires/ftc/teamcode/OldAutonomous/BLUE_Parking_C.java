package org.firstinspires.ftc.teamcode.OldAutonomous;
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

@Autonomous (name = "BLUE_Parking_C", group = "RED")
public class BLUE_Parking_C extends LinearOpMode {

    /*
     */

    private Robot rb = null;
    private final int t = 1; // pauza intre executarea unei noi functii
    // private Scanner scanner = new Scanner(); // definire vuforia
    //private int scanResult = -1; // valoare initiala pentru rezultatul scanrii
    private float stoneDist = 10.2f; // distanta fata de stone pentru al putea colecta
    private float ap = 13;  // distanta mai mare fata de stone pentru a putea pregati bratul
    private float calibrateDist = 22; // distanta fata de stone pentru a "putea calibra robotul" sa treaca sigur sub bridge safe
    private float wallDist = 25; // distanta fata de perete [frontdist] pentru a colecta al doi-lea stone ca sa fim siguri ca il prindem
    private double voltComp =0;
    static double compOffset =1.12; // multiplier

    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);
        voltComp = ((rb.controlHub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS) - 13.7) * compOffset);


        //SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);

//        waitForStart();

        while (!isStarted() && !isStopRequested()) {
            if (isStopRequested()) break;
            //   int temp = scanner.scanFirstTwo(this);
            //   scanResult = (temp == -1 ? scanResult : temp);
            //    telemetry.addData("Result:", scanResult);
            //  telemetry.update();
        }

        //   scanner.stopTfod();

        //scanResult = scanner.scanWithAverage(this);

        //  telemetry.addData("scan result", scanResult);

        rb.movement.setPoseEstimate(new Pose2d(28, 64, 0));
        rb.movement.followTrajectorySync(
                rb.movement.trajectoryBuilder()
                        .strafeTo(new Vector2d(28,40))
                        .setReversed(true)
                        .splineTo(new Pose2d(-4,40,0))
                        .build()

        );
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CLOSED); // initializare
        sleep(t);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(26000);
        return ;
    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        //  scanner.Init("webcam", hwm);
        //   scanner.initTfod(hwm);
        //  scanner.activateTfod();

        //rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
    }

}