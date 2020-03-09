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
        Init,
        Drive
    }

    public enum grabberPositions{
        CLOSED,
        CATCH,
        RELEASE,
        autoCatch
    }


    private float[] armPositionValues = {0, .1f, .15f, .55f ,.135f ,.18f,.62f,.60f};
    private float[] grabberPositionValues = {.6f, 0.4f , 0.13f , 0};

    private grabberPositions grabberPosition = grabberPositions.CLOSED;
    private armPositions armPosition = armPositions.Init;

    public void Init(String _grabberName, String _armName, HardwareMap hwm){
        grabber.Init(_grabberName,hwm);
        arm.Init(_armName,hwm);
        grabberSetPosition(grabberPositions.CLOSED);
        armSetPosition(armPositions.Drive);
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
        else if(armPosition == armPositions.Drive){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[7]);
        }
    }

    public String Update(Gamepad gamepad1) {
/*        if(gamepad1.dpad_right){
            armSetPosition(armPositions.Init);
        }
        else if(gamepad1.dpad_left){
            armSetPosition(armPositions.DOWN);
        }*/

/*
        if(gamepad1.dpad_left){
            grabberSetPosition(grabberPositions.RELEASE);
        }
        else if(gamepad1.dpad_right){
            grabberSetPosition(grabberPositions.CLOSED);
        }
*/

        return "Arm position: [" + armPosition.toString() + "] " + "Grabber position: [" + grabberPosition.toString() + "]";

    }

}

