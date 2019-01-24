package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class HorizontalArm
{
    public DcMotor rail, basculanta;
    public CRServo matura1, matura2;
    public Servo cuva;
    public TouchSensor cuvaDown;
    
    boolean ok = false;

    private String railName = "rail";
    private String basculantaName = "basculanta";
    private String matura1Name = "matura1";
    private String matura2Name = "matura2";
    private String cuvaName = "cuva";
    private String cuvaDownName = "cuvaDown";

    private boolean basculantaIsPressed = false;

    public int maxBasculanta = 800; //to be calculated after re-assembling the robot (installing a mechanical stop)
    private double basculantaPower = 0.7;
    public String basculantaPosition = "UP";

    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        while (!ok){
            cuva.setPosition(0.3);
            ok=true;
        }
        
        if(gamepad2.right_stick_y!=0)
            rail.setPower(-gamepad2.right_stick_y);
        else rail.setPower(0);
        
        /*
        if(gamepad1.left_stick_y > 0.1)
        {
            basculanta.setPower(0.3);
        }
        else if (gamepad1.left_stick_y < -0.1)
        {
            basculanta.setPower(-0.15);
        }
        else basculanta.setPower(0.2);
        */

        int basCurrPos = basculanta.getCurrentPosition();
        
        if(gamepad2.x) { //Mid1
            // du-te la maxBasculanta/2
            basculanta.setTargetPosition(maxBasculanta/2);
            basculanta.setPower(basculantaPower/4);
            
            basculantaPosition = "MID1";
 
        }
        if(gamepad2.y) { //up
            // du-te la 0
            basculanta.setTargetPosition(0);
            basculanta.setPower(basculantaPower/4);
            while(basculanta.isBusy()) { }
            basculanta.setPower(0);
            basculantaPosition = "UP";
        }
        if(gamepad2.a) { //down
            // du-te la maxBasculanta
            basculanta.setTargetPosition(maxBasculanta);
            basculanta.setPower(basculantaPower/4);
            while(basculanta.isBusy()) { }
            basculanta.setPower(0);
            basculantaPosition = "DOWN";
        }
        if(gamepad2.b) { //Mid2
            //du-te la maxBasculanta/4
            basculanta.setTargetPosition(maxBasculanta/4);
            basculanta.setPower(basculantaPower/4);

            basculantaPosition = "MID2";
        }
        
        
        if(gamepad2.right_trigger > 0.1) {
            matura1.setPower(1);
            matura2.setPower(-1);
        }
        if(gamepad2.left_trigger > 0.1) {
            matura1.setPower(-1);
            matura2.setPower(1);
        }
        if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0)
            {
                matura1.setPower(0);
                matura2.setPower(0);
            }

        if(gamepad2.right_bumper) {
            cuva.setPosition(1);
        }
        if(gamepad2.left_bumper) {
            cuva.setPosition(0.3);
        }
        
        if(cuvaDown.isPressed()) {
            maxBasculanta = basculanta.getCurrentPosition();
        }
    }

    public void initHorizontalArm(HardwareMap hwm) {
        rail = hwm.dcMotor.get(railName);
        basculanta = hwm.dcMotor.get(basculantaName);
        basculanta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        basculanta.setTargetPosition(0);
        basculanta.setPower(1);
        matura1 = hwm.crservo.get(matura1Name);
        matura2 = hwm.crservo.get(matura2Name);
        cuva = hwm.servo.get(cuvaName);
        
        cuvaDown = hwm.touchSensor.get(cuvaDownName);
    }
}
