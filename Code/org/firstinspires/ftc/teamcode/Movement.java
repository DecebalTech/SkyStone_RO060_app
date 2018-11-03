package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;


public class Movement {

    public DcMotor moto1;
    public DcMotor moto2;

    public void move(float power) {
        moto1.setPower(-power);
        moto2.setPower(power);
    }

    public void rotate(float power) {
        moto1.setPower(-power);
        moto2.setPower(-power);
    }

    public void stop() {
        moto1.setPower(0);
        moto2.setPower(0);
    }
}
