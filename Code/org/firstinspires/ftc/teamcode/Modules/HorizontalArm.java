package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
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

    private String railName = "rail";
    private String basculantaName = "basculanta";
    private String maturaName = "matura";

    private boolean basculantaIsPressed = false;

    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        if(gamepad2.right_stick_y!=0)
            rail.setPower(gamepad2.right_stick_y);
        else rail.setPower(0);

        if(gamepad2.y && !basculantaIsPressed)
        {
            basculantaIsPressed = true;
            if(basculanta.getCurrentPosition() >-250)
            {
                basculanta.setTargetPosition(-500);
                basculanta.setPower(0.2);
            }
            else
            {
                basculanta.setTargetPosition(0);
                basculanta.setPower(0.2);
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
    }

    public void initHorizontalArm(HardwareMap hwm) {
        rail = hwm.dcMotor.get(railName);
        basculanta = hwm.dcMotor.get(basculantaName);
        basculanta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        matura = hwm.crservo.get(maturaName);
    }

}
