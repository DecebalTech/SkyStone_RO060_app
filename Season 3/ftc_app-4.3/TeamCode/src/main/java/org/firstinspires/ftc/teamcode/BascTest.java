package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test Basculanta", group = "Teste")

public class BascTest extends LinearOpMode {

     public DcMotor basc;
    @Override public void runOpMode() {
        basc = hardwareMap.dcMotor.get("basculanta");
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            if(gamepad1.right_stick_y!=0)
                basc.setPower(gamepad1.right_stick_y);
            else basc.setPower(0);
            
            telemetry.addData("Power", gamepad1.right_stick_y);
            telemetry.update();
        }
    }
}
