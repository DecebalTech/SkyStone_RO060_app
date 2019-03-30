package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Test Touch Sensor (cuvaDown)", group="Teste")

public class Test extends LinearOpMode {

    //public DcMotor motor;
    public TouchSensor cuvaDown;
    public double power = 0.4;
    public HorizontalArm hArm = new HorizontalArm();
    //private String motorName = "motor";
    
    @Override public void runOpMode(){
        //motor= hardwareMap.dcMotor.get("motor");
        //cuvaDown = hardwareMap.touchSensor.get("cD");
        hArm.initHorizontalArm(hardwareMap);
        
        while(!isStarted()){
                idle();
            }
        
        while (opModeIsActive()){
            /*
            if (gamepad1.left_stick_y > 0.1){
                motor.setPower(power * gamepad1.left_stick_y);
            }
            if (gamepad1.left_stick_y < -0.1){
                motor.setPower(power * gamepad1.left_stick_y);
            }
            if (gamepad1.left_stick_y == 0){
                motor.setPower(0);
            }
            if(cuvaDown.isPressed())
            {
                telemetry.addLine("OUCH! OwO It hwuts, Mr Obama");
            }
            if(!cuvaDown.isPressed())
            {
                telemetry.addLine("Hewwo, Mr Obama!");
            }
            */
            if(gamepad1.x) {
                hArm.basculanta.resetPos();
            }
            telemetry.addData("Bas Pos:", hArm.basculanta.getCurrentPosition());
            telemetry.update();
        }
    }
}
