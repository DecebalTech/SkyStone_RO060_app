package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class BreaslaProgramatorilor extends LinearOpMode{

    Movement mov = new Movement();

    double motorPower = 1, minPower = 0.5, maxPower=2.5;
    @Override public void runOpMode() {

        initRobot();

        while(!isStarted())
        {
            idle();
        }
        while(opModeIsActive())
        {
          telemetry.addLine("yeethaw");
          telemetry.update();
          if(gamepad1.left_stick_y!=0){
              mov.move(gamepad1.left_stick_y);
          }
          if (gamepad1.left_stick_x!=0){
              mov.rotate(gamepad1.left_stick_x);
          }
          if(gamepad1.left_stick_x==0 && gamepad1.left_stick_y==0)
          {
              mov.stop();
          }
        }
    }

    public void initRobot() {
        mov.moto1 = hardwareMap.dcMotor.get("moto1");
        mov.moto2 = hardwareMap.dcMotor.get("moto2");
    }
}
