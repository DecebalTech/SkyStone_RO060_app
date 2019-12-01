package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Modules.Marker;

public class Robot {
    //public Brat brat = new Brat();
    public TwoWheel_Movement movement;
    public Brat brat;
public Cub cub;
public Marker marker;
    public Robot(HardwareMap hwm) {
        //brat.Init("Brat", hwm);
        movement = new TwoWheel_Movement();
        movement.Init(hwm);
        brat = new Brat();
        brat.Init("Brat", hwm);
        cub=new Cub();
        cub.Init("Cub", hwm);
        marker=new Marker();
        marker.Init("Marker",hwm);
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
   if(cub.IsOn())
       op.telemetry.addLine("Servo position: " + cub.Move(gamepad1));
   else
   { op.telemetry.addLine("Servo is not defined/connected.");}
        if(marker.IsOn())
            op.telemetry.addLine("Servo position: " + marker.Move(gamepad1));
        else
        { op.telemetry.addLine("Servo is not defined/connected.");}
     op.telemetry.addLine("Turbo:"+ movement.GetTurbo());
    }

}
