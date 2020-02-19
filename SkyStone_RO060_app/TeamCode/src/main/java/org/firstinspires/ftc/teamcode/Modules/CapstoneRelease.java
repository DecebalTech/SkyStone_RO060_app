package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

import java.util.Set;

public class CapstoneRelease {

    public Servo_Pos CapRelease = new Servo_Pos();

    public enum ServoPositions {
        Catch,
        Release
    };

    private ServoPositions servoPosition = ServoPositions.Catch;
    private float[] servoPos = {.08f,.8f};


    public void Init(String CapRelease_, HardwareMap hwm){
        CapRelease.Init(CapRelease_, hwm);
        SetPos(ServoPositions.Catch);
    }

    void SetPos(ServoPositions _pos){
        servoPosition = _pos;
        if(servoPosition == ServoPositions.Catch){
             if(CapRelease.IsOn()) CapRelease.SetPosition(servoPos[0]);
        }
        if(servoPosition == ServoPositions.Release){
             if(CapRelease.IsOn()) CapRelease.SetPosition(servoPos[1]);
        }
    }

    public String Update(Gamepad gamepad2){
        if(gamepad2.b) SetPos(ServoPositions.Catch);
        if(gamepad2.x) SetPos(ServoPositions.Release);
        return "CapstoneRelease position: [" + servoPosition.toString() + "]";
    }

}
