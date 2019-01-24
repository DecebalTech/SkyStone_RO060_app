package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Testare Roti (cu poziții)", group="Teste")

public class TestRoti extends LinearOpMode {
    
    Movement mov = new Movement();
    
    @Override public void runOpMode() {
        
        initRobot();
        
        while(!isStarted())
        {
            idle();
        }
        
        while(opModeIsActive())
        {
            if(gamepad1.x)
                mov.moto1.setPower(1);
            else if (gamepad1.y)
                mov.moto2.setPower(1);
            else if (gamepad1.a)
                mov.moto3.setPower(1);
            else if (gamepad1.b)
                mov.moto4.setPower(1);
            else 
            {
                mov.moto1.setPower(0);
                mov.moto2.setPower(0);
                mov.moto3.setPower(0);
                mov.moto4.setPower(0);
            }
            
            telemetry.addData("moto1 pos", mov.moto1.getCurrentPosition());
            telemetry.addData("moto2 pos", mov.moto2.getCurrentPosition());
            telemetry.addData("moto3 pos", mov.moto3.getCurrentPosition());
            telemetry.addData("moto4 pos", mov.moto4.getCurrentPosition());
            telemetry.update();
            
            if(gamepad1.dpad_up)
                mov.resetEncoders();
            
        }
    }
    
    public void initRobot() {
        mov.initMovement(hardwareMap);
    }
}
    
