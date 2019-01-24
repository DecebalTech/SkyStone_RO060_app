package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;

@Autonomous(name="Crater", group="Autonomie")

/*
-

*/

public class AutonomousMode_Crater extends LinearOpMode {

    Movement mov = new Movement();
    vuforia_class vf = new vuforia_class();
    Hook hook = new Hook();
    Marker marker = new Marker();
    HorizontalArm horizontalArm = new HorizontalArm();
    
    @Override public void runOpMode() {
        
        initRobot();
        
        while(!isStarted())
            idle();
        
        
        hook.moveHookWithEncoders(hook.maxHookPos);
        sleep(100);
        mov.moveWithEncoders(-450, 0.25);
        /*
        sleep(250);
        hook.moveHookWithEncoders(0); //nu mai retragem hook-ul
        */
        sleep(100);
        mov.sidewaysWithEncoders(1250, 0.5);
        sleep(250);
        mov.moveWithEncoders(-4300, 0.5);
        sleep(500);
        mov.rotateWithEncoders(2360, 0.5);
        sleep(300);
        mov.sidewaysWithEncoders(4000, 0.5);
        sleep(500);
        marker.marker.setPosition(0);
        sleep(1500);
        marker.marker.setPosition(0.5);
        sleep(100);
        mov.rotateWithEncoders(1450, 0.5);
        sleep(300);
        mov.moveWithEncoders(-8200, 0.75);
        
    }
    
    public void initRobot() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        hook.initHook(hardwareMap);
        hook.hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marker.initMarker(hardwareMap);
        horizontalArm.initHorizontalArm(hardwareMap);
    }
}
