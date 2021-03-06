package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.DriveConstants;
import org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.Robot;
import org.openftc.revextensions2.RevBulkData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.StrictMath.abs;
import static org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.DriveConstants.MOTOR_VELO_PID;
import static org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.DriveConstants.encoderTicksToInches;
import static org.firstinspires.ftc.teamcode.Modules.RoadRunner_Modules.DriveConstants.getMotorVelocityF;

public class Movement extends SampleMecanumDriveBase {

    public DistSensor rightDist = new DistSensor(), frontDist = new DistSensor(), backDist = new DistSensor();
    private Motor frontLeft = new Motor(), frontRight = new Motor(), backLeft = new Motor(), backRight = new Motor();
    private List<Motor> motors;
    //private Gyro gyro = new Gyro();
    private IMU imu = new IMU();
    private static String Names[] = {"frontLeft", "frontRight", "backLeft", "backRight"};
    private Robot rb;

    private float[] TurboMultipliers = {.25f, 0.65f, 1f};
    private int TurboIndex = 1;

    //private static float TickPerCm = 24.42f; //this is only for forward/backward movement
    private static float Radius = 34.85f; //distance from center of robot to center of a wheel

    public void Init(HardwareMap hwm, Robot _rb, boolean Autonomous) {

        rightDist.Init("rightDist", hwm);
        frontDist.Init("frontDist", hwm);
        backDist.Init("backDist", hwm);

        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);
        backLeft.Init(Names[2], hwm);
        backRight.Init(Names[3], hwm);
        motors = Arrays.asList(frontLeft, backLeft, backRight, frontRight);
        if(Autonomous) imu.Init("imu", hwm);


        if(frontRight.IsOn()) {
            frontRight.InvertDirection();
        }

        if(backRight.IsOn()) {
            backRight.InvertDirection();
        }

        if(AreWheelsActive()) {
            setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        }

        rb = _rb;

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
        r = (float)Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y) * 2;

        powX = (float)(Math.cos(angle)*r);
        powY = (float)(Math.sin(angle)*r);

        String s = "";

        if(frontLeft.IsOn()) {
            s += "frontLeft: ";
            try {
                float pow = (powX - 2*gamepad1.right_stick_x) * TurboMultipliers[TurboIndex];
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
                float pow = (powY + 2*gamepad1.right_stick_x) * TurboMultipliers[TurboIndex];
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
                float pow = (powY - 2*gamepad1.right_stick_x)*TurboMultipliers[TurboIndex];
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
                float pow = (powX + 2*gamepad1.right_stick_x)*TurboMultipliers[TurboIndex];
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

        int dx, dy;
        float powx, powy;

        float robotAngle = angle - (float)Math.PI/4;

        dx = -(int)(Math.cos(robotAngle) * dist_cm * DriveConstants.getTicksPerCM());
        dy = -(int)(Math.sin(robotAngle) * dist_cm * DriveConstants.getTicksPerCM());


        /*
        powx = (float)Math.cos(robotAngle)*pow;
        powy = (float)Math.sin(robotAngle)*pow;
        */

        if(dx>dy) {
            powx = pow;
            powy = dy/dx * pow;
        }
        else
        {
            powy = pow;
            powx = dx/dy * pow;
        }

        setTargetPosition(dx, dy, dy, dx);
        runToPosition();
        //if(angle==Math.PI/2 ||  angle==3*Math.PI/2) powx=powy=pow;
        setPower(powx, powy, powy, powx);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() && op.opModeIsActive()) { op.idle(); }
        stop();
    }
    
    public void moveCM_ramped(double angle, float dist_cm, float maxPow, LinearOpMode op) {

        stopAndResetEncoder();

        //calcularea distantelor pe axele de miscare ale robotului
        
        double robotAngle = angle - Math.PI/4;
     
        int dx = -(int)(Math.cos(robotAngle) * dist_cm * DriveConstants.getTicksPerCM());
        int dy = -(int)(Math.sin(robotAngle) * dist_cm * DriveConstants.getTicksPerCM());

        setTargetPosition(dx, dy, dy, dx);
        runToPosition();


        float powCoeffX, powCoeffY;

        powCoeffX = (float)Range.clip(Math.abs(dx/dy), 0, 1);
        powCoeffY = (float)Range.clip(Math.abs(dy/dx), 0, 1);

        // ramping, prin corectarea erorii
        // pana la jumatatea drumului (|errorX|>dx/2, |errorY|>dy/2) puterea creste de la 0.25 la maxPow
        // de la jumatate la final (|errorX|<dx/2, |errorY|<dx/2) puterea scade de la maxPow la 0.25
        // pasul erorii se calculeaza ca fiind a 5-a parte din diferenta maxPow - minPow 
        // schimbarea se petrece la intervalul de stepTime milisecunde

        float minPow = 0.25f; // puterea minima alocata rotilor
        float step = (maxPow - minPow) / 10; // pasul de schimbare a puterii
        int stepTime = 5;
        int minError = 20; // marja de eroare pentru distanta IN TICKURI

        // calcularea erorilor
        // pe axe, este media aritmetica a erorilor rotilor omoloage (de pe aceeasi axa), adica in X
        int errorX, errorY;

        float powX = minPow * powCoeffX, powY = minPow * powCoeffY;

        //while((errorX>minError || errorY>minError) && !op.isStopRequested()) {
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() && !op.isStopRequested()) {
            errorX = (int)Math.abs(dx - Math.abs((frontLeft.getCurrentPosition() + backRight.getCurrentPosition())/2));
            errorY = (int)Math.abs(dy - Math.abs((frontRight.getCurrentPosition() + backLeft.getCurrentPosition())/2));
            op.telemetry.addData("targetX", dx);
            op.telemetry.addData("errorX", errorX);
            op.telemetry.addData("targetY", dy);
            op.telemetry.addData("errorY", errorY);
            op.telemetry.update();
            if(errorX>dx/2) {
                if(powX<maxPow * powCoeffX) powX = Math.min(powX + step * powCoeffX, maxPow * powCoeffX);
            }
            else {
                if(powX>minPow * powCoeffX) powX = Math.max(powX - step * powCoeffX, minPow * powCoeffX);
            }

            if(errorY>dx/2) {
                if(powY < maxPow * powCoeffY) powY = Math.min(powY + step * powCoeffY, maxPow * powCoeffY);
            }
            else {
                if(powY > minPow * powCoeffY) powY = Math.max(powY - step * powCoeffY, minPow * powCoeffY);
            }

            setPower(powX, powY, powY, powX);
            op.sleep(stepTime);
        }

        stop();	

}
    
    public void moveDist(double dist_cm, DistSensor sensor, double pow, LinearOpMode op) {

        if(!sensor.IsOn()) {
            op.telemetry.addLine("Cannot find DistanceSensor");
            op.telemetry.update();
            return;
        }

        stopAndResetEncoder();
        runUsingEncoder();

        double error,mull=1;

        error = sensor.getDistanceCM()-dist_cm;

        while (Math.abs(error)>0.8 && op.opModeIsActive())
        {
            error = sensor.getDistanceCM()-dist_cm;

            if(error>0)
            {
                mull = Math.max(0.25,Math.min(error/45,.6));
            }
            else if(error<0)
            {
                mull = Math.min(-0.25,Math.max(error/45,-.6));
            }
            double power = pow * mull;
            String devName = sensor.GetName();
            op.telemetry.addLine(devName);
            op.telemetry.addData("Distance (CM): ", sensor.getDistanceCM());
            op.telemetry.update();
            switch(devName) {
                case "rightDist":
                    setPower((float)-power, (float)power, (float)power, (float)-power);
                    break;
                case "frontDist":
                    setPower(-(float)power);
                    break;
                case "backDist":
                    setPower((float)power);
                    break;
                default:
                    break;
            }
        }
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
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() && op.opModeIsActive()) { op.idle(); }
        stop();
    }

    public void rotate(float angle, float pow, LinearOpMode op) {
        stopAndResetEncoder();
        runToPosition();

        int d = (int)(Radius * angle * DriveConstants.getTicksPerCM());

        setTargetPosition(d, -d, d, -d);
        setPower(pow);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() && op.opModeIsActive()) { op.idle(); }
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

        while(!onTarget && op.opModeIsActive()) {
            error = imu.getError(angle);
            if(Math.abs(error) < Math.PI/200) {
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
        while(!onTarget && op.opModeIsActive()) {
            error = imu.getError(nextAngle);

            if(Math.abs(error) < Math.PI/180) {
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
    public void runUsingEncoder() {
        if(AreWheelsActive()) {
            for(Motor m : motors) {
                m.runUsingEncoder();
            }
        }
    }

    public void stopAndResetEncoder() {
        if(AreWheelsActive()) {
            for(Motor m : motors) {
                m.stopAndResetEncoder();
            }
        }
    }

    public void runToPosition() {
        if(AreWheelsActive()) {
            for(Motor m : motors) {
                m.runToPosition();
            }
        }
    }

    public void Brake_ZeroPowerBehavior() {
        if(AreWheelsActive()) {
            for(Motor m : motors) {
                m.Brake();
            }
        }
    }

    public boolean AreAllWheelsBusy() {
        return frontRight.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }


    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn() && backLeft.IsOn() && backRight.IsOn();
    }

    @Override
    public PIDCoefficients getPIDCoefficients(DcMotor.RunMode runMode) {
        PIDFCoefficients coefficients = frontLeft.getPIDFCoefficients(runMode);
        return new PIDCoefficients(coefficients.p, coefficients.i, coefficients.d);
    }

    @Override
    public void setPIDCoefficients(DcMotor.RunMode runMode, PIDCoefficients coefficients) {
        for(Motor m : motors) {
            m.setPIDFCoefficients(runMode, new PIDFCoefficients(
                    coefficients.kP, coefficients.kI, coefficients.kD, getMotorVelocityF()));
        }
    }

    @Override
    public List<Double> getWheelPositions() {
        List<Double> wheelPositions = new ArrayList<>();
        RevBulkData bulkData = rb.getBulkData(rb.expansionHub);
        for (Motor m : motors) {
            wheelPositions.add((encoderTicksToInches(-m.getCurrentPosition(bulkData))));
        }
        return wheelPositions;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        setPower(-(float)v, -(float)v3, -(float)v1, -(float)v2);
    }

    @Override
    protected double getRawExternalHeading() {
        return imu.GetAngles().firstAngle;
    }
}
