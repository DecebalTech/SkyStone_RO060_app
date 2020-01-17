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
                sleep(time);

                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN);
                sleep(time);

                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(time);

                    rb.movement.moveDist(15, rb.distSensor,1,this);  // mergem la 15 cm fata de skystone
                sleep(time);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(time);
                rb.movement.moveDist(10, rb.distSensor,1,this);  // mergem la 15 cm fata de skystone
                sleep(100);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam mana jos
                sleep(time);

                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(300);

                  rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // ridicam mana(bratul) cu skystone
                sleep(300);

                    rb.movement.moveCM((float)Math.PI/2,25,1f,this); // strafe catre perete
                sleep(time);

                rb.movement.moveDist(25, rb.distSensor,1,this);  // mergem la 15 cm fata de skystone
                sleep(time);

                rb.movement.moveCM((float)Math.PI/2.05f,70,1f,this); //Catre bridge
                sleep(time);



/*                   rb.movement.moveCM((float)Math.PI/3f,80,1f,this); // ceva miscari in diagonala ca sa ajungem langa fundatie
                sleep(time);*/




                break;
        }

        telemetry.addData("distance in cm", rb.distSensor.getDistanceCM());
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2.05f,180,1f,this); //Catre bridge
        sleep(time);

        rb.movement.moveDist(3, rb.distSensor,1,this);  //
        sleep(time);

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
        sleep(400);

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem ghiara ca sa putem trece sub pod
        sleep(time);

        rb.movement.moveDist(30, rb.distSensor,1,this);  //
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2,-135,1f,this); //mergem sa luam celalat skystone
        sleep(time);
        rb.movement.rotateIMUAbsolute(0, 1f, this);
        sleep(time);
        rb.movement.moveDist(18, rb.distSensor,1,this);  // mergem la 15 cm fata de skystone
        sleep(time);

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
                sleep(time);

                rb.movement.moveCM((float)Math.PI/2,-220,1f,this); //mergem sa luam celalat skystone
                sleep(time);

                rb.movement.moveDist(17, rb.distSensor,1f,this);  // ne setam distanta fata de skystone
                sleep(time);

                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // deschidem servoul de prindere
                sleep(100);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(time);

                rb.movement.moveDist(11, rb.distSensor,1f,this);  // ne apropiem de skystone
                sleep(time);

                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystone-ul
                sleep(200);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // ridicam skystone-ul
                sleep(300);

                rb.movement.moveCM((float)Math.PI/2,25,1f,this); // strafe catre perete
                sleep(time);

                rb.movement.moveDist(25, rb.distSensor,1,this);  // mergem la 15 cm fata de skystone
                sleep(time);

                rb.movement.moveCM((float)Math.PI/2.05f,154,1f,this); //Catre bridge
                sleep(time);
                rb.movement.moveDist(18, rb.distSensor,1,this);  // mergem la 15 cm fata de skystone
                sleep(time);

                break;
        }

/*                rb.movement.moveCM((float)Math.PI/2.02f,170,1f,this); // mergem in fata catre fundatie
                sleep(time);

                 rb.movement.moveDist(6, rb.distSensor,1,this);  //
                 sleep(time);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
                sleep(time);

                rb.movement.moveDist(3, rb.distSensor,1,this);  //
                sleep(time);

                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
                sleep(500);
                rb.movement.moveCM((float)Math.PI/1.1f, 40, 1f, this);
                sleep(time);
                rb.movement.moveCM((float)Math.PI/2, -25 , 1f, this); //strafe cu placa
                sleep(time);
                rb.movement.rotate((float)Math.PI/2, 0.5f, this);

                sleep(time);
                rb.movement.moveCM((float)Math.PI, -50 , 1f, this);
                sleep(time);
                rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
                sleep(time);
                rb.movement.moveCM((float)Math.PI, 50 , 1f, this);
                sleep(time);*/
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
