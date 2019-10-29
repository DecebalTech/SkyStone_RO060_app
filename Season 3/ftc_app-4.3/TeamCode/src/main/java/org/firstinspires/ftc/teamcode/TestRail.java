package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Test Rail", group="Teste")

public class TestRail extends LinearOpMode {

    HorizontalArm horizontalArm = new HorizontalArm();
    DcMotor rail;
    
    @Override public void runOpMode() {
        initRobot();
        
        rail = horizontalArm.rail.rail;
        rail.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        while(!isStarted()) {
            idle();
        }
        
        while(opModeIsActive()) {
            telemetry.addData("rail pos:", rail.getCurrentPosition());
            telemetry.update();
            
            rail.setPower(gamepad1.right_stick_y);
            if(gamepad1.a) {
                rail.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rail.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                
            }
            if(gamepad1.x) {
                rail.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rail.setTargetPosition(-3500);
                rail.setPower(0.6);
                while(rail.isBusy())
                    idle();
                rail.setPower(0);
                rail.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    
            }
        }
    }
    
    public void initRobot() {
        horizontalArm.initHorizontalArm(hardwareMap);
        
    }
}
