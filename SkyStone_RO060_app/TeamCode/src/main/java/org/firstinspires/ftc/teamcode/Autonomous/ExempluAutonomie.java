package org.firstinspires.ftc.teamcode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveREV;

@Autonomous(group = "drive")
public class ExempluAutonomie extends LinearOpMode {

    /*
        Ideea este să declari
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);
        și să utilizezi drive-ul de aici.

        Iar pentru alte chestii de pe robot, declari un robot ca în celelalte autonomii.
     */


    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        drive.setPoseEstimate(new Pose2d(-36,-70,(float)Math.PI));
        drive.followTrajectorySync(

                drive.trajectoryBuilder()
                        .strafeTo(new Vector2d(-47,-35))
                        .setReversed(true)
                        .splineTo(new Pose2d(-20,-42,(float)Math.PI))
                        .lineTo(new Vector2d(40,-40))
                        .setReversed(false)
                        .build()

        );
        sleep(1000);
        drive.followTrajectorySync(

                drive.trajectoryBuilder()
                        .lineTo(new Vector2d(-50,-40))
                        .build()

        );



    }
}
