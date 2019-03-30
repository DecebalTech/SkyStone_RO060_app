package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp( name = "***Drive Control (dă pă ăsta)***", group = "Control")

public class BreaslaProgramatorilor extends LinearOpMode{

    Movement mov = new Movement();
    HorizontalArm horizontalArm = new HorizontalArm();
    VerticalArm verticalArm = new VerticalArm();
    Hook hook = new Hook();
    Marker marker = new Marker();
    
    @Override public void runOpMode() {
        initRobot();
        
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);
        while(!isStarted())
        {
            idle();
        }
        while(opModeIsActive())
        {
            mov.updateMovement(gamepad1, gamepad2);
            horizontalArm.updateArm(gamepad1, gamepad2);
            verticalArm.updateArm(gamepad1, gamepad2);
            hook.updateHook(gamepad1, gamepad2);
            if(gamepad1.dpad_left)
                marker.down();
            if(gamepad1.dpad_right)
                marker.up();
                
            if(verticalArm.getPosition() >= verticalArm.halfPos && !horizontalArm.cuva.auto) {
                horizontalArm.cuva.setState(HorizontalArm.Cuva.State.MID);
                horizontalArm.cuva.auto = true;
            }
            if(verticalArm.getPosition() < verticalArm.halfPos) horizontalArm.cuva.auto = false;
            
            telemetry.addData("Turbo", mov.turbo);
            telemetry.addData("BascPos_Encoder", horizontalArm.basculanta.getCurrentPosition());
            telemetry.addData("BascPos_MaxPos", horizontalArm.basculanta.maxBasculanta);
            telemetry.addData("BascPos_STATE", horizontalArm.basculanta.getState());
            telemetry.addData("hook Pos", hook.hook.getCurrentPosition());
            telemetry.addData("Hook_MaxPos", hook.maxHookPos);
            telemetry.addData("rail pos", horizontalArm.rail.getCurrentPosition());
            telemetry.addData("Vertical arm pos", verticalArm.arm.getCurrentPosition());
            telemetry.addLine("Muie PSD");
            telemetry.update();
        }
    }

    public void initRobot() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        mov.wheels.runWithoutEncoder();
        horizontalArm.initHorizontalArm(hardwareMap);
        verticalArm.initVerticalArm(hardwareMap);
        hook.initHook(hardwareMap);
        hook.hook.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        marker.initMarker(hardwareMap);
        marker.up();
        
    }
}
