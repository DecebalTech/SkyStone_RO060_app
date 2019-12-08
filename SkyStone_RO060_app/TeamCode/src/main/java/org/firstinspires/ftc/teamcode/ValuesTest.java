package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Modules.OldRoboChassisModules.Marker;

@TeleOp
public class ValuesTest extends LinearOpMode {

    private Servo GripLeft, GripRight, MarkerPivot;

    private Servo FoundationLeft, FoundationRight;

    public void Init() {
        GripLeft = hardwareMap.servo.get("ServoPrindereLeft");
        GripRight = hardwareMap.servo.get("ServoPrindereRight");
        MarkerPivot = hardwareMap.servo.get("MarkerPivot");

        FoundationLeft = hardwareMap.servo.get("FoundationLeft");
        FoundationRight = hardwareMap.servo.get("FoundationRight");

        GripLeft.setPosition(.50f);
        sleep(500);
        GripRight.setPosition(.50f);

        MarkerPivot.setPosition(0.1f);

        //GripLeft.setPosition(1f);
        //GripRight.setPosition(0f);
    }
    @Override
    public void runOpMode() throws InterruptedException {

        Init();
        while(!isStarted()) {
            idle();
        }

        while(opModeIsActive()) {
            telemetry.addData("GripLeftMotor Position:", GripLeft.getPosition());
            telemetry.addData("GripRightMotor Position:", GripRight.getPosition());
            telemetry.addData("MarkerPivot Position:", MarkerPivot.getPosition());

            if(gamepad2.y) { //open
                GripLeft.setPosition(.60);
                GripRight.setPosition(.40);
            }
            if(gamepad2.a) { //closed
                GripLeft.setPosition(.55);
                GripRight.setPosition(.45);
            }

            if(gamepad2.dpad_down) {
                FoundationLeft.setPosition(.15f);
                FoundationRight.setPosition(.15f);
            }
            if(gamepad2.dpad_up) {
                FoundationLeft.setPosition(.8f);
                FoundationRight.setPosition(.8f);
            }
            
            telemetry.update();
        }
    }
}
