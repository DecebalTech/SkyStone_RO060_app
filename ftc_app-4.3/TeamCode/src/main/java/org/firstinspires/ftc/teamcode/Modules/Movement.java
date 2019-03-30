package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class Movement {

    public static class Wheels {
        public DcMotor stangaSpate=null;
        public DcMotor stangaFata=null;
        public DcMotor dreaptaFata=null;
        public DcMotor dreaptaSpate=null;
        private String stangaFataName = "stangaFata"; //in 0 pe config
        private String dreaptaFataName = "dreaptaFata"; //in 1 pe config
        private String stangaSpateName = "stangaSpate"; // in 2 pe config
        private String dreaptaSpateName = "dreaptaSpate"; // in 3 pe config
        
        private float MOTOR_TICKS = 560f; //20:1 //1120f; for //40:1
        private float WHEEL_DIAMETER = 10.16f; //cm
        private int TICKS_PER_CM = (int)(MOTOR_TICKS / (WHEEL_DIAMETER * Math.PI));
        
        public double power = 1.0;
        
        public Wheels(HardwareMap hwm) {
            stangaFata = hwm.dcMotor.get(stangaFataName);
            dreaptaFata = hwm.dcMotor.get(dreaptaFataName);
            stangaSpate = hwm.dcMotor.get(stangaSpateName);
            dreaptaSpate = hwm.dcMotor.get(dreaptaSpateName);
        }
        
        public int cm_to_ticks(int cm) {
            return cm * TICKS_PER_CM;
        }
        
        public void setPower(double p1, double p2, double p3, double p4) {
            stangaFata.setPower(p1);
            dreaptaFata.setPower(p2);
            stangaSpate.setPower(p3);
            dreaptaSpate.setPower(p4);
        }
        
        public void setPower(double power) {
            stangaFata.setPower(power);
            dreaptaFata.setPower(power);
            stangaSpate.setPower(power);
            dreaptaSpate.setPower(power);
        }
        
        public void setTargetPosition(int p1, int p2, int p3, int p4) {
            stangaFata.setTargetPosition(p1);
            dreaptaFata.setTargetPosition(p2);
            stangaSpate.setTargetPosition(p3);
            dreaptaSpate.setTargetPosition(p4);
        }
        
        public void stop() {
            stangaFata.setPower(0);
            dreaptaFata.setPower(0);
            stangaSpate.setPower(0);
            dreaptaSpate.setPower(0);
        }
        
        public void motoBrake() {
            stangaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            dreaptaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            stangaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            dreaptaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        
        public void motoFloat() {
            stangaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            dreaptaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            stangaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            dreaptaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        
        public void runUsingEncoder() {
            stangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            stangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER); 
        }
        
        public void runWithoutEncoder() {
            stangaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            dreaptaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            stangaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            dreaptaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        
        public void stopAndResetEncoder() {
            stangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            stangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        
        public void runToPosition() {
            stangaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            dreaptaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            stangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            dreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        
        public void resetEncoders() {
            stopAndResetEncoder();
            runUsingEncoder();
        }
        
        public boolean busy_or() {
            return stangaFata.isBusy() || dreaptaFata.isBusy() || stangaSpate.isBusy() || dreaptaSpate.isBusy();
        }
        public boolean busy_and() {
            return stangaFata.isBusy() && dreaptaFata.isBusy() && stangaSpate.isBusy() && dreaptaSpate.isBusy();
        }
    }
    
    public Wheels wheels;
    public float turbo = 0.75f;
    private float minTurbo = 0.5f, maxTurbo = 1f;
    private boolean turboIsPressed = false, control = false;

    public LinearOpMode op;

    public void updateMovement(Gamepad gamepad1, Gamepad gamepad2) {
        move(gamepad1.left_stick_x, gamepad1.left_stick_y, -gamepad1.right_stick_x);

        if(gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0)
            wheels.stop();

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
    
    public void move(float x, float y, float r) {
        
        double raza = java.lang.Math.hypot(x, y);
        
        double robotAngle = Math.atan2(y, -x) - Math.PI/4;
        
        
        double pStangaFata, pDreaptaFata, pStangaSpate, pDreaptaSpate;
        
        pStangaFata = raza*Math.cos(robotAngle)+r;
        pDreaptaFata = raza*Math.sin(robotAngle)-r;
        pStangaSpate = raza*Math.sin(robotAngle)+r;
        pDreaptaSpate = raza*Math.cos(robotAngle)-r;
        
        wheels.setPower(pStangaFata * turbo, -pDreaptaFata * turbo, pStangaSpate * turbo, -pDreaptaSpate * turbo);
    }

    public void initMovement(HardwareMap hwm) {
        wheels = new Wheels(hwm);
        wheels.runUsingEncoder();
        wheels.motoBrake();
    }
    
    
    public void move (int pStangaFata, int pDreaptaFata, int pStangaSpate, int pDreaptaSpate, double power) {
        wheels.stopAndResetEncoder();
        wheels.runToPosition();
        
        wheels.setTargetPosition(pStangaFata, pDreaptaFata, pStangaSpate, pDreaptaSpate);
        
        wheels.setPower(power);
        
        while(wheels.busy_and()) {
            op.idle();
        }
        wheels.stop();
        
        wheels.stopAndResetEncoder();
    }
    
    public void move(double angle, int dist) {
        int t1, t2, t3, t4;
        double p1, p2, p3, p4;
        
        double robotAngle = angle - Math.PI/4;
        
        t1 = t4 = (int)(Math.sin(robotAngle) * dist * wheels.TICKS_PER_CM);
        t2 = t3 = (int)(Math.cos(robotAngle) * dist * wheels.TICKS_PER_CM);
        
        p1 = p4 = Math.sin(robotAngle);
        p2 = p3 = Math.sin(robotAngle);
        
        op.telemetry.addData("Target 1", t1);
        op.telemetry.addData("Power 1", p1);
        op.telemetry.addData("Target 2", t2);
        op.telemetry.addData("Power 2", p2);
        op.telemetry.addData("Target 3", t3);
        op.telemetry.addData("Power 3", p3);
        op.telemetry.addData("Target 4", t4);
        op.telemetry.addData("Power 4", p4);
        op.telemetry.addData("Distance (in cm)", dist);
        op.telemetry.update();
        
        
        wheels.setTargetPosition(-t1, t2, -t3, t4);
        wheels.setPower(p1, p2, p3, p4);
        while(wheels.busy_and()) { op.idle(); }
        wheels.stop();
        
        wheels.stopAndResetEncoder();
    }
    
    public void move_forwards (int ticks, double power) {
        wheels.stopAndResetEncoder();
        wheels.runToPosition();
        
        wheels.setTargetPosition(-ticks, ticks, -ticks, ticks);
        wheels.setPower(power);
        while(wheels.busy_and())
            op.idle();
        wheels.stop();
    }
    
    public void move_forwards_cm (int cm, double power) {
        int ticks = wheels.cm_to_ticks(cm);
        
        wheels.stopAndResetEncoder();
        wheels.runToPosition();
        
        wheels.setTargetPosition(-ticks, ticks, -ticks, ticks);
        wheels.setPower(power);
        while(wheels.busy_and())
            op.idle();
        wheels.stop();
    }
    
    public void strafe (int ticks, double power) {
        wheels.stopAndResetEncoder();
        wheels.runToPosition();
        
        wheels.setTargetPosition(ticks, ticks, -ticks, -ticks);
        wheels.setPower(power);
        while(wheels.busy_and())
            op.idle();
        wheels.stop();
    }
    
    /*
    public void strafe_cm (int cm, double power) { 
        int ticks = wheels.cm_to_ticks(cm); // traba calculat altfel
        
        wheels.stopAndResetEncoder();
        wheels.runToPosition();
        
        wheels.setTargetPosition(ticks, ticks, -ticks, -ticks);
        wheels.setPower(power);
        while(wheels.busy_and())
            op.idle();
        wheels.stop();
    }
    */
    
    public void rotate (int ticks, double power) {
        wheels.stopAndResetEncoder();
        wheels.runToPosition();
        
        wheels.setTargetPosition(-ticks, -ticks, -ticks, -ticks);
        wheels.setPower(power);
        while(wheels.busy_and())
            op.idle();
        wheels.stop();
    }
    
    /* 
    Pentru movement fata: - + - +
    Pentru movement spate: + - + -
    Pentru strafe stanga: + + - -
    Pentru strafe dreapta: - - + +
    Pentru rotatie: - - - - / + + + +
    Pentru diagonala: + 0 0 - / - 0 0 + / 0 + - 0 / 0 - + 0
    
    algoritmul pentru movement autonom:
    > reset encoder
    > run to position
    > set target position 
    > set power
    > while(busy), idle
    > stop
    > stop and reset encoder
    
    */
}
