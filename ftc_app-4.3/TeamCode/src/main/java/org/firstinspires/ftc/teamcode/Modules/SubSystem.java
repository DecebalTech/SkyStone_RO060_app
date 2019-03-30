package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class SubSystem {

    public OpMode op;
    public HardwareMap hwm;
    public Gamepad gamepad1, gamepad2;

    public void update() {}

    public void init() {}
}
