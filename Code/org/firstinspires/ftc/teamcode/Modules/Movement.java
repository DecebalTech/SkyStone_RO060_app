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

    public float turbo = 0.5f;
    private float minTurbo = 0.25f, maxTurbo = 1.0f;
    private boolean turboIsPressed = false, control = false;
    public double power = 0.4;

    private String moto1Name = "moto1";
    private String moto2Name = "moto2";
    private String moto3Name = "moto3";
    private String moto4Name = "moto4";

    public LinearOpMode op;

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
    
    public void resetEncoders() {
        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        moto1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moto2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moto3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moto4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    /*public void diagonalRight(float power)
    {
        moto1.setPower(0);
        moto2.setPower(power * turbo);
        moto3.setPower(-power * turbo);
        moto4.setPower(0);
    }
    
    public void diagonalLeft(float power)
    {
        moto1.setPower(power * turbo);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(-power * turbo);
    }*/

    /*public void modifyTurbo(float modifier) {
        turbo+=modifier;
        if(turbo < minTurbo) turbo = minTurbo;
        if(turbo > maxTurbo) turbo = maxTurbo;
    }*/

    public void updateMovement(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad1.left_stick_y != 0 && gamepad1.left_stick_x == 0) {
            move(-gamepad1.left_stick_y);
        }

        if (gamepad1.right_stick_x != 0) {
            rotate(gamepad1.right_stick_x);
        }

        if (gamepad1.left_stick_x != 0 && gamepad1.left_stick_y == 0) {
            moveSideways(-gamepad1.left_stick_x);
        }

        /*if(gamepad1.dpad_right && gamepad1.dpad_up) {
            diagonalLeft(1); //dreapta fata 
        }

        if(gamepad1.dpad_left && gamepad1.dpad_up) {
            diagonalRight(-1); //stanga fata  
        }

        if(gamepad1.dpad_right && gamepad1.dpad_down) {
           diagonalRight(1); // dreapta spate  
        }

        if(gamepad1.dpad_left && gamepad1.dpad_down) {
            diagonalLeft(-1); // stanga spate
        }*/

        if(gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0)
        {
            stop();
            /*
            if(control)
            {
                moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                
                moto1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                moto2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                moto3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                moto4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            */
        }

        if(gamepad1.left_trigger > 0.1 && !turboIsPressed) {
            turbo = 0.25f;
            turboIsPressed = true;
        }
        
        if(gamepad1.right_trigger > 0.1 && !turboIsPressed) {
            turbo = 1.0f;
            turboIsPressed = true;
        }
        
        if(gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0){
            turbo = 0.5f;
            turboIsPressed = false;
        }

    }

    public void initMovement(HardwareMap hwm) {
        moto1 = hwm.dcMotor.get(moto1Name);
        moto2 = hwm.dcMotor.get(moto2Name);
        moto3 = hwm.dcMotor.get(moto3Name);
        moto4 = hwm.dcMotor.get(moto4Name);
    }
    
    /*
    public void initControl() {
        moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        moto1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moto2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moto3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moto4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        control = true;
    }
    */

    public void moveWithEncoders(int pos) {

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
    
    public void moveWithEncoders(int pos, double power) {

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

    public void rotateWithEncoders(int pos) {

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
    
    public void rotateWithEncoders(int pos, double power) {

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

    public void sidewaysWithEncoders(int pos) {

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
    
    public void sidewaysWithEncoders(int pos, double power) {

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
