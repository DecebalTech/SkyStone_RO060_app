package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp( name = "Drive Control (dă pă ăsta)", group = "Control")

public class BreaslaProgramatorilor extends LinearOpMode{

    Movement mov = new Movement();
    HorizontalArm horizontalArm = new HorizontalArm();
    VerticalArm verticalArm = new VerticalArm();
    Hook hook = new Hook();

    @Override public void runOpMode() {
        initRobot();
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
            telemetry.addData("Turbo", mov.turbo);
            /*telemetry.addData("BascPos", horizontalArm.basculantaPosition);
            telemetry.addData("BascPos_Encoder", horizontalArm.basculanta.getCurrentPosition());
            telemetry.addData("BascPos_MaxPos", horizontalArm.maxBasculanta);
            */telemetry.addData("hook Pos", hook.hook.getCurrentPosition());
            telemetry.addData("Hook_MaxPos", hook.maxHookPos);/*
            telemetry.addData("motor1 power ", mov.moto1.getPower());
            telemetry.addData("motor2 power ", mov.moto2.getPower());
            telemetry.addData("motor3 power ", mov.moto3.getPower());
            telemetry.addData("motor4 power ", mov.moto4.getPower());
            */
            //telemetry.addLine("ha vaț uitat 👌😜🌈🌈");
            telemetry.update();
        }
    }

    public void initRobot() {
        mov.initMovement(hardwareMap);
        //mov.initControl(); //just for the driver controlled phase
        horizontalArm.initHorizontalArm(hardwareMap);
        verticalArm.initVerticalArm(hardwareMap);
        hook.initHook(hardwareMap);
    }
}
