package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;

@Autonomous(name = "Depot", group = "Autonomie")

/*

- backwards 300
- left 500
- rotates to the left 1650
- forwards 4350
- rotates to the right 1320
- marker timey
- rotates to the left 400
- backwards 6800

*/

/*
[NU ÎNCĂ; DEOCAMDATĂ FACEM O AUTONOMIE SIMPLIFICATĂ, RENUNȚÂND LA MINERALS]
Autonomous Logic (for the Depot placement):

- (ABSOLUTELY MANDATORY, WE DIDN'T DO THIS AND EVERYTHING WENT TERRIBLY WRONG) dab for good luck
- hit "Init"
- hit "Play"
- lower the robot with the Hook
- move backwards to unhook the Hook
- retract the hook (optional)
- move sideways to the left
- rotate 90 degrees to right
- move forwards in front of the minerals
- go sideways scanning each mineral
- move the mineral (by going forwards then backwards)
- move sideways to the left then go to the depot to leave the marker
- leave marker (0 to leave it; 0.5 to bring the servo back up, leaving the marker)
- move backwards into the crater
- (mandatory if everything works) take one FATASS DAB  👌💪

*/
public class AutonomousMode_Depot extends LinearOpMode{
    
    Movement mov = new Movement();
    vuforia_class vf = new vuforia_class();
    Hook hook = new Hook();
    Marker marker = new Marker();
    HorizontalArm horizontalArm = new HorizontalArm();
    
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
        mov.moveWithEncoders(-450, 0.5);
        /*
        sleep(250);
        hook.moveHookWithEncoders(0); //nu mai retragem hook-ul
        */
        sleep(100);
        mov.sidewaysWithEncoders(500, 0.5);
        sleep(250);
        mov.rotateWithEncoders(1650, 0.5);
        sleep(500);
        /* 
        // mineral part - gave it up because we have no time for it yet - must be completed asap
        mov.sidewaysWithEncoders(-1080, 0.1);
        vf.initVuforia();
        vf.initTfod(hardwareMap);
        vf.tfod.activate();
        for(int i=1;i<=100;i++)
        {
            if(vf.scanMineral) 
        }
        for(int i=1;i<=300;i++)
        {
            gTemp = vf.scan_minerals(this);
            if(gTemp!=-1) gold = gTemp;
            telemetry.addData("Am scanat minerale de atatea ori", i);
            telemetry.update();
            sleep(10);
        }
        vf.tfod.shutdown();
        switch(gold){
            case 0:
                telemetry.addData("Left", gold);
                break;
            case 1:
                telemetry.addData("Middle", gold);
                break;
            case 2:
                telemetry.addData("Right", gold);
                break;
            default:
                telemetry.addData("Scan error", gold);
                break;
        }
        */
        
        mov.moveWithEncoders(4200, 0.5);
        sleep(500);
        mov.rotateWithEncoders(-1320, 0.25);
        sleep(500);
        mov.moveWithEncoders(500, 0.25);
        sleep(300);
        marker.marker.setPosition(0);
        sleep(1000);
        marker.marker.setPosition(0.5);
        sleep(100);
        
        mov.rotateWithEncoders(340, 0.25);
        sleep(100);
        
        mov.moveWithEncoders(-7500, 0.75);
        
    }
    
    /*public void initRobotAuto() {
        vuforia.initTfod(hardwareMap);
        mov.initMovement(hardwareMap);
    }*/
    
    public void initRobot() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        hook.initHook(hardwareMap);
        hook.hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marker.initMarker(hardwareMap);
        marker.marker.setPosition(0.5);
        horizontalArm.initHorizontalArm(hardwareMap);
    }
}
