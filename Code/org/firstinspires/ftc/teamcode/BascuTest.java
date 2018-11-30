package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class BascuTest extends LinearOpMode {

    DcMotor basculanta;
    double basculantaPower = 0.5;
    int maxBasculanta = 450;
    String basculantaPosition;
    
    @Override public void runOpMode() {
        basculanta = hardwareMap.dcMotor.get("basculanta");
        basculanta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        while(!isStarted())
        {
            idle();
        }
        
        while(opModeIsActive())
        {
            int basCurrPos = basculanta.getCurrentPosition();
            telemetry.addData("Pos", basculantaPosition);
            telemetry.addData("Enc pos", basCurrPos);
            /*
            if(gamepad2.x)
            {
                if(basCurrPos < maxBasculanta/3)
                {
                    basculanta.setTargetPosition(maxBasculanta/4);
                    basculanta.setPower(basculantaPower/4);
                    basculanta.setTargetPosition(maxBasculanta/2);
                    basculanta.setPower(basculantaPower/4);
                    basculanta.setTargetPosition(maxBasculanta);
                    basculanta.setPower(basculantaPower/4);
                    basculantaPosition = "DOWN";
                }
                else if(basCurrPos > maxBasculanta/3 && basCurrPos < 2*maxBasculanta/3)
                {
                    basculanta.setTargetPosition(maxBasculanta/2);
                    basculanta.setPower(basculantaPower/4);
                    basculanta.setTargetPosition(maxBasculanta);
                    basculanta.setPower(basculantaPower/4);
                    basculantaPosition = "DOWN";
            }
            if(gamepad2.y) {
                if(basCurrPos < maxBasculanta/3)
                {
                    basculanta.setTargetPosition(maxBasculanta/2);
                    basculanta.setPower(basculantaPower/4);
                    basculanta.setTargetPosition(maxBasculanta/4);
                    basculanta.setPower(basculantaPower/4);
                    basculantaPosition = "MIDDLE";
                }
                else if(basCurrPos > 2*maxBasculanta/3)
                {
                    basculanta.setTargetPosition(maxBasculanta/4);
                    basculanta.setPower(basculantaPower/4);
                    basculantaPosition = "MIDDLE";
                }
            }
            
        }*/
    }
        
        
        
    }
}
