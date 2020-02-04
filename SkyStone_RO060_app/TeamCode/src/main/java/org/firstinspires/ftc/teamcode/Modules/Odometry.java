package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry {

    public static final double WHEEL_RADIUS = 9.6; //cm
    public static final double TICKS_PER_REVOLUTION = 4.5; //la pow 0.5f;

    private DcMotor Left, Right, Back;

    public static final double CM_PER_REVOLUTION() {
        return WHEEL_RADIUS/TICKS_PER_REVOLUTION;
    }

    public Odometry(String leftName, String rightName, String backName, HardwareMap hwm) {
        Left = hwm.dcMotor.get(leftName);
        Right = hwm.dcMotor.get(rightName);
        Back = hwm.dcMotor.get(backName);

        resetEncoders();
    }

    public Odometry(DcMotor LeftMotor, DcMotor RightMotor, DcMotor BackMotor) {
        Left = LeftMotor;
        Right = RightMotor;
        Back = BackMotor;

        resetEncoders();
    }

    public void resetEncoders() {
        resetLeft();
        resetRight();
        resetBack();
    }

    public void resetLeft() {
        Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetRight() {
        Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetBack() {
        Back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double getLeftDistCM() {
        return Left.getCurrentPosition() * CM_PER_REVOLUTION();
    }

    public double getRightDistCM() {
        return Right.getCurrentPosition() * CM_PER_REVOLUTION();
    }

    public double getBackDistCM() {
        return Back.getCurrentPosition()  * CM_PER_REVOLUTION();
    }

    public double getLeftDist() {
        return Left.getCurrentPosition();
    }

    public double getRightDist() {
        return Right.getCurrentPosition();
    }

    public double getBackDist() {
        return Back.getCurrentPosition();
    }
}
