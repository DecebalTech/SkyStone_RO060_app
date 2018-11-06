package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp( name = "BreaslaProgramatorilor", group = "Teste")

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
            mov.updateMovement(gamepad1, gamepad2);
        }
    }

    public void initRobot() {
        mov.initMovement(hardwareMap, "moto1", "moto2");
    }
}
