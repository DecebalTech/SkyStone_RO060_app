package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="Test Senzor Distanță", group = "Teste")

public class TestSenzorDistanta extends LinearOpMode {

    private DistanceSensor sensor;
    
    @Override public void runOpMode() {
        
        sensor = hardwareMap.get(DistanceSensor.class, "cuvaDown");
        
        while(opModeIsActive()) 
        {
            telemetry.addData("Dist", sensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
        
    }
}
