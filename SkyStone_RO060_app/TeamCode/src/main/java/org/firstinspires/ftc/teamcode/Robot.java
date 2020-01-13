package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.MarkerArm;
import org.firstinspires.ftc.teamcode.Modules.Movement;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;

public class Robot {
    //public Brat brat = new Brat();
    public Movement movement;
    //public ServoBratePrindere servoBratePrindere;
    public PrindereCub prindereCub;
    public MarkerArm markerArm;
    public FoundationServos foundationServos;
    public Auto_StoneArm stoneArm;

    public Robot(HardwareMap hwm, LinearOpMode op) {
        //brat.Init("Brat", hwm);
        movement = new Movement();
        movement.Init(hwm);
        op.sleep(50);
        //servoBratePrindere = new ServoBratePrindere();
        //servoBratePrindere.Init("ServoPrindereLeft", "ServoPrindereRight", hwm, op);
        op.sleep(250);
        prindereCub = new PrindereCub();
        prindereCub.Init("GripMotorLeft", "GripMotorRight", hwm);
        op.sleep(250);
        markerArm = new MarkerArm();
        markerArm.Init("MarkerArmRotation", "MarkerArmExtender", "MarkerGrab", "MarkerPivot", "MagneticSwitch", hwm);

        foundationServos = new FoundationServos();
        foundationServos.Init("FoundationLeft", "FoundationRight", hwm);

        stoneArm = new Auto_StoneArm();
        stoneArm.Init("grabber", "arm", hwm);
    }

    public void LinearUpdate(Gamepad gamepad1, Gamepad gamepad2, OpMode op) {

        op.telemetry.addLine(movement.Move(gamepad1));
        op.telemetry.addLine(movement.Encoders(gamepad1));

        /*
        if(servoBratePrindere.IsOn()) {
            op.telemetry.addLine(servoBratePrindere.Move(gamepad1));
        }
        else op.telemetry.addLine("ServoPrindereBrate is not defined/connected.");
        */
        if(prindereCub.IsOn()) {
            op.telemetry.addLine(prindereCub.UpdateGrips(gamepad1));
        }
        else op.telemetry.addLine("PrindereCub is not defined/connected.");

        op.telemetry.addLine(markerArm.UpdateMarkerArm(gamepad2));

        op.telemetry.addLine(foundationServos.Update(gamepad1));

    }

}
