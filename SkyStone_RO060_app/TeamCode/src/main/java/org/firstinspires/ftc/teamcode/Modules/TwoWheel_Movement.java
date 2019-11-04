package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static java.lang.Math.*;

public class TwoWheel_Movement {

    private Wheel frontLeft = new Wheel(), frontRight = new Wheel();
    private static String Names[] = {"frontLeft", "frontRight"};

    public void Init(HardwareMap hwm) {
        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);

        if(AreWheelsActive()) {
            frontRight.InvertDirection();
        }
    }
    public String Move(Gamepad gamepad1) {
        if(pow(gamepad1.left_stick_x, 2) > 0.1) {
            frontLeft.SetPower(-gamepad1.left_stick_x);
            frontRight.SetPower(gamepad1.left_stick_x);
        }
        else if(pow(gamepad1.left_stick_y, 2) > 0.1) {
            frontLeft.SetPower(gamepad1.left_stick_y);
            frontRight.SetPower(gamepad1.left_stick_y);
        }
        else StopMotors();

        return "Power: N/A" ;
    }

    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn();
    }

    public void StopMotors() {
        frontLeft.SetPower(0f);
        frontRight.SetPower(0f);
    }
}
