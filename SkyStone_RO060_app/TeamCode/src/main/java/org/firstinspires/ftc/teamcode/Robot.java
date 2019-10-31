package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.*;

public class Robot {
    public Brat brat = new Brat();
    public Movement movement;

    public Robot(HardwareMap hwm) {
        brat.Init("Brat", hwm);
        movement = new Movement(hwm);
    }

    public void LinearUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if(movement.AreWheelsActive())
            movement.Move(gamepad1);
        else
        {}

    }

    

}
