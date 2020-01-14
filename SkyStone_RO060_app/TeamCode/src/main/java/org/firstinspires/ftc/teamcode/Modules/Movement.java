package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Movement {

    /**
        d = 10cm (Diameter of Wheels)

        ticks/motor rotation = 383.6 (Encoder Countable Events Per Revolution for GoBilda 5202)
        2:1 reduction
        => ticks/wheel rotation = 767.2
        cm/rotation = 31.415 (The Length of the Wheel)


        tick/cm = 767.2 / 31.415

        tick/cm = 24.63
     **/

    private Motor frontLeft = new Motor(), frontRight = new Motor(), backLeft = new Motor(), backRight = new Motor();
    //private Gyro gyro = new Gyro();
    private IMU imu = new IMU();
    private static String Names[] = {"frontLeft", "frontRight", "backLeft", "backRight"};

    private float[] TurboMultipliers = {0.25f, 0.5f, 1};
    private int TurboIndex = 1;

    private static float TickPerCm = 24.42f; //this is only for forward/backward movement
    private static float Radius = 34.85f; //distance from center of robot to center of a wheel

    public void Init(HardwareMap hwm) {
        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);
        backLeft.Init(Names[2], hwm);
        backRight.Init(Names[3], hwm);
        imu.Init("imu", hwm);

        if(frontRight.IsOn()) {
            frontRight.InvertDirection();
        }

        if(backRight.IsOn()) {
            backRight.InvertDirection();
        }

        runUsingEncoder();
        Brake_ZeroPowerBehavior();
    }

    public String Encoders(Gamepad gamepad1) {
        if(gamepad1.x) {
            stopAndResetEncoder();
            runUsingEncoder();
        }
        return "frontLeft: " + frontLeft.getCurrentPosition() + "\nfrontRight: "
                + frontRight.getCurrentPosition() + "\nbackLeft: "
                + backLeft.getCurrentPosition() + "\nbackRight: "
                + backRight.getCurrentPosition();
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


    /**
     *
     * @param p1 frontLeft
     * @param p2 frontRight
     * @param p3 backLeft
     * @param p4 backRight
     */
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

    public void moveCM(float angle, int dist_cm, float pow, LinearOpMode op) {
        stopAndResetEncoder();
        runToPosition();

        int dx, dy;
        float powx, powy;

        float robotAngle = angle - (float)Math.PI/4;

        dx = -(int)(Math.cos(robotAngle) * dist_cm * TickPerCm);
        dy = -(int)(Math.sin(robotAngle) * dist_cm * TickPerCm);

        powx = (float)Math.cos(robotAngle)*pow;
        powy = (float)Math.sin(robotAngle)*pow;

        setTargetPosition(dx, dy, dy, dx);
        setPower(powx, powy, powy, powx);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }

    public void moveTICKS(float angle, int dist, float pow, LinearOpMode op) {
        stopAndResetEncoder();
        runToPosition();

        int dx, dy;
        float powx, powy;

        float robotAngle = angle - (float)Math.PI/4;

        dx = -(int)(Math.cos(robotAngle) * dist);
        dy = -(int)(Math.sin(robotAngle) * dist);

        powx = (float)Math.cos(robotAngle)*pow;
        powy = (float)Math.sin(robotAngle)*pow;

        setTargetPosition(dx, dy, dy, dx);
        setPower(powx, powy, powy, powx);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }

    public void rotate(float angle, float pow, LinearOpMode op) {
        stopAndResetEncoder();
        runToPosition();

        int d = (int)(Radius * angle * TickPerCm);

        setTargetPosition(d, -d, d, -d);
        setPower(pow);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }
    public void rotateIMUAbsolute(double angle, float pow, LinearOpMode op) { //the reference orientation is the one the DecebalBot has at Init
        if(!imu.IsOn()) {
            op.telemetry.addLine("Error. Cannot find IMU.");
            op.telemetry.update();
            return;
        }
        runUsingEncoder();
        double error;
        double steer;
        boolean onTarget = false;
        double pLeft, pRight;

        while(!onTarget) {
            error = imu.getError(angle);

            if(Math.abs(error) < Math.PI/300) {
                steer = 0;
                pLeft = pRight = steer * pow;
                onTarget = true;
            }
            else
            {
                steer = imu.getSteer(error, Gyro.P_TURN_COEFF);
                pLeft = steer * pow;
                pRight = -pLeft;
            }

            setPower((float)pLeft, (float)pRight, (float)pLeft, (float)pRight);
        }
    }

    public void rotateIMURelative(double angle, float pow, LinearOpMode op) {
        if(!imu.IsOn()) {
            op.telemetry.addLine("Error. Cannot find IMU.");
            op.telemetry.update();
            return;
        }
        runUsingEncoder();
        double error;
        double steer;
        boolean onTarget = false;
        double pLeft, pRight;

        double nextAngle = imu.GetAngles().firstAngle + angle;
        while(!onTarget) {
            error = imu.getError(nextAngle);

            if(Math.abs(error) < Math.PI/300) {
                steer = 0;
                pLeft = pRight = steer * pow;
                onTarget = true;
            }
            else
            {
                steer = imu.getSteer(error, Gyro.P_TURN_COEFF);
                pLeft = steer * pow;
                pRight = -pLeft;
            }

            setPower((float)pLeft, (float)pRight, (float)pLeft, (float)pRight);
        }
    }

    /*
    public void rotateGyro(double angle, float pow, LinearOpMode op) {
        if(!gyro.IsOn()) return;
        double error;
        double steer;
        boolean onTarget = false;
        double pLeft, pRight;

        while (!onTarget) {
            error = gyro.getError(angle);

            if (error < Gyro.HEADING_THRESHOLD) {
                steer = 0.0;
                pLeft = pRight = 0.0;
                onTarget = true;
            }
            else
            {
                steer = gyro.getSteer(angle, Gyro.P_TURN_COEFF);
                pLeft = steer * pow;
                pRight = -pLeft;
            }

            setPower((float)pLeft, (float)pRight, (float)pLeft, (float)pRight);
        }
    }
    */
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

    public void Brake_ZeroPowerBehavior() {
        if(AreWheelsActive()) {
            frontLeft.Brake();
            frontRight.Brake();
            backLeft.Brake();
            backRight.Brake();
        }
    }


    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn() && backLeft.IsOn() && backRight.IsOn();
    }
}
