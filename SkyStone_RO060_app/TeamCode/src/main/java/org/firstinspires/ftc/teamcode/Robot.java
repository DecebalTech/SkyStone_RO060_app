package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.CapstoneRelease;
import org.firstinspires.ftc.teamcode.Modules.DistSensor;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.MarkerArm;
import org.firstinspires.ftc.teamcode.Modules.Movement;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.openftc.revextensions2.ExpansionHubEx;
import org.openftc.revextensions2.RevBulkData;

public class Robot {
    public Movement movement;
    public PrindereCub prindereCub;
    public MarkerArm markerArm;
    public FoundationServos foundationServos;
    public Auto_StoneArm stoneArm;
    public DistSensor rightDist, frontDist, backDist;
    public CapstoneRelease capstoneRelease;


    public ExpansionHubEx controlHub, expansionHub;
    public RevBulkData bulkData;

    public Robot(HardwareMap hwm, LinearOpMode op, boolean autonomous) {
        //brat.Init("Brat", hwm);
        movement = new Movement();
        movement.Init(hwm, this, autonomous);
        op.sleep(50);
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

        capstoneRelease = new CapstoneRelease();
        capstoneRelease.Init("capRelease", hwm);

        if(autonomous) {
            rightDist = new DistSensor();
            rightDist.Init("rightDist", hwm);

            frontDist = new DistSensor();
            frontDist.Init("frontDist", hwm);

            backDist = new DistSensor();
            backDist.Init("backDist", hwm);
        }

        controlHub = hwm.get(ExpansionHubEx.class, "Expansion Hub 1 (controlHub)");
        expansionHub = hwm.get(ExpansionHubEx.class, "Expansion Hub 2 (expansionHub)");
        expansionHub.setAllI2cBusSpeeds(ExpansionHubEx.I2cBusSpeed.STANDARD_100K);
        controlHub.setAllI2cBusSpeeds(ExpansionHubEx.I2cBusSpeed.STANDARD_100K);
    }

    public void LinearUpdate(Gamepad gamepad1, Gamepad gamepad2, OpMode op) {

        op.telemetry.addLine(movement.Move(gamepad1));
        op.telemetry.addLine(movement.Encoders(gamepad1));

        if(prindereCub.IsOn()) {
            op.telemetry.addLine(prindereCub.UpdateGrips(gamepad1));
        }
        else op.telemetry.addLine("PrindereCub is not defined/connected.");

        op.telemetry.addLine(markerArm.UpdateMarkerArm(gamepad2));

        op.telemetry.addLine(foundationServos.Update(gamepad1));

        /*if(rightDist.IsOn()) {
            op.telemetry.addData("RightSensor (cm)", rightDist.getDistanceCM());
        }
        if(frontDist.IsOn()) {
            op.telemetry.addData("FrontSensor (cm)", frontDist.getDistanceCM());
        }
        if(backDist.IsOn()) {
            op.telemetry.addData("BackSensor (cm)", backDist.getDistanceCM());
        }*/

        op.telemetry.addLine(stoneArm.Update(gamepad2));
        op.telemetry.addLine(capstoneRelease.Update(gamepad2));

    }

    public RevBulkData getBulkData(ExpansionHubEx hub) {
        bulkData = hub.getBulkInputData();
        return bulkData;
    }

}
