package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Testare Movement (poziții, etc)", group="Teste")

public class TestMovement extends LinearOpMode {

    Movement mov = new Movement();
    
    @Override public void runOpMode() {
        mov.initMovement(hardwareMap);
        
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            mov.updateMovement(gamepad1, gamepad2);
            
            telemetry.addData("moto1 pos", mov.moto1.getCurrentPosition());
            telemetry.addData("moto2 pos", mov.moto2.getCurrentPosition());
            telemetry.addData("moto3 pos", mov.moto3.getCurrentPosition());
            telemetry.addData("moto4 pos", mov.moto4.getCurrentPosition());
            telemetry.update();
            
            if(gamepad1.x)
                mov.resetEncoders();
        }
    }
}
