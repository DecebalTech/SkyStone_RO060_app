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
        int gTemp = -1, gold = 0, img = -1; // -1 - scan error; 0 - left; 1 - center; 2 - right;
        while(!isStarted()) {
            gTemp = vf.scan_minerals(this);
            if(gTemp!=-1) gold = gTemp;
        }
        vf.tfod.shutdown();
        telemetry.addData("Gold place", gold);
        telemetry.update();

        //mov.moveWithEncoders(1000, this);
        telemetry.addLine("M-am dus in fata. :)");
        telemetry.update();
        sleep(1000);
        //mov.rotateWithEncoders(1080, this);
        telemetry.addLine("M-am invartit xD");
        telemetry.update();
        sleep(1000);
        vf.initVuforiaForTarget();
        for(int i=0;i<300;i++)
        {
            img = vf.scan_for_target(this);
            telemetry.addData("Citii de atatea ori", i);
            telemetry.addLine("OwO");
            telemetry.update();
            sleep(10);
        }
        //vf.targetsRoverRuckus.shutdown();

        telemetry.addData("Wall", img);
        telemetry.update();

        sleep(5000);

    }

    public void initRobot() {
        mov.initMovement(hardwareMap);
        vf.initVuforia();
        vf.initTfod(hardwareMap);
        vf.tfod.activate();
    }
}
