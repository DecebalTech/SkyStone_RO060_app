package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Modules.OldRoboChassisModules.Marker;

public class Robot {
    //public Brat brat = new Brat();
    public Movement movement;
    public ServoBratePrindere servoBratePrindere;
    public PrindereCub prindereCub;

    public Robot(HardwareMap hwm) {
        //brat.Init("Brat", hwm);
        movement = new Movement();
        movement.Init(hwm);

        servoBratePrindere = new ServoBratePrindere();
        servoBratePrindere.Init("ServoPrindereLeft", "ServoPrindereRight", hwm);

        prindereCub = new PrindereCub();
        prindereCub.Init("GripMotorLeft", "GripMotorRight", hwm);
    }

    public void LinearUpdate(Gamepad gamepad1, Gamepad gamepad2, OpMode op) {
        if(movement.AreWheelsActive())
            op.telemetry.addLine(movement.Move(gamepad1));
        else
        { op.telemetry.addLine("Wheels not defined/connected.");}

        if(servoBratePrindere.IsOn()) {
            op.telemetry.addLine(servoBratePrindere.Move(gamepad1));
        }
        else op.telemetry.addLine("ServoPrindereBrate is not defined/connected.");

        if(prindereCub.IsOn()) {
            op.telemetry.addLine(prindereCub.UpdateGrips(gamepad2));
        }
        else op.telemetry.addLine("PrindereCub is not defined/connected.");

    }

}
