package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class stone_Rotate {


    private Servo_Pos stone_Rotate = new Servo_Pos();

    public enum armPositions{
        forward, // in fata
        side // in lateral
    }




    private float[] armPositionValues = {.03f, .395f};



    private armPositions armPosition = armPositions.forward;

    public void Init(String _armName, HardwareMap hwm){

        stone_Rotate.Init(_armName,hwm);

        armSetPosition(armPositions.forward);
    }



    public void armSetPosition(armPositions _pos){
        armPosition = _pos;

        if(armPosition == armPositions.forward){
            if(stone_Rotate.IsOn()) stone_Rotate.SetPosition(armPositionValues[0]);
        }
        else if(armPosition == armPositions.side){
            if(stone_Rotate.IsOn()) stone_Rotate.SetPosition(armPositionValues[1]);
        }
    }

    public String UpdateS(Gamepad gamepad1) {

        if(gamepad1.dpad_up){
            armSetPosition(armPositions.forward);
        }
        else if(gamepad1.dpad_down){
            armSetPosition(armPositions.side);
        }



        return "stone position: [" + armPosition.toString() + "] ";

    }

}

