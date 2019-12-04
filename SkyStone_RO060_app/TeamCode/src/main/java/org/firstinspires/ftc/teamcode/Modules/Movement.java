package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Movement {

    private Motor frontLeft = new Motor(), frontRight = new Motor(), backLeft = new Motor(), backRight = new Motor();
    private static String Names[] = {"frontLeft", "frontRight", "backLeft", "backRight"};

    private float[] TurboMultipliers = {0.25f, 0.5f, 1};
    private int TurboIndex = 1;

    public void Init(HardwareMap hwm) {
        frontLeft.Init(Names[0], hwm);
        frontRight.Init(Names[1], hwm);
        backLeft.Init(Names[2], hwm);
        backRight.Init(Names[3], hwm);

        if(AreWheelsActive()) {
            frontRight.InvertDirection();
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

        frontLeft.SetPower((powX - gamepad1.right_stick_x)*TurboMultipliers[TurboIndex]);
        frontRight.SetPower((powY + gamepad1.right_stick_x)*TurboMultipliers[TurboIndex]);
        backLeft.SetPower((powY - gamepad1.right_stick_x)*TurboMultipliers[TurboIndex]);
        backRight.SetPower((powX + gamepad1.right_stick_x)*TurboMultipliers[TurboIndex]);


        return "PowX: " + powX + "\nPowY: " + powY + "\nTurbo: " + TurboIndex;
    }

    public boolean AreWheelsActive() {
        return frontLeft.IsOn() && frontRight.IsOn() && backLeft.IsOn() && backRight.IsOn();
    }
}
