package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class autoRotate {


    private Servo_Pos autoRotate = new Servo_Pos();

    public enum armPositions{
        forward, // in fata
        side ,// in latera
        init
    }




    private float[] armPositionValues = {0.064f, .63f,1f};



    private armPositions armPosition = armPositions.forward;

    public void Init(String _armName, HardwareMap hwm){

        autoRotate.Init(_armName,hwm);

        armSetPosition(armPositions.side);
    }



    public void armSetPosition(armPositions _pos){
        armPosition = _pos;

        if(armPosition == armPositions.forward){
            if(autoRotate.IsOn()) autoRotate.SetPosition(armPositionValues[0]);
        }
        else if(armPosition == armPositions.side){
            if(autoRotate.IsOn()) autoRotate.SetPosition(armPositionValues[1]);
        }
        else if(armPosition == armPositions.init){
            if(autoRotate.IsOn()) autoRotate.SetPosition(armPositionValues[2]);
        }
    }

    public String UpdateS(Gamepad gamepad1) {
/*
        if(gamepad1.dpad_up){
            armSetPosition(armPositions.forward);
        }
        else if(gamepad1.dpad_down){
            armSetPosition(armPositions.side);
        }
*/



        return "stone position: [" + armPosition.toString() + "] ";

    }

}

