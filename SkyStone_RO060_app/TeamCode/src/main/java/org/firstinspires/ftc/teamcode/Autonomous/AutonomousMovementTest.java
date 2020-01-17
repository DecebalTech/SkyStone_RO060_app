package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous
public class AutonomousMovementTest extends LinearOpMode {

    private Robot rb;
    private Auto_StoneArm stoneArm = new Auto_StoneArm();

    public void runOpMode() throws InterruptedException{
        initRobot();
        int time=1;
        while(!isStarted()) idle();

        /*while(opModeIsActive()){
            stoneArm.Update(gamepad1);
        }*/
        /*
        sleep(time);

        rb.movement.rotateIMURelative(Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 100, 1f, this);
        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);

         */


        /*rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
        sleep(500);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN);
        sleep(time);
        rb.movement.moveDist(14,1f,this);
        sleep(time);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH);
        sleep(time);
        rb.movement.moveDist(10,1f,this);
        sleep(time);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO);
        sleep(time);*/
/*        rb.movement.moveCM(0, 30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -30, 1f, this);
        sleep(time);
        rb.movement.moveCM(0, -30, 1f, this);
        sleep(time);


        rb.movement.moveCM((float)Math.PI, -30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 30, 1f, this);
        sleep(time);*/
/*       // telemetry.addData("distance in cm", rb.distSensor.getDistanceCM());
*//*
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
        sleep(time);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(time);

        rb.movement.moveDist(15,1,this);  // mergem la 15 cm fata de skystone
        sleep(time);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
        sleep(time);

        rb.movement.moveDist(12,1f,this); // ne apropiem si mai mult de skystone
        sleep(time);

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
        sleep(time);

        rb.movement.moveDist(10,1f,this); // ne apropiem de skystone in timp ce servo-ul se inchide pentru a salva timp
        sleep(time);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // ridicam mana(bratul) cu skystone
        sleep(time);

        rb.movement.moveCM((float)Math.PI,20,1f,this); // strafe catre perete
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2,200,1f,this); // mergem spre fundatie
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2.5f,70,1f,this); // ceva miscari in diagonala ca sa ajungem langa fundatie
        sleep(time);

        rb.movement.moveCM((float)Math.PI/3f,20,1f,this); // ceva miscari in diagonala ca sa ajungem langa fundatie
        sleep(time);
*//*

*//*
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // ridicam mana(bratul) cu skystone
        sleep(time);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION);  // lasam skystone-ul pe fundatie
        sleep(time);
*//*

        rb.movement.moveCM((float)Math.PI,-20,1f,this);  // facem strafe catre fundatie
        sleep(time);

*//*
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(100);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.MID); // ridicam bratul
        sleep(time);

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem ghiara ca sa putem trece sub pod
        sleep(time);
*//*

        rb.movement.moveCM((float)Math.PI,15,1f,this);// facem strafe catre perete
        sleep(time);

        rb.movement.moveCM((float)Math.PI/3,-40,1f,this);//// cev miscari in diagonala ca sa ajungem de unde am plecat
        sleep(time);*/
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(30000);

    }

    public void initRobot() {
        rb = new Robot(hardwareMap, this);
    }
}
