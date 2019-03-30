package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp( name = "Testare Hook", group = "Teste")

public class HookTest extends LinearOpMode {
    
    //with hook module class
    
    Hook hook = new Hook();
    
    @Override public void runOpMode() {
    
        hook.initHook(hardwareMap);
        hook.hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.hook.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            telemetry.addData("Hook Pos:", hook.hook.getCurrentPosition());
            telemetry.update();
            //hook.updateHook(gamepad1, gamepad2);
            if(gamepad1.right_stick_y!=0)
                hook.hook.setPower(gamepad1.right_stick_y);
            else hook.hook.setPower(0);
        }
        
    }
    
    //direct motor control
    /*
    public DcMotor hook;
    
    @Override public void runOpMode() {
        hook = hardwareMap.dcMotor.get("hook");
        
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            if(gamepad1.right_stick_y!=0)
                hook.setPower(gamepad1.right_stick_y);
            else hook.setPower(0);
            
            telemetry.addData("Power", gamepad1.right_stick_y);
            telemetry.update();
        }
    }
    */
}
