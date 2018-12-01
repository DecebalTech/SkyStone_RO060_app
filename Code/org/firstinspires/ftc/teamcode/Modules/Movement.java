package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class Movement {

    public DcMotor moto1;
    public DcMotor moto2;
    public DcMotor moto3;
    public DcMotor moto4;

    public float turbo = 1.0f;
    private float minTurbo = 0.5f, maxTurbo = 2.5f;
    private boolean turboIsPressed = false;
    public double power = 0.4;

    private String moto1Name = "moto1";
    private String moto2Name = "moto2";
    private String moto3Name = "moto3";
    private String moto4Name = "moto4";


    public void move(float power) {
        moto1.setPower(power * turbo);
        moto2.setPower(-power * turbo);
        moto3.setPower(power * turbo);
        moto4.setPower(-power * turbo);
    }

    public void rotate(float power) {
        moto1.setPower(-power * turbo);
        moto2.setPower(-power * turbo);
        moto3.setPower(-power * turbo);
        moto4.setPower(-power * turbo);
    }

    public void moveSideways(float power) {
        moto1.setPower(-power * turbo);
        moto2.setPower(-power * turbo);
        moto3.setPower(power * turbo);
        moto4.setPower(power * turbo);
    }

    public void stop() {
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);
    }

    public void modifyTurbo(float modifier) {
        turbo+=modifier;
        if(turbo < minTurbo) turbo = minTurbo;
        if(turbo > maxTurbo) turbo = maxTurbo;
    }

    public void updateMovement(Gamepad gamepad1, Gamepad gamepad2) {
        if(gamepad1.left_stick_y != 0){
            move(gamepad1.left_stick_y);
        }
        if (gamepad1.left_stick_x != 0){
            rotate(gamepad1.left_stick_x);
        }
        if (gamepad1.right_stick_x != 0)
        {
            moveSideways(gamepad1.right_stick_x);
        }
        if(gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0)
        {
            stop();
        }

        if(gamepad1.left_bumper && !turboIsPressed) {
            modifyTurbo(-0.5f);
            turboIsPressed = true;
        }
        if(gamepad1.right_bumper && !turboIsPressed) {
            modifyTurbo(0.5f);
            turboIsPressed = true;
        }
        if(!gamepad1.left_bumper && !gamepad1.right_bumper)
            turboIsPressed = false;
    }

    public void initMovement(HardwareMap hwm) {
        moto1 = hwm.dcMotor.get(moto1Name);
        moto2 = hwm.dcMotor.get(moto2Name);
        moto3 = hwm.dcMotor.get(moto3Name);
        moto4 = hwm.dcMotor.get(moto4Name);
    }

    public void moveWithEncoders(int pos, LinearOpMode op) {

        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        moto1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moto1.setTargetPosition(pos);
        moto2.setTargetPosition(-pos);
        moto3.setTargetPosition(pos);
        moto4.setTargetPosition(-pos);

        moto1.setPower(power);
        moto2.setPower(power);
        moto3.setPower(power);
        moto4.setPower(power);

        while(moto1.isBusy() && moto2.isBusy() && moto3.isBusy() && moto4.isBusy()) {
            op.idle();
        }
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);

        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void rotateWithEncoders(int pos, LinearOpMode op) {

        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        moto1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moto1.setTargetPosition(pos);
        moto2.setTargetPosition(pos);
        moto3.setTargetPosition(pos);
        moto4.setTargetPosition(pos);

        moto1.setPower(-power);
        moto2.setPower(-power);
        moto3.setPower(-power);
        moto4.setPower(-power);

        while(moto1.isBusy() && moto2.isBusy() && moto3.isBusy() && moto4.isBusy()) {
            op.idle();
        }
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);

        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void sidewaysWithEncoders(int pos, LinearOpMode op) {

        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        moto1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moto1.setTargetPosition(-pos);
        moto2.setTargetPosition(-pos);
        moto3.setTargetPosition(pos);
        moto4.setTargetPosition(pos);

        moto1.setPower(power);
        moto2.setPower(power);
        moto3.setPower(power);
        moto4.setPower(power);

        while(moto1.isBusy() && moto2.isBusy() && moto3.isBusy() && moto4.isBusy()) {
            op.idle();
        }
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);

        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
