package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Auto_StoneArm {

    private Servo_Pos grabber = new Servo_Pos();
    private Servo_Pos arm = new Servo_Pos();

    public enum armPositions{
        UP, // sus
        MID, // ca sa treaca sub bridge
        AUTO, // deplasare autonomie
        DOWN,  // prindere stone
        FOUNDATION, // pentru pus pe fundatie
        PICKandGO // special pentru colectare stone
    }

    public enum grabberPositions{
        CATCH,
        RELEASE,
        OPEN
    }


    private float[] armPositionValues = {0, .1f, .15f, .405f ,.30f ,.25f};
    private float[] grabberPositionValues = {0, 0.2f , 0.35f};

    private grabberPositions grabberPosition = grabberPositions.RELEASE;
    private armPositions armPosition = armPositions.UP;

    public void Init(String _grabberName, String _armName, HardwareMap hwm){
        grabber.Init(_grabberName,hwm);
        arm.Init(_armName,hwm);
        grabberSetPosition(grabberPositions.CATCH);
        armSetPosition(armPositions.MID);
    }

    public void grabberSetPosition(grabberPositions _pos){
        grabberPosition = _pos;

        if(grabberPosition == grabberPositions.CATCH){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[0]);
        }
        else if(grabberPosition == grabberPositions.RELEASE){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[1]);
        }
        else if(grabberPosition == grabberPositions.OPEN){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[2]);
        }
    }

    public void armSetPosition(armPositions _pos){
        armPosition = _pos;

        if(armPosition == armPositions.UP){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[0]);
        }
        else if(armPosition == armPositions.MID){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[1]);
        }
        else if(armPosition == armPositions.AUTO){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[2]);
        }
        else if(armPosition == armPositions.DOWN){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[3]);
        }
        else if(armPosition == armPositions.FOUNDATION){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[4]);
        }
        else if(armPosition == armPositions.PICKandGO){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[5]);
        }
    }

    public String Update(Gamepad gamepad1) {
        if(gamepad1.dpad_up){
            armSetPosition(armPositions.UP);
        }
        else if(gamepad1.dpad_down){
            armSetPosition(armPositions.DOWN);
        }

        if(gamepad1.dpad_left){
            grabberSetPosition(grabberPositions.RELEASE);
        }
        else if(gamepad1.dpad_right){
            grabberSetPosition(grabberPositions.CATCH);
        }

        return "Arm position: [" + armPosition.toString() + "] " + "Grabber position: [" + grabberPosition.toString() + "]";

    }

}

