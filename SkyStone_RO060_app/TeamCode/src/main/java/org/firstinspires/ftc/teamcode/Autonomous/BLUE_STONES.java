package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.IMU;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;
@Disabled
@Deprecated
@Autonomous (name = "BLUE_STONES", group = "RED")
public class BLUE_STONES extends LinearOpMode {

    private Robot rb = null;
    private final int t = 1;
    private Scanner scanner = new Scanner();
    private int scanResult = -1;
    private float stoneDist = 11f;
    private float ap = 20f;
    private float calibrateDist = 25;
    private float wallDist = 25;


    @Override
    public void runOpMode() throws InterruptedException {
       // telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //FtcDashboard.getInstance().startCameraStream(scanner.getVuforia(), 0);

        initRobot(hardwareMap);

        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
            telemetry.addData("Scan Result", scanResult);
        }

        telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
        scanResult = -1;
        switch (scanResult) {
            case 2:
                telemetry.addLine("1 sau 4 pe zar skystone la perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // initializare
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap cm fata de stone , distanta care ne permite efectuarea manevrelor necesare
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare [unghi]
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stonedist fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
                sleep(300);
                rb.movement.moveCM((float)Math.PI/2,30,1f,this); // mergem in fata sa fim langa o alta piatra
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam pozitia robotului [unghi]
                sleep(t);
                rb.movement.moveDist(17, rb.rightDist,1,this);  // mergem la dist_cm fata de stone
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,110,1f,this); //mergem catre building zone
                break;

            case 0: //requires work
                telemetry.addLine("3 sau 6 pe zar skystone catre perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // initializare
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap cm fata de stone , distanta care ne permite efectuarea manevrelor necesare
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare [unghi]
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,40,1f,this); //mergem in fata catre skystone
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stonedist fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam pozitia robotului [unghi]
                sleep(t);
                rb.movement.moveCM((float)Math.PI,15,1,this); // facem un strafe catre perete sa fim siguri ca nu lovim podul
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,120,1f,this); //mergem catre building zone
                sleep(t);
                break;

            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // initializare
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // initializare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap cm fata de stone , distanta care ne permite efectuarea manevrelor necesare
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare [unghi]
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,20,1f,this); //mergem in fata catre skystone
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stonedist fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pridem skystone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer
                sleep(300);
                rb.movement.moveCM((float)Math.PI/2,40,1f,this); // mergem in fata sa fim langa o alta piatra
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam pozitia robotului [unghi]
                sleep(t);
                rb.movement.moveDist(17, rb.rightDist,1,this);  // mergem la dist_cm fata de stone
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,120,1f,this); //mergem catre building zone
                break;
        }

                rb.movement.rotateIMUAbsolute(0, 1f, this);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.FOUNDATION); // initializare
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // initializare
                sleep(100);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // incepem sa inchidem ghiara
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,45,1f,this); // mergem dupa alt skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);

        switch (scanResult) {
                case 2:
                    telemetry.addLine("1 sau 4 pe zar skystone la bridge");
                    sleep(t);
                    rb.movement.rotateIMUAbsolute(0,1f,this);
                    sleep(t);
                    rb.movement.moveCM(3*(float)Math.PI/2,230,1f,this); //mergem dupa urmatorul skystone
                    sleep(t);
                    rb.movement.rotateIMUAbsolute(0,1f,this); //corectam unghiul robotului
                    sleep(t);
                    rb.movement.moveDist(7, rb.backDist,1,this);  // mergem la dist_cm fata de perete cu spatele pentru a elimina orice eroare umana
                    sleep(t);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // pregatim prinderea urmatorului skystone
                    sleep(t);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // pregatim prinderea urmatorului skystone
                    sleep(t);
                    rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap fata de skystone
                    sleep(t);
                    rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de rotatie
                    sleep(t);
                    rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  //mergem la stoneDist fata de skystone
                    sleep(t);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                    sleep(400);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystone-ul
                    sleep(400);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la ceruri
                    sleep(300);
                    rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de unghi
                    sleep(t);
                    rb.movement.moveCM((float)Math.PI/2,25,1f,this); //mergem langa un stone
                    sleep(t);
                    rb.movement.moveDist(15, rb.rightDist,1,this);//ne calibram fata de stone pentru a trece sub bridge
                    sleep(t);
                    rb.movement.moveCM((float)Math.PI/2,290,1f,this); //catre building zone
                    sleep(t);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam skystone-ul jos
                    sleep(400);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // lasam skystone-ul in building zone
                    sleep(200);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // pregatim bratul ca sa putem trece sub bridge
                    sleep(200);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pregatim bratul ca sa putem trece sub bridge
                    sleep(t);
                    rb.movement.moveCM(3*(float)Math.PI/2,170,1f,this); //mergem in fata dupa urmatorul stone
                    sleep(t);
                    rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de unghi
                    sleep(t);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // pregatim bratul pentru a prinde un stone
                    sleep(t);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // pregatim bratul pentru colectare
                    sleep(t);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                    sleep(t);
                    rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stonedist pentru a fii siguri ca prindem stone-ul
                    sleep(300);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem stone-ul
                    sleep(400);
                    rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam stone-ul
                    sleep(t);
                    rb.movement.rotateIMUAbsolute(0, 1f, this);// corectam orice posibila  problema unghi
                    sleep(t);
                    rb.movement.moveCM((float)Math.PI,20,1,this); //facem strafe ca sa fim siguri ca trecem sub bridge
                    sleep(t);
                    rb.movement.moveCM((float)Math.PI/2,160,1f,this); //mergem catre building zone
                    sleep(t);
                    rb.movement.rotate((float)Math.PI/2,1f,this);// ne rotim ca sa aruncam piatra
                    sleep(t);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la piatra
                    sleep(t);
                    rb.movement.rotate(-1*(float)Math.PI/2,1f,this);// ne rotim inapoi ca sa ne parcam
                    sleep(t);
                    rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem bratul ca sa putem intra sub bridge
                    sleep(t);
                    rb.movement.moveCM(3*(float)Math.PI/2,40,1f,this); //mergem sa ne parcam
                    sleep(t);
                break;

            case 0: // requires works
                telemetry.addLine("3 sau 6 pe zar");
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,210,1f,this); //mergem dupa urmatorul skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this); //corectam unghiul robotului
                sleep(t);
                rb.movement.moveDist(35, rb.backDist,1,this);  // mergem la dist_cm fata de perete cu spatele pentru a elimina orice eroare umana
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // pregatim prinderea urmatorului skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // pregatim prinderea urmatorului skystone
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de rotatie
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  //mergem la stoneDist fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la ceruri
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de unghi
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,25,1f,this); //mergem langa un stone
                sleep(t);
                rb.movement.moveDist(15, rb.rightDist,1,this);//ne calibram fata de stone pentru a trece sub bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,265,1f,this); //catre building zone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam skystone-ul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // lasam skystone-ul in building zone
                sleep(200);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // pregatim bratul ca sa putem trece sub bridge
                sleep(200);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pregatim bratul ca sa putem trece sub bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,190,1f,this); //mergem in fata dupa urmatorul stone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de unghi
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // pregatim bratul pentru a prinde un stone
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // pregatim bratul pentru colectare
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stonedist pentru a fii siguri ca prindem stone-ul
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem stone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam stone-ul
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);// corectam orice posibila  problema unghi
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,1,this); //facem strafe ca sa fim siguri ca trecem sub bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,180,1f,this); //mergem catre building zone
                sleep(t);
                rb.movement.rotate((float)Math.PI/2,1f,this);// ne rotim ca sa aruncam piatra
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la piatra
                sleep(t);
                rb.movement.rotate(-1*(float)Math.PI/2,1f,this);// ne rotim inapoi ca sa ne parcam
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem bratul ca sa putem intra sub bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,40,1f,this); //mergem sa ne parcam
                sleep(t);

                break;
            default:
                rb.movement.rotateIMUAbsolute(0,1f,this);
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,230,1f,this); //mergem dupa urmatorul skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this); //corectam unghiul robotului
                sleep(t);
                rb.movement.moveDist(25, rb.backDist,1,this);  // mergem la dist_cm fata de perete cu spatele pentru a elimina orice eroare umana
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // pregatim prinderea urmatorului skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // pregatim prinderea urmatorului skystone
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de rotatie
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  //mergem la stoneDist fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la ceruri
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de unghi
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,25,1f,this); //mergem langa un stone
                sleep(t);
                rb.movement.moveDist(15, rb.rightDist,1,this);//ne calibram fata de stone pentru a trece sub bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,265,1f,this); //catre building zone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam skystone-ul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // lasam skystone-ul in building zone
                sleep(200);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // pregatim bratul ca sa putem trece sub bridge
                sleep(200);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pregatim bratul ca sa putem trece sub bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,170,1f,this); //mergem in fata dupa urmatorul stone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam orice posibila eroare de unghi
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // pregatim bratul pentru a prinde un stone
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.OPEN); // pregatim bratul pentru colectare
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stonedist pentru a fii siguri ca prindem stone-ul
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem stone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam stone-ul
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);// corectam orice posibila  problema unghi
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,1,this); //facem strafe ca sa fim siguri ca trecem sub bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,140,1f,this); //mergem catre building zone
                sleep(t);
                rb.movement.rotate((float)Math.PI/2,1f,this);// ne rotim ca sa aruncam piatra
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la piatra
                sleep(t);
                rb.movement.rotate(-1*(float)Math.PI/2,1f,this);// ne rotim inapoi ca sa ne parcam
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem bratul ca sa putem intra sub bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,40,1f,this); //mergem sa ne parcam
                sleep(t);

                break;
        }

        sleep(t);
        telemetry.update();

    }

    public void initRobot(HardwareMap hwm) {
        rb = new Robot(hwm, this, true);
        scanner.Init("webcam", hwm);
        scanner.initTfod(hwm);
        scanner.activateTfod();

        rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
    }
}
