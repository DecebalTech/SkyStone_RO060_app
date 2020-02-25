package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Auto_StoneArm {

    private Servo_Pos grabber = new Servo_Pos();
    private Servo_Pos arm = new Servo_Pos();

    public enum armPositions{
        DOWN, // sus
        AUTO, // ca sa treaca sub bridge
        MID, // deplasare autonomie
        UP,  // prindere stone
        PICKandGO, // pentru pus pe fundatie
        FOUNDATION, // special pentru colectare stone FOUNDATION
        Init
    }

    public enum grabberPositions{
        CLOSED,
        CATCH,
        RELEASE,
        autoCatch
    }


    private float[] armPositionValues = {0, .1f, .15f, .55f ,.135f ,.18f,.7f};
    private float[] grabberPositionValues = {0, 0.2f , 0.28f , .6f};

    private grabberPositions grabberPosition = grabberPositions.CLOSED;
    private armPositions armPosition = armPositions.Init;

    public void Init(String _grabberName, String _armName, HardwareMap hwm){
        grabber.Init(_grabberName,hwm);
        arm.Init(_armName,hwm);
        grabberSetPosition(grabberPositions.CLOSED);
        armSetPosition(armPositions.Init);
    }

    public void grabberSetPosition(grabberPositions _pos){
        grabberPosition = _pos;

        if(grabberPosition == grabberPositions.CLOSED){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[0]);
        }
        else if(grabberPosition == grabberPositions.CATCH){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[1]);
        }
        else if(grabberPosition == grabberPositions.RELEASE){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[2]);
        }
        else if(grabberPosition == grabberPositions.autoCatch){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[3]);
        }
    }

    public void armSetPosition(armPositions _pos){
        armPosition = _pos;

        if(armPosition == armPositions.DOWN){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[0]);
        }
        else if(armPosition == armPositions.AUTO){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[1]);
        }
        else if(armPosition == armPositions.MID){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[2]);
        }
        else if(armPosition == armPositions.UP){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[3]);
        }
        else if(armPosition == armPositions.PICKandGO){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[4]);
        }
        else if(armPosition == armPositions.FOUNDATION){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[5]);
        }
        else if(armPosition == armPositions.Init){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[6]);
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

