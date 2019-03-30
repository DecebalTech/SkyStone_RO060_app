package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Testare Movement (poziții, etc)", group="Teste")

public class TestMovement extends LinearOpMode {

    Movement mov = new Movement();
    //Marker marker = new Marker();
    
    @Override public void runOpMode() {
        mov.initMovement(hardwareMap);
        mov.wheels.runWithoutEncoder();
        //marker.initMarker(hardwareMap);
        //marker.up();
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            mov.updateMovement(gamepad1, gamepad2);
            
            telemetry.addData("stangaFata pos", mov.wheels.stangaFata.getCurrentPosition());
            telemetry.addData("dreaptaFata pos", mov.wheels.dreaptaFata.getCurrentPosition());
            telemetry.addData("stangaSpate pos", mov.wheels.stangaSpate.getCurrentPosition());
            telemetry.addData("dreaptaSpate pos", mov.wheels.dreaptaSpate.getCurrentPosition());
            telemetry.update();
            
            if(gamepad1.x) {
                mov.wheels.resetEncoders();
                mov.wheels.runWithoutEncoder();
            }
        }
    }
}
