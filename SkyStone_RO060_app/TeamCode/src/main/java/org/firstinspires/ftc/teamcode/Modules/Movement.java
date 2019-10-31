package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Movement {

    private Wheel frontLeft, frontRight, backLeft, backRight;
    private String Names[] = {"frontLeft", "frontRight", "backLeft", "backRight"};

    public Movement(HardwareMap hwm) {
        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);
        frontRight.InvertDirection();
        backLeft.Init(Names[2], hwm);
        backRight.Init(Names[3], hwm);
        backRight.InvertDirection();
    }

    public String Move(Gamepad gamepad1) {
        float angle, r, powX, powY;

        angle = (float)(Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI/2);
        r = (float)Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);

        powX = (float)(Math.cos(angle)*r);
        powY = (float)(Math.sin(angle)*r);

        frontLeft.SetPower(powY);
        frontRight.SetPower(powX);
        backLeft.SetPower(powX);
        backRight.SetPower(powY);
        return "PowX: " + powX + "\nPowY: " + powY;
    }

    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn() && backLeft.IsOn() && backRight.IsOn();
    }
}
