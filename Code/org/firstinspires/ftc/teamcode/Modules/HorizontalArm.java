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
    public DcMotor matura;
    public Servo cuva;

    private String railName = "rail";
    private String basculantaName = "basculanta";
    private String maturaName = "matura";
    private String cuvaName = "cuva";

    private boolean basculantaIsPressed = false;

    private int maxBasculanta = 800; //to be calculated after re-assembling the robot (installing a mechanical stop)
    private double basculantaPower = 0.7;
    public String basculantaPosition = "UP";

    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        if(gamepad2.right_stick_y!=0)
            rail.setPower(gamepad2.right_stick_y);
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
                basculanta.setTargetPosition(25); //usually, 0
                basculanta.setPower(basculantaPower/4);
                basculantaPosition = "UP";
            }
        }

        if(gamepad2.left_trigger>0)
            matura.setPower(1);
        else if(gamepad2.right_trigger>0)
            matura.setPower(-1);
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
        basculanta.setTargetPosition(0);
        basculanta.setPower(1);
        matura = hwm.dcMotor.get(maturaName);
        cuva = hwm.servo.get(cuvaName);
    }

}
