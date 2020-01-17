package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class IMU implements Modul {

    private BNO055IMU imu = null;
    private String Name;
    private boolean State;

    public static double FORWARD_ANGLE = Math.PI/2;
    public static double BACKWARD_ANGLE = 3 * Math.PI/2;
    public static double LEFTWARD_ANGLE = Math.PI;
    public static double RIGHTWARD_ANGLE = 0;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        try {
            SetName(_Name);
            imu = hwm.get(BNO055IMU.class, Name);
            SwitchState(true);
        }
        catch (Exception ex) {
            SwitchState(false);
        }

        if(IsOn()) {
            BNO055IMU.Parameters param = new BNO055IMU.Parameters();
            param.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            param.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            param.calibrationDataFile = "BNO055IMUCalibration.json";
            param.loggingEnabled      = true;
            param.loggingTag          = "IMU";
            param.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            imu.initialize(param);

            imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        }
    }

    @Override
    public boolean IsOn() {
        return State;
    }

    @Override
    public void Kill() {
        imu.stopAccelerationIntegration();
    }

    @Override
    public void SetName(String _Name) {
        Name = _Name;
    }

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
    }

    public Orientation GetAngles() {
        return imu.getAngularOrientation();
    }

    public double getError(double targetAngle) {
        double robotError;

        // calculate error in -179 to +180 range  (
        robotError = targetAngle - GetAngles().firstAngle;
        while (robotError > Math.PI)  robotError -= 2*Math.PI;
        while (robotError < -Math.PI) robotError += 2*Math.PI;
        return robotError;
    }

    public double getSteer(double error, double Pcoeff) {
        if(error<0) {
            return Range.clip(error * Pcoeff, -0.9, -0.1);
        }
        else {
            return Range.clip(error * Pcoeff, 0.1, 0.9);
        }
    }
}
