package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Modules.*;

@Autonomous (name = "B_STONES", group = "BLUE")
public class BLUE_STONES extends LinearOpMode {

    private Robot rb = null;
    private final int time = 300;
    private Scanner scanner = new Scanner();
    private int scanResult = -1;

    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);

        while(!isStarted()) {
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }
        /*
        Autonomous Logic - Blue Stones:

        1. Scan
        2. Go Rightwards next to stones
        3.a If the scan was Left, go forwards to catch the first stone
        3.b If the scan was Middle, stay
        3.c If the scan was Right, go backwards to catch the third stone
        4. Lower the AutoArm and catch the stone
        5. Move to Foundation and place the stone

         */
        int time=1;




        switch (scanResult) {
            case 0:
                telemetry.addLine("1 sau 4 pe zar");

                break;
            case 2:
                telemetry.addLine("3 sau 6 pe zar");

                break;
            default:
                //telemetry.addLine("🍆, dar mergem pe mijloc");
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION);
                sleep(time);
                rb.movement.moveDist(15,1f,this);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN);
                sleep(time);
                rb.movement.moveDist(12,1f,this);
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH);
                sleep(time);
                rb.movement.moveDist(10,1f,this);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO);
                sleep(time);
                rb.movement.moveCM((float)Math.PI,20,1f,this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2,193,1f,this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2.5f,70,1f,this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/3f,10,1f,this);
                sleep(time);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION);
                sleep(time);
                rb.movement.moveCM((float)Math.PI,-20,1f,this);
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(300);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.MID);
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH);
                sleep(time);

                rb.movement.moveCM((float)Math.PI,15,1f,this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/3,-40,1f,this);
                sleep(time);

                rb.movement.moveCM((float)Math.PI/2,-315,1f,this);
                sleep(time);
                rb.movement.moveDist(17,1f,this);
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN);
                sleep(time);
                rb.movement.moveDist(11,1f,this);
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH);
                sleep(300);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO);
                sleep(time);
                rb.movement.moveCM((float)Math.PI,18,1f,this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2,275,1f,this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2.5f,75,1f,this);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION);
                sleep(time);
                rb.movement.moveCM((float)Math.PI,-10,1f,this);
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(300);
                /*
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN);
                sleep(1000);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH);
                sleep(500);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP);
                sleep(1000);
                rb.movement.moveCM((float)IMU.LEFTWARD_ANGLE, 100, 1f, this);
                sleep(time);
                 */

                break;
        }
        telemetry.update();






    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
    }
}
