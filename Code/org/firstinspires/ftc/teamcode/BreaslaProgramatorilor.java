package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp( name = "BreaslaProgramatorilor", group = "Teste")

public class BreaslaProgramatorilor extends LinearOpMode{

    Movement mov = new Movement();
    HorizontalArm horizontalArm = new HorizontalArm();
    VerticalArm verticalArm = new VerticalArm();

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
            telemetry.addData("Turbo", mov.turbo);
            telemetry.addData("BascPos", horizontalArm.basculantaPosition);
            telemetry.addData("BascPos_Encoder", horizontalArm.basculanta.getCurrentPosition());
            telemetry.update();
        }
    }

    public void initRobot() {
        mov.initMovement(hardwareMap);
        horizontalArm.initHorizontalArm(hardwareMap);
        verticalArm.initVerticalArm(hardwareMap);
    }
}
