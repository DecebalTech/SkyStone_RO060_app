package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;
import org.firstinspires.ftc.teamcode.Modules.*;

@Autonomous(name = "Autonomous Test", group = "Autonomous")

public class AutonomousTest extends LinearOpMode {

    Movement mov = new Movement();
    vuforia_class vf = new vuforia_class();

    @Override public void runOpMode() throws InterruptedException {

        initRobot();
        int gTemp = -1, gold = 0; // -1 - scan error; 0 - left; 1 - center; 2 - right;
        while(!isStarted()) {
            gTemp = vf.scan(this);
            if(gTemp!=-1) gold = gTemp;
        }
        telemetry.addData("Gold place", gold);
        telemetry.update();

        vf.tfod.shutdown();

        mov.rotateWithEncoders(1080, this);
        sleep(100);
        //getPosition;
        sleep(1000);
        mov.moveWithEncoders(1000, this); // WORK IN PROGRESS
        sleep(1000);
        mov.moveWithEncoders(-1000, this); // WORK IN PROGRESS
        sleep(1000);

    }

    public void initRobot() {
        mov.initMovement(hardwareMap, "moto1", "moto2", "moto3");
        vf.initVuforia();
        vf.initTfod(hardwareMap);
        vf.tfod.activate();
    }
}
