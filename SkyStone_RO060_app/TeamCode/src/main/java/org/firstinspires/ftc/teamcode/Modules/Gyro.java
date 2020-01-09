package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

@Deprecated
public class Gyro implements Modul {


    private ModernRoboticsI2cGyro gyro = null;
    private String Name;
    private boolean State;

    // These constants define the desired driving/control characteristics
    // The can/should be tweaked to suite the specific robot drive train.
    public static final double     DRIVE_SPEED             = 0.7;     // Nominal speed for better accuracy.
    public static final double     TURN_SPEED              = 0.5;     // Nominal half speed for better accuracy.

    public static final double     HEADING_THRESHOLD       = 1 ;      // As tight as we can make it with an integer gyro
    public static final double     P_TURN_COEFF            = 1;     // Larger is more responsive, but also less stable
    public static final double     P_DRIVE_COEFF           = 0.15;     // Larger is more responsive, but also less stable

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        try {
            gyro = (ModernRoboticsI2cGyro)hwm.gyroSensor.get(Name);
            SwitchState(true);
        }
        catch (Exception ex) {
            SwitchState(false);
        }
    }

    @Override
    public boolean IsOn() {
        return State;
    }

    @Override
    public void Kill() {

    }

    @Override
    public void SetName(String _Name) {
        Name = _Name;
    }

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
    }

    public void Calibrate() {
        gyro.calibrate();
    }

    public boolean isCalibrating() {
        return gyro.isCalibrating();
    }

    /**
     * getError determines the error between the target angle and the robot's current heading
     * @param   targetAngle  Desired angle (relative to global reference established at last Gyro Reset).
     * @return  error angle: Degrees in the range +/- 180. Centered on the robot's frame of reference
     *          +ve error means the robot should turn LEFT (CCW) to reduce error.
     */
    public double getError(double targetAngle) {
        double robotError;

        // calculate error in -179 to +180 range  (
        robotError = targetAngle - gyro.getIntegratedZValue();
        while (robotError > 180)  robotError -= 360;
        while (robotError <= -180) robotError += 360;
        return robotError;
    }

    public double getSteer(double error, double Pcoeff) {
        return Range.clip(error * Pcoeff , -1, 1);
    }


}
