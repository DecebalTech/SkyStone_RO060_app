package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Modules.*;

@Autonomous (name = "B_STONES", group = "BLUE")
public class BLUE_STONES extends LinearOpMode {

    private Robot rb = null;
    private final int time = 1;
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

        telemetry.addData("distance in cm", rb.distSensor.getDistanceCM());

        switch (scanResult) {
            case 0:
                telemetry.addLine("1 sau 4 pe zar");

                break;
            case 2:
                telemetry.addLine("3 sau 6 pe zar");

                break;
            default:
                //telemetry.addLine("🍆, dar mergem pe mijloc");
                telemetry.addData("distance in cm", rb.distSensor.getDistanceCM());
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(time);

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
                sleep(time);

                rb.movement.moveCM((float)Math.PI/3,50,1f,this); // mergem spre fundatie
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

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION);  // lasam skystone-ul pe fundatie
                sleep(time);

                    rb.movement.moveCM((float)Math.PI,-20,1f,this);  // facem strafe catre fundatie
                sleep(time);

                  rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
                sleep(100);

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.MID); // ridicam bratul
                sleep(time);

                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem ghiara ca sa putem trece sub pod
                sleep(time);

                    rb.movement.moveCM((float)Math.PI,15,1f,this);// facem strafe catre perete
                sleep(time);

                    rb.movement.moveCM((float)Math.PI/3,-40,1f,this);//// cev miscari in diagonala ca sa ajungem de unde am plecat
                sleep(time);

           //         rb.movement.rotateIMUAbsolute(0, 1f, this); // ne rotim cu giroscopul la unghiul la care robotul ar trebui sa fie pentru siguranta
                sleep(time);

                    rb.movement.moveCM((float)Math.PI/2,-325,1f,this); //mergem sa luam celalat skystone
                sleep(time);

                    rb.movement.moveDist(17,1f,this);  // ne setam distanta fata de skystone
                sleep(time);

                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // deschidem servoul de prindere
                sleep(time);

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(time);

                    rb.movement.moveDist(11,1f,this);  // ne apropiem de skystone
                sleep(time);

                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystone-ul
                sleep(300);

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // ridicam skystone-ul
                sleep(time);

                    rb.movement.moveCM((float)Math.PI,18,1f,this); //strafe catre perete
                sleep(time);

                    //  rb.movement.rotateIMUAbsolute(0, 1f, this); // ne rotim cu giroscopul la unghiul la care robotul ar trebui sa fie pentru siguranta
                sleep(time);

                    rb.movement.moveCM((float)Math.PI/2,275,1f,this); // mergem in fata catre fundatie
                sleep(time);

                    rb.movement.moveCM((float)Math.PI/2.5f,75,1f,this); // ceva miscari in diagonala ca sa ajungem langa fundatie
                sleep(time);

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION);  // lasam skystone-ul pe fundatie
                sleep(time);

                    rb.movement.moveCM((float)Math.PI,-10,1f,this); // strafe catre fundatie
                sleep(time);

                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
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
