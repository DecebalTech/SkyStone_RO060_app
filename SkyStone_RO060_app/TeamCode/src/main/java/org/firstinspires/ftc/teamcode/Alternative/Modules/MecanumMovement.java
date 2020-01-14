package org.firstinspires.ftc.teamcode.Alternative.Modules;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.robomatic.robot.Robot;
import com.acmerobotics.robomatic.robot.Subsystem;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MecanumMovement extends Subsystem {

    public static double P = 10;
    public static double I = 4;
    public static double D = 0;
    public static double F = 19;
    public static double TELEOP_V = 45;
    public static double TELEOP_OMEGA = 2;

    private DcMotorEx[] motors;
    private static final String[] motorNames = {"frontLeft", "frontRight", "backLeft", "backRight"};
    private BNO055IMU imu;

    public MecanumMovement(Robot rb) {
        super("MecanumMovement");

        for(int i=0;i<=3;i++)
            motors[i] = rb.getMotor(motorNames[i]);
            imu = rb.getRevHubImu(0);
    }



    protected MecanumMovement(String telemetryPrefix) {
        super(telemetryPrefix);
    }

    @Override
    public void update(Canvas fieldOverlay) {

    }
}
