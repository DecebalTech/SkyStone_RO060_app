package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;



@Autonomous(name = "[Crater Double Sampling] **NU-I GATA**", group = "Autonomie")

public class CraterDoubleSampling extends LinearOpMode {
    
    Movement mov = new Movement();  
    vuforia_class vf = new vuforia_class();
    Hook hook = new Hook();
    Marker marker = new Marker();
    HorizontalArm horizontalArm = new HorizontalArm();
    
    @Override 
    public void runOpMode() throws InterruptedException{
        
        initRobot();
        int gTemp = -1, gold = 0; // -1 - scan error; 0 - left; 1 - center; 2 - right;
        vf.initTfod(hardwareMap, 0.4f);
        vf.tfod.activate();
        while(!isStarted())
        {
            gTemp = vf.scan_minerals(this);
            if(gTemp!=-1) gold = gTemp;
        }
        vf.tfod.shutdown();
        telemetry.addData("Gold place", gold);
        telemetry.update();
        
        hook.moveHookWithEncoders(hook.maxHookPos); //dă-te jos din bananier 🍌🍌🍌
        sleep(100);
        mov.move_forwards_cm(-7, 0.5); //dehatch
        sleep(100);
        mov.strafe(350, 0.5); // strafe left
        sleep(100);
        switch(gold) {
            case 0:
                mov.rotate(-1050, 0.5); //rotate to face left gold mineral
                sleep(100);
                mov.move_forwards_cm(53, 0.2); //move gold
                sleep(100);
                mov.move_forwards_cm(-13, 0.3); //move to previous location
                sleep(100);
                mov.rotate(770,0.3);//rotate right
                sleep(100);
                mov.move_forwards_cm(-20, 0.2);
                sleep(100);
                mov.strafe(1450, 0.20);//strafe left towards the wall
                sleep(100);
                mov.strafe(-120,0.3); //wall correction
                sleep(100);
                mov.move_forwards_cm(-140, 0.2); //move backwards to depot
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
                mov.rotate(-60,0.2);//rotate towards crater 
                sleep(100);
                mov.move_forwards_cm(80, 0.3); //move towards crater
                sleep(100);
                mov.strafe(200, 0.15); //strafe left into wall to correct direction
                sleep(100);
                mov.move_forwards_cm(60, 0.3); //move towards crater
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                break;
            case 1:
                mov.rotate(-750, 0.5); //rotate to face middle mineral
                sleep(100);
                mov.move_forwards_cm(50, 0.75); //move gold 
                sleep(100);
                mov.move_forwards_cm(-50, 0.2); //move to previous position
                sleep(100);
                mov.rotate(1120, 0.3); //rotate right
                sleep(100);
                mov.strafe(-230, 0.2); // strafe right to avoid silver mineral
                sleep(100);
                mov.move_forwards_cm(-115, 0.2);//la culoar 1.0
                sleep(100);
                mov.strafe(-900, 0.3); // strafe right to depot
                sleep(100);
                mov.move_forwards(-150, 0.2); // move backwards a bit to correct movement
                sleep(100);
                mov.strafe(-1000, 0.3); //strafe right to depot
                sleep(100);
                mov.rotate(-777, 0.3); // rotate to leave marker
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
                mov.rotate(-100,0.7);//rotate towards crater 
                sleep(100);
                mov.move_forwards_cm(80, 0.75); //move towards crater
                sleep(100);
                mov.strafe(250, 0.25); //strafe left into wall to correct direction
                sleep(100);
                mov.strafe(-50,1); //strafe right so as to not hit the wall 
                sleep(100);
                mov.move_forwards_cm(50, 0.75); //move towards crater
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                
                break;
            case 2:
                mov.rotate(-400, 0.5);
                sleep(100);
                mov.move_forwards_cm(65, 0.25);
                sleep(100);
                mov.move_forwards_cm(-65, 0.25);
                sleep(100);
                mov.strafe(200, 0.15);
                sleep(100);
                mov.move_forwards_cm(-20, 0.25);
                sleep(100);
                mov.strafe(1700, 0.3);//strafe left towards the wall
                sleep(100);
                mov.move_forwards_cm(-140, 0.5); //move backwards to depot
                sleep(100);
                marker.down();
                sleep(1500);
                marker.up();
                sleep(100);
               // mov.rotate(-60,0.2);//rotate towards crater 
                //sleep(100);
                mov.move_forwards_cm(70, 0.3); //move towards crater
                sleep(100);
                mov.strafe(-600,0.5);// strafe right to get gold
                sleep(100);
                mov.strafe(600,0.5);//strafe left back to lane
                sleep(100);
                mov.strafe(50, 0.15); //strafe left into wall to correct direction
                sleep(100);
                mov.strafe(-50, 0.15); //strafe right to stop grazing
                sleep(100);
                mov.move_forwards_cm(90, 0.3); //move towards crater
                horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);//basc down
                sleep(100);
                
                break;
            default:
            
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
        marker.marker.setPosition(0.5);
        horizontalArm.initHorizontalArm(hardwareMap);
        horizontalArm.autonomousInit();
        vf.initVuforia();
    }
}
