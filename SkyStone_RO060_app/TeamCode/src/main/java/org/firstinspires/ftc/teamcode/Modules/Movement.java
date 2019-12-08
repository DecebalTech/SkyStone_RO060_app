package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Movement {

    /**
        d = 10cm (Diameter of Wheels)

        ticks/rotation = 383.6 (Encoder Countable Events Per Revolution for GoBilda 5202)
        cm/rotation = 31.415 (The Length of the Wheel)
        2:1 reduction

        tick/cm = (383.6 / 31.415) * 2
        tick/cm = 12.21 * 2

        tick/cm = 24.42
     **/

    private Motor frontLeft = new Motor(), frontRight = new Motor(), backLeft = new Motor(), backRight = new Motor();
    private static String Names[] = {"frontLeft", "frontRight", "backLeft", "backRight"};

    private float[] TurboMultipliers = {0.25f, 0.5f, 1};
    private int TurboIndex = 1;

    private static float TickPerCm = 24.42f; //this is only for forward/backward movement

    public void Init(HardwareMap hwm) {
        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);
        backLeft.Init(Names[2], hwm);
        backRight.Init(Names[3], hwm);

        if(frontRight.IsOn()) {
            frontRight.InvertDirection();
        }

        if(backRight.IsOn()) {
            backRight.InvertDirection();
        }
    }

    public String Move(Gamepad gamepad1) {
        float angle, r, powX, powY;

        if(gamepad1.left_trigger>0) TurboIndex = 0;
        else if (gamepad1.right_trigger>0) TurboIndex = 2;
        else TurboIndex = 1;

        angle = (float)(Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI/4);
        r = (float)Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);

        powX = (float)(Math.cos(angle)*r);
        powY = (float)(Math.sin(angle)*r);

        String s = "";

        if(frontLeft.IsOn()) {
            s += "frontLeft: ";
            try {
                float pow = (powX - gamepad1.right_stick_x) * TurboMultipliers[TurboIndex];
                frontLeft.SetPower(pow);
                s += pow + ";";
            } catch (Exception ex) {
                frontLeft.SwitchState(false);
                s += "DISCONNECTED; ";
            }
        }
        else s += "frontLeft: DISCONNECTED; ";

        if(frontRight.IsOn()) {
            s += "frontRight: ";
            try {
                float pow = (powY + gamepad1.right_stick_x) * TurboMultipliers[TurboIndex];
                frontRight.SetPower(pow);
                s += pow + ";";
            } catch (Exception ex) {
                frontRight.SwitchState(false);
                s += "DISCONNECTED; ";
            }
        }
        else s += "frontRight: DISCONNECTED; ";

        if(backLeft.IsOn()) {
            s += "backLeft: ";
            try {
                float pow = (powY - gamepad1.right_stick_x)*TurboMultipliers[TurboIndex];
                backLeft.SetPower(pow);
                s += pow + ";";
            }
            catch (Exception ex) {
                backLeft.SwitchState(false);
                s += "DISCONNECTED; ";
            }
        }
        else s += "backLeft: DISCONNECTED; ";

        if(backRight.IsOn()) {
            s+= "backRight: ";
            try {
                float pow = (powX + gamepad1.right_stick_x)*TurboMultipliers[TurboIndex];
                backRight.SetPower(pow);
                s += pow + ";";
            }
            catch (Exception ex) {
                backRight.SwitchState(false);
                s += "DISCONNECTED; ";
            }
        }
        else s+= "backRight: DISCONNECTED; ";

        return s +"\nTurbo: " + TurboIndex;
    }


    public void setTargetPosition(int p1, int p2, int p3, int p4) {
        if(AreWheelsActive()) {
            frontLeft.setTargetPosition(p1);
            frontRight.setTargetPosition(p2);
            backLeft.setTargetPosition(p3);
            backRight.setTargetPosition(p4);
        }
    }

    public void setPower(float p1, float p2, float p3, float p4) {
        if(AreWheelsActive()) {
            frontLeft.SetPower(p1);
            frontRight.SetPower(p2);
            backLeft.SetPower(p3);
            backRight.SetPower(p4);
        }
    }

    public void setPower(float p) {
        setPower(p, p, p, p);
    }

    public void stop() {
        setPower(0);
    }

    public void move(double angle, int dist, LinearOpMode op) {
        stopAndResetEncoder();
        runToPosition();
        int t1, t2, t3, t4;

        double robotAngle = angle - Math.PI/4;

        t1 = t4 = (int)(Math.sin(robotAngle) * dist * TickPerCm);
        t2 = t3 = (int)(Math.cos(robotAngle) * dist * TickPerCm);

        setTargetPosition(-t1, t2, -t3, t4);
        setPower(.5f);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }

    public void moveForwards(int cm, LinearOpMode op) {
        stopAndResetEncoder();
        runToPosition();
        setTargetPosition(-(int)(cm*TickPerCm), -(int)(cm*TickPerCm), -(int)(cm*TickPerCm), -(int)(cm*TickPerCm));
        setPower(.5f);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }

    public void runUsingEncoder() {
        if(AreWheelsActive()) {
            frontLeft.runUsingEncoder();
            frontRight.runUsingEncoder();
            backLeft.runUsingEncoder();
            backRight.runUsingEncoder();
        }
    }

    public void stopAndResetEncoder() {
        if(AreWheelsActive()) {
            frontLeft.stopAndResetEncoder();
            frontRight.stopAndResetEncoder();
            backLeft.stopAndResetEncoder();
            backRight.stopAndResetEncoder();
        }
    }

    public void runToPosition() {
        if(AreWheelsActive()) {
            frontLeft.runToPosition();
            frontRight.runToPosition();
            backLeft.runToPosition();
            backRight.runToPosition();
        }
    }


    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn() && backLeft.IsOn() && backRight.IsOn();
    }
}
