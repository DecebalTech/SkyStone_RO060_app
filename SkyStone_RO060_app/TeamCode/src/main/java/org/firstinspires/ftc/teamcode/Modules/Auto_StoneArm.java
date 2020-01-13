package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Auto_StoneArm {

    private Servo_Pos grabber = new Servo_Pos();
    private Servo_Pos arm = new Servo_Pos();

    private enum grabberPositions{
        CATCH,
        RELEASE
    }

    private enum armPositions{
        UP,
        DOWN
    }

    private float[] armPositionValues = {0, .39f};
    private float[] grabberPositionValues = {0, 1};

    private grabberPositions grabberPosition = grabberPositions.RELEASE;
    private armPositions armPosition = armPositions.UP;

    public void Init(String _grabberName, String _armName, HardwareMap hwm){
        grabber.Init(_grabberName,hwm);
        arm.Init(_armName,hwm);
        grabberSetPosition(grabberPositions.CATCH);
        armSetPosition(armPositions.UP);
    }

    public void grabberSetPosition(grabberPositions _pos){
        grabberPosition = _pos;

        if(grabberPosition == grabberPositions.CATCH){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[0]);
        }
        else if(grabberPosition == grabberPositions.RELEASE){
            if(grabber.IsOn()) grabber.SetPosition(grabberPositionValues[1]);
        }
    }

    public void armSetPosition(armPositions _pos){
        armPosition = _pos;

        if(armPosition == armPositions.UP){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[0]);
        }
        else if(armPosition == armPositions.DOWN){
            if(arm.IsOn()) arm.SetPosition(armPositionValues[1]);
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

