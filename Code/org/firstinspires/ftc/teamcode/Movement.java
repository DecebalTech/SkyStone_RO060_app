package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


public class Movement {

    public DcMotor moto1;
    public DcMotor moto2;
    public float turbo = 1.0f;
    private float minTurbo = 0.5f, maxTurbo = 2.5f;
    private boolean turboIsPressed = false;


    public void move(float power) {
        moto1.setPower(-power * turbo);
        moto2.setPower(power * turbo);
    }

    public void rotate(float power) {
        moto1.setPower(-power * turbo);
        moto2.setPower(-power * turbo);
    }

    public void stop() {
        moto1.setPower(0);
        moto2.setPower(0);
    }

    public void modifyTurbo(float modifier) {
        turbo+=modifier;
        if(turbo < minTurbo) turbo = minTurbo;
        if(turbo > maxTurbo) turbo = maxTurbo;
    }

    public void updateMovement(Gamepad gamepad1, Gamepad gamepad2) {
        if(gamepad1.left_stick_y!=0){
            move(gamepad1.left_stick_y);
        }
        if (gamepad1.left_stick_x!=0){
            rotate(gamepad1.left_stick_x);
        }
        if(gamepad1.left_stick_x==0 && gamepad1.left_stick_y==0)
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

    public void initMovement(String moto1Name, String moto2Name, HardwareMap hwm) {
        moto1 = hwm.dcMotor.get(moto1Name);
        moto2 = hwm.dcMotor.get(moto2Name);
    }
}
