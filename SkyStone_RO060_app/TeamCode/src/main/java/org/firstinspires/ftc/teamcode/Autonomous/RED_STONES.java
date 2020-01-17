package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED_STONES", group = "RED")
public class RED_STONES extends LinearOpMode {

    private Robot rb = null;
    private final int time = 1;
    private Scanner scanner = new Scanner();
    private int scanResult = -1;

    @Override
    public void runOpMode() throws InterruptedException {

        initRobot(hardwareMap);

        while(!isStarted() && !isStopRequested()) {
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }

        telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());

        switch (scanResult) {
/*            case 0:
                telemetry.addLine("1 sau 4 pe zar");

                break;
            case 2:
                telemetry.addLine("3 sau 6 pe zar");

                break;*/
            default:
                //telemetry.addLine("🍆, dar mergem pe mijloc");

                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(time);
                rb.movement.moveDist(10, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(time);
                rb.movement.moveDist(6.5f, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);

                rb.movement.moveCMNS((float)Math.PI/2,-20,1f,this); //Catre bridge
                rb.movement.moveDist(15, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCMNS((float)Math.PI/2,-80,1f,this); //Catre bridge





/*                   rb.movement.moveCM((float)Math.PI/3f,80,1f,this); // ceva miscari in diagonala ca sa ajungem langa fundatie
                sleep(time);*/




                break;
        }


        rb.movement.moveCM((float)Math.PI/2,-155,1f,this); //Catre fundatie
        sleep(time);

        rb.movement.moveDist(5, rb.rightDist,1,this);  //
       // rb.movement.moveCM((float)Math.PI/3f,25,1f,this); //Catre fundatie

        sleep(time);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(400);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(200);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(time);
        rb.movement.moveDist(25, rb.rightDist,1,this);  //
     //  rb.movement.moveCM((float)Math.PI/3f,-25,1f,this); //Catre fundatie

        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
        rb.movement.moveCMNS((float)Math.PI/2,110,1f,this); //mergem sa luam celalat skystone


        switch (scanResult) {
/*            case 0:
                telemetry.addLine("1 sau 4 pe zar");

                break;
            case 2:
                telemetry.addLine("3 sau 6 pe zar");

                break;*/
            default:
                //telemetry.addLine("🍆, dar mergem pe mijloc");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // deschidem servoul de prindere
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2,180,1f,this); //mergem sa luam celalat skystone
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2,30,1f,this); //mergem sa luam celalat skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveDist(1, rb.frontDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(time);
                rb.movement.moveDist(10, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(10);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(time);
                rb.movement.moveDist(7, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam mana(bratul) cu skystone
                sleep(300);
                rb.movement.moveCMNS((float)Math.PI/2,-30,1f,this); //Catre bridge
                sleep(time);
                rb.movement.moveDist(15, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
                sleep(time);
                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(time);
                rb.movement.moveCMNS((float)Math.PI/2,-160,1f,this); //Catre bridge
                break;
        }

                rb.movement.moveCM((float)Math.PI/2,-205,1f,this); // mergem in fata catre fundatie
                sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);

        rb.movement.moveDist(5, rb.rightDist,1,this);  //
        // rb.movement.moveCM((float)Math.PI/3f,25,1f,this); //Catre fundatie

        sleep(time);
        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
        sleep(400);
        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(200);

        rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
        sleep(time);

        ////
        rb.movement.moveDist(1, rb.rightDist,1,this);  // mergem la 10 cm fata de skystone
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(400);
        rb.movement.moveCM((float)Math.PI/2, 20 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/1.1f, 90 , 0.7f, this); //strafe cu placa
        sleep(time);
        rb.movement.rotate(-1*(float)Math.PI/1.4f, 0.5f, this);
        //rb.movement.rotateIMU(-Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -75 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(200);
        rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -130 , 1f, this);
        sleep(time);
/////trage
//        //aliniere cu mijloc
/*        rb.movement.moveDist(1, rb.rightDist,1,this);
        sleep(time);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(450);
        rb.movement.moveCM((float)Math.PI/2,-20,1f,this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/8,-100,1f,this);
        sleep(time);

        rb.movement.rotate((float)Math.PI,0.5f,this);

        rb.movement.moveCM((float)Math.PI,-40,1f,this);
        sleep(time);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(100);
        rb.movement.moveDist(10, rb.rightDist,1,this);
        sleep(time);
        rb.movement.rotateIMUAbsolute((float)Math.PI/2,1f,this);
        rb.movement.moveCM((float)Math.PI,130,1f,this);
        sleep(time);*/

/*        rb.movement.moveCM((float)Math.PI+((float)Math.PI*(1-1/1.08f)), -20, 1f, this);
        sleep(time);*/
      /*  rb.movement.moveCM((float)Math.PI/3, -90 , 1f, this); //strafe cu placa
        sleep(time);
        rb.movement.rotate((float)Math.PI/1.77f, 0.5f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 30 , 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -90 , 1f, this);
        sleep(time);
        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);*/
        sleep(time);
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
