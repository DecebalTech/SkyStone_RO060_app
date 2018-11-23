package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
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
    public CRServo matura;
    public Servo cuva;

    private String railName = "rail";
    private String basculantaName = "basculanta";
    private String maturaName = "matura";
    private String cuvaName = "cuva";

    private boolean basculantaIsPressed = false;
    
    private int maxBasculanta = 750;
    private double basculantaPower = 0.5;
    public String basculantaPosition = "UP";
    
    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        if(gamepad2.right_stick_y!=0)
            rail.setPower(gamepad2.right_stick_y);
        else rail.setPower(0);

        if(gamepad2.y && !basculantaIsPressed)
        {
            basculantaIsPressed = true;
            if(basculanta.getCurrentPosition() < maxBasculanta/2)
            {
                basculanta.setTargetPosition(maxBasculanta/4);
                basculanta.setPower(basculantaPower/4);
                basculanta.setTargetPosition(maxBasculanta/2);
                basculanta.setPower(basculantaPower/4);
                basculanta.setTargetPosition(maxBasculanta);
                basculanta.setPower(basculantaPower/4);
                basculantaPosition = "DOWN";
            }
            else
            {
                basculanta.setTargetPosition(0);
                basculanta.setPower(basculantaPower);
                basculantaPosition = "UP";
                ///suicide
            }
        }
        if(!gamepad2.y)
            basculantaIsPressed = false;

        if(gamepad2.x) {
            matura.setPower(-10);
        }
        if(gamepad2.a) {
            matura.setPower(0);
        }
        if(gamepad2.b) {
            matura.setPower(10);
        }
        if(gamepad2.right_bumper) {
            cuva.setPosition(1);
        }
        if(gamepad2.left_bumper) {
            cuva.setPosition(0.4);
        }
    }

    public void initHorizontalArm(HardwareMap hwm) {
        rail = hwm.dcMotor.get(railName);
        basculanta = hwm.dcMotor.get(basculantaName);
        basculanta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        matura = hwm.crservo.get(maturaName);
        cuva = hwm.servo.get(cuvaName);
    }

}
