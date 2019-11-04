package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.*;

public class Robot {
    //public Brat brat = new Brat();
    public TwoWheel_Movement movement;
    public Brat brat;

    public Robot(HardwareMap hwm) {
        //brat.Init("Brat", hwm);
        movement = new TwoWheel_Movement();
        movement.Init(hwm);
        brat = new Brat();
        brat.Init("Brat", hwm);
    }

    public void LinearUpdate(Gamepad gamepad1, Gamepad gamepad2, OpMode op) {
        if(movement.AreWheelsActive())
            op.telemetry.addLine(movement.Move(gamepad1));
        else
        { op.telemetry.addLine("Wheels not defined/connected.");}

        if(brat.IsOn())
            op.telemetry.addLine("Servo position: " + brat.Move(gamepad1));
        else
        { op.telemetry.addLine("Servo is not defined/connected.");}
    }

}
