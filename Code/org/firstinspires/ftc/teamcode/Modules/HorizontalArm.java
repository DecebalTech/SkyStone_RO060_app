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

        int basCurrPos = basculanta.getCurrentPosition();
        if(gamepad2.x) {
            if(basCurrPos < maxBasculanta/3)
            {
                basculanta.setTargetPosition(maxBasculanta/4);
                basculanta.setPower(basculantaPower/4);
                basculanta.setTargetPosition(maxBasculanta/2);
                basculanta.setPower(basculantaPower/4);
                basculanta.setTargetPosition(maxBasculanta);
                basculanta.setPower(basculantaPower/4);
                basculantaPosition = "DOWN";
            }
            else if(basCurrPos > maxBasculanta/3 && basCurrPos < 2*maxBasculanta/3)
            {
                basculanta.setTargetPosition(maxBasculanta/2);
                basculanta.setPower(basculantaPower/4);
                basculanta.setTargetPosition(maxBasculanta);
                basculanta.setPower(basculantaPower/4);
                basculantaPosition = "DOWN";
            }
        }
        if(gamepad2.y) {
            if(basCurrPos < maxBasculanta/3)
            {
                basculanta.setTargetPosition(maxBasculanta/2);
                basculanta.setPower(basculantaPower/4);
                basculanta.setTargetPosition(maxBasculanta/4);
                basculanta.setPower(basculantaPower/4);
                basculantaPosition = "MIDDLE";
            }
            else if(basCurrPos > 2*maxBasculanta/3)
            {
                basculanta.setTargetPosition(maxBasculanta/4);
                basculanta.setPower(basculantaPower/4);
                basculantaPosition = "MIDDLE";
            }
        }
        if(gamepad2.b) {
            if(basCurrPos > maxBasculanta/6)
            {
                basculanta.setTargetPosition(-20); //usually, 0
                basculanta.setPower(basculantaPower/2);
                basculantaPosition = "UP";
            }
        }
        
        if(gamepad2.left_trigger>0)
            matura.setPower(10);
        else if(gamepad2.right_trigger>0)
            matura.setPower(-10);
        else matura.setPower(0);

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
