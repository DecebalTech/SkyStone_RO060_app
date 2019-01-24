package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;

@Autonomous(name = "Lander", group = "Autonomie")

public class AutonomousMode_Lander extends LinearOpMode{
    
    Movement mov = new Movement();
    vuforia_class vf = new vuforia_class();
    Hook hook = new Hook();
    Marker marker = new Marker();
    
    @Override
    public void runOpMode() throws InterruptedException{
        
        initRobot();
        int gTemp = -1, gold = 0, img = -1; // -1 - scan error; 0 - left; 1 - center; 2 - right;
        while(!isStarted())
        {
            idle();
        }
        hook.moveHookWithEncoders(hook.maxHookPos);
        sleep(100);
        mov.moveWithEncoders(-450, 0.25);
    }
    
    public void initRobot() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        hook.initHook(hardwareMap);
        hook.hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marker.initMarker(hardwareMap);
        marker.marker.setPosition(0.5);
    }
}
