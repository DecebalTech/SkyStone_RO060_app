package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.hardware.motors.GoBILDA5202Series;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Movement {

    public static final MotorConfigurationType MOTOR_CONFIG = MotorConfigurationType.getMotorType(GoBILDA5202Series.class);
    public static final int WHEEL_DIAMETER = 10; //in cm
    public static final int GEAR_RATIO = 2;
    public static final int kP = 2;
    public static final int kI = 0;
    public static final int kD = 19;

    private Motor frontLeft = new Motor(), frontRight = new Motor(), backLeft = new Motor(), backRight = new Motor();
    //private Gyro gyro = new Gyro();
    private IMU imu = new IMU();
    private static String Names[] = {"frontLeft", "frontRight", "backLeft", "backRight"};

    private float[] TurboMultipliers = {0.25f, 0.5f, 1};
    private int TurboIndex = 1;

    //private static float TickPerCm = 24.42f; //this is only for forward/backward movement
    public double getTickPerCm() {return MOTOR_CONFIG.getTicksPerRev() / WHEEL_DIAMETER * Math.PI * GEAR_RATIO;}
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

        dx = -(int)(Math.cos(robotAngle) * dist_cm * getTickPerCm());
        dy = -(int)(Math.sin(robotAngle) * dist_cm * getTickPerCm());

        powx = (float)Math.cos(robotAngle)*pow;
        powy = (float)Math.sin(robotAngle)*pow;

        setTargetPosition(dx, dy, dy, dx);
        setPower(powx, powy, powy, powx);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }

    public void moveCM_IMU(double angle, int dist_cm, float pow, LinearOpMode op) {
        if(!imu.IsOn()) {
            op.telemetry.addLine("Error. Cannot find IMU.");
            op.telemetry.update();
            return;
        }

        stopAndResetEncoder();

        int     newLeftTarget;
        int     newRightTarget;
        int     moveCounts;
        double  max;
        double  error;
        double  prevError;
        double  steer;
        double  leftSpeed;
        double  rightSpeed;
        double  integral;
        double  derivative;

        moveCounts = (int)(dist_cm * getTickPerCm());
        newLeftTarget = moveCounts;
        newRightTarget = moveCounts;

        setTargetPosition(newLeftTarget, newRightTarget, newLeftTarget, newRightTarget);

        runToPosition();

        setPower(1);

        integral = 0;
        prevError = 0;

        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.reset();


        while(AreAllWheelsBusy()) {
            error = imu.getError(angle);
            integral += integral + error * elapsedTime.milliseconds();
            derivative = (error - prevError) / elapsedTime.milliseconds();
            steer = imu.getSteer(error, Gyro.P_DRIVE_COEFF);

            if(dist_cm<0) steer*=-1;

            leftSpeed = (1) - steer;
            rightSpeed = (1) + steer;

            /*
                leftSpeed = (kP * error + kI * integral + kD * derivative) - steer;
                rightSpeed = (kP * error + kI * integral + kD * derivative) + steer;
            */
            max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
            if(max > 1) {
                leftSpeed /= max;
                rightSpeed /= max;
            }

            setPower((float)leftSpeed, (float)rightSpeed, (float)leftSpeed, (float)rightSpeed);

            prevError = error;
        }

        stop();

        runUsingEncoder();
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

        int d = (int)(Radius * angle * getTickPerCm());

        setTargetPosition(d, -d, d, -d);
        setPower(pow);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) { op.idle(); }
        stop();
    }
    public void rotateIMUAbsolute(double angle, float pow, LinearOpMode op) { //the reference orientation is the one the Robot has at Init
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
        setTargetPosition(-(int)(cm*getTickPerCm()), -(int)(cm*getTickPerCm()), -(int)(cm*getTickPerCm()), -(int)(cm*getTickPerCm()));
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

    public boolean AreAllWheelsBusy() {
        return frontRight.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }


    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn() && backLeft.IsOn() && backRight.IsOn();
    }
}
