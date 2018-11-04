package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import org.firstinspires.ftc.teamcode.*;

@Autonomous(name = "Autonomous Test", group = "Autonomous")

public class AutonomousTest extends LinearOpMode {

    Movement mov = new Movement();

    @Override public void runOpMode() throws InterruptedException {

        initRobot();

        while(!isStarted())
            idle();

        sleep(1000);
        mov.moveWithEncoders(1000, this); // WORK IN PROGRESS
        sleep(1000);
        mov.moveWithEncoders(-1000, this); // WORK IN PROGRESS
        sleep(1000);

    }

    public void initRobot() {
        mov.initMovement(hardwareMap, "moto1", "moto2");
    }
}
