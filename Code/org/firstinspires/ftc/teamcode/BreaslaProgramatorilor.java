package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class BreaslaProgramatorilor extends LinearOpMode{

    Movement mov = new Movement();

    @Override public void runOpMode() {
        initRobot();

        while(!isStarted())
        {
            idle();
        }
        while(opModeIsActive())
        {
            telemetry.addLine("pola");
            telemetry.update();

            mov.updateMovement(gamepad1, gamepad2);

        }
    }

    public void initRobot() {
        mov.moto1 = hardwareMap.dcMotor.get("moto1");
        mov.moto2 = hardwareMap.dcMotor.get("moto2");
    }
}
