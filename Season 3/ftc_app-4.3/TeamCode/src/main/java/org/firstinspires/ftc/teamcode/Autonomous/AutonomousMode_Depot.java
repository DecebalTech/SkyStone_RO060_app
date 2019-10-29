package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;

@Autonomous(name = "[Depot]", group = "Autonomie")

public class AutonomousMode_Depot extends LinearOpMode{
    
    Movement mov = new Movement();
    vuforia_class vf = new vuforia_class();
    Hook hook = new Hook();
    Marker marker = new Marker();
    HorizontalArm horizontalArm = new HorizontalArm();
    
    @Override
    public void runOpMode() throws InterruptedException{
        
        initRobot();
        int gTemp = -1, gold = -1; // -1 - scan error; 0 - left; 1 - center; 2 - right;
        vf.initTfod(hardwareMap);
        vf.tfod.activate();
        while(!isStarted())
        {
            gTemp = vf.scan_minerals(this);
            if(gTemp!=-1) gold = gTemp;
            sleep(50);
        }
        
        vf.tfod.shutdown();
        telemetry.addData("Gold place", gold);
        telemetry.update();
        hook.moveHookWithEncoders(hook.maxHookPos); //dă-te jos din corcoduș 🍒
        sleep(100);
        mov.move_forwards_cm(-8, 0.5); //dehatch
        sleep(100);
        mov.strafe(350, 0.5); // strafe left
        sleep(100);
        switch(gold) {
            case 0:
                mov.rotate(-1050, 0.5);
                sleep(100);
                mov.move_forwards_cm(95, 0.15);
                sleep(100);
                mov.move_forwards_cm(-15, 0.2);
                sleep(100);
                mov.rotate(-1000,0.3);//rotate towards crater and depot
                sleep(100);
                mov.strafe(-375,0.3);//strafe right towards the wall
                sleep(100);
                mov.strafe(170,0.3);//strafe left a bit
                sleep(100);
                mov.move_forwards_cm(-40,0.3);
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
                mov.rotate(20,0.2);//rotate right towards crater 
                sleep(100);
                mov.move_forwards_cm(100, 0.3); //move forward 1 m
                sleep(100);
                mov.strafe(-230, 0.3); //strafe right into wall to correct movement
                sleep(100);
                mov.strafe(25, 0.3); //strafe left so as npot to graze
                sleep(100);
                mov.move_forwards_cm(20, 0.3); //go forwards to crater
                sleep(100);
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                break;
            case 1:
                mov.rotate(-750, 0.5); //rotate to face middle mineral
                sleep(100);
                mov.move_forwards_cm(80, 0.2); //move gold into depot
                sleep(100);
                mov.move_forwards_cm(-80, 0.2); //move to previous position
                sleep(100);
                mov.rotate(1160, 0.3);//rotate right //was 1220
                sleep(100);
                mov.strafe(-330, 0.2); // strafe right to avoid white
                sleep(100);
                mov.move_forwards_cm(-100, 0.5);//la culoar
                sleep(100);
                mov.strafe(1500, 0.3); // strafe left to depot
                sleep(100);
                mov.rotate(770,0.4);//rotate right to leave marker
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
                mov.rotate(120,0.2);//rotate towards crater 
                sleep(100);
                mov.strafe(-150, 0.3); //strafe right into wall to correct direction
                sleep(100);
                mov.strafe(35,0.3);//strafe left so as not to graze 
                sleep(100);
                mov.move_forwards_cm(110, 0.25);//move towards crater,was 165
                sleep(100);
                mov.strafe(-100,0.3);//strafe right
                sleep(100);
                mov.strafe(45,0.3);//strafe left so as not to graze 
                sleep(100);
                mov.move_forwards_cm(35, 0.2);//move towards crater
                sleep(100);
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                //Dab on em' haters 🌚🌝🌈🔥🔥🔥🔥 mada 
                
                break;
            case 2:
                mov.rotate(-450, 0.5);
                sleep(100);
                mov.move_forwards_cm(75, 0.2);
                sleep(100);
                mov.move_forwards_cm(-75, 0.2);
                sleep(100);
                mov.rotate(770, 0.2);//rotate towards wall
                sleep(100);
                mov.strafe(-210, 0.2); // strafe right to avoid white
                sleep(100);
                mov.move_forwards_cm(-100, 0.2);//la culoar
                sleep(100);
                mov.rotate(65,0.2); //error correction
                sleep(100);
                mov.strafe(1600, 0.3); // strafe left to depot
                sleep(100);
                mov.rotate(770,0.5);//rotate right to leave marker
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
                mov.strafe(-300, 0.5);
                sleep(100);
                mov.rotate(60, 0.3);//rotate towards crater 
                sleep(100);
                mov.strafe(45,0.3);//don't graze 
                sleep(100);
                mov.move_forwards_cm(150, 0.3);//move towards crater
                sleep(100);
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                //Dab on em' haters 🌚🌝🌈🔥🔥🔥🔥
                break;
            default:
                 mov.rotate(-450, 0.5);
                sleep(100);
                mov.move_forwards_cm(75, 0.2);
                sleep(100);
                mov.move_forwards_cm(-75, 0.2);
                sleep(100);
                mov.rotate(770, 0.2);//rotate towards wall
                sleep(100);
                mov.strafe(-210, 0.2); // strafe right to avoid white
                sleep(100);
                mov.move_forwards_cm(-100, 0.2);//la culoar
                sleep(100);
                mov.rotate(65,0.2); //error correction
                sleep(100);
                mov.strafe(1600, 0.3); // strafe left to depot
                sleep(100);
                mov.rotate(770,0.5);//rotate right to leave marker
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
                mov.strafe(-300, 0.5);
                sleep(100);
                mov.rotate(60, 0.3);//rotate towards crater 
                sleep(100);
                mov.strafe(45,0.3);//don't graze 
                sleep(100);
                mov.move_forwards_cm(150, 0.3);//move towards crater
                sleep(100);
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                break;
        }
    }
    
    public void initRobot() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        hook.initHook(hardwareMap);
        hook.hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        marker.initMarker(hardwareMap);
        marker.marker.setPosition(0.45);
        horizontalArm.initHorizontalArm(hardwareMap);
        horizontalArm.autonomousInit();
        vf.initVuforia();
    }
}
