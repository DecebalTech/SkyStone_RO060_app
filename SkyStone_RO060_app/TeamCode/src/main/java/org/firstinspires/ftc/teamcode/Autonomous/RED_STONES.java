package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Auto_StoneArm;
import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.IMU;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.Scanner;
import org.firstinspires.ftc.teamcode.Robot;
/*Autonomia Red Aliance starting at stones
* 
* este la o versiune stabila , ne putem baza pe ea in momentul de fata , inca putem face ceva tweakuri */
@Disabled
@Deprecated
@Autonomous (name = "RED_STONES", group = "RED")
public class RED_STONES extends LinearOpMode {

    private Robot rb = null;
    private final int t = 1; // pauza intre executarea unei noi functii
    private Scanner scanner = new Scanner(); // definire vuforia
    private int scanResult = -1; // valoare initiala pentru rezultatul scanrii
    private float stoneDist = 11f; // distanta fata de stone pentru al putea colecta
    private float ap = 20f;  // distanta mai mare fata de stone pentru a putea pregati bratul
    private float calibrateDist = 22; // distanta fata de stone pentru a "putea calibra robotul" sa treaca sigur sub bridge safe
    private float wallDist = 25; // distanta fata de perete [frontdist] pentru a colecta al doi-lea stone ca sa fim siguri ca il prindem 
   
    //////////// valori vechi
    // private float stoneDist = 8.3f;
    // private float ap = 12f;
    //////////// valori vechi
    
    @Override
    public void runOpMode() throws InterruptedException {
        // cele doua functii ne-au cauzat probleme in timpul meciurilor 
       // telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //FtcDashboard.getInstance().startCameraStream(scanner.getVuforia(), 0);
        initRobot(hardwareMap);


///////////////////////// initializareRobot ///////////////////////// 
       
        while(!isStarted() && !isStopRequested()) {
            if(isStopRequested()) break;
            int temp = scanner.scan(this);
            scanResult = (temp == -1 ? scanResult : temp);
        }

///////////////////////// initializareRobot ///////////////////////// 
        
        telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());

/////////////////////////   AutonomieRobot  ///////////////////////// 
        
        scanResult =2; // anulam scanare vuforia
        switch (scanResult) { // in functie de ce detecteaza vuforia vom executa una dintre urmatoarele cazuri
            case 2:
                telemetry.addLine("3 sau 6 pe zar skystone la perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());

                //*auto initilization*//

                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO);
                sleep(t);

                //*auto initilization*//
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stoneDist cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // colectam skystone
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone
                sleep(300);
                rb.movement.moveCM_ramped((float)Math.PI,15,1,this); // facem un strafe catre perete sa fim siguri ca nu lovim podul
                sleep(t);
                rb.movement.moveCM_ramped(3*(float)Math.PI/2,135,1f,this); //mergem sa colectam pucte :)


                break;
            case 0:
                telemetry.addLine("1 sau 4 pe zar skystone catre perete");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());

                //*auto initilization*//

                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO);
                sleep(t);

                //*auto initilization*//

                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap cm fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam unghiul actual al robotului cu pozitia initiala
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,60,1f,this); //mergem in fata dist_cm cm pentru a fi aliniati cu skystone-ul
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam unghiul actual al robotului cu pozitia initiala
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stoneDist cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystoneul , yay :)
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul la cer , HAHA
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);// verificam sa fim aliniati [angle] cu pozitia initiala
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //mergem in fata paralel cu urmatoru stone pentru a ne calibra distanta
                sleep(t);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);  // ne calibram distanta fata de un stone pentru a trace sigur sub bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,190,1f,this); //mergem sa lasam skystone-ul
                sleep(t);

                break;
            default:
                telemetry.addLine("mergem pe mijloc [2 sau 5]");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());

                //*auto initilization*//

                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO);
                sleep(t);

                //*auto initilization*//

                rb.movement.moveDist(ap, rb.rightDist,1,this);  // ne apropiem la distanta de ap fata de skystone , distanta care ne permite sa executam si alte manevre
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // verificam daca suntem la unghiul care trebuie
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,30,1f,this); // mergem in fata 30 de cm pentru a fii langa skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); //verificam again daca suntem cum trebuie cu unghiul
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // ne apropiem sperietor de mult catre skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // coboram asamblul de colectare
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // furam skystone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone-ul , speram sa nu observe nimeni
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this);// verificam daca suntem rotiti cum trebuie
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //mergem putin in fata , dupa ne calibram fata de un stone
                sleep(t);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this); // ne calibram fata de stone-ul de langa noi , ca sa fim siguri ca nu vom avea o coliziune
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,140,1f,this); // mergem sa ascundem skystone-ul la noi acasa ᓚᘏᗢ , ne vom face o casa inalta


                break;
        }



        switch (scanResult) {
            case 2:
                telemetry.addLine("3 sau 6 pe zar skystone la bridge");
                sleep(t);
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,185,1f,this); //mergem catre urmatorul skystone
                sleep(t);
                rb.movement.moveDist(26, rb.frontDist,1,this);  // ne calibram cu peretele sa eliminam eroarea umana de la inceputul meciului
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // pregatire brat autonom
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); // pregatire brat autonom
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // mergem la ap fata de skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam unghiul robotului
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // mergem la stone dist cm fata de skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // pregatim colectare stone
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // colectam stone
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam  skystone
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam erori de miscare
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //mergem in fata langa urmatorul stone
                sleep(t);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);// ne calibram ca sa trecem sub pod
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,255,1f,this); //mergem sa lasam urmatorul skystone
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // Lasam Bratul jos suficient de mult sa eliberam skystone-ul
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystone
                sleep(200);

                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ne pregatim sa trecem sub bridge
                sleep(200);

                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // ne pregatim sa trecem sub bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,220,1f,this); //Catre urmatorul stone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); //corectam orice posibila eorare de miscare
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // ne pregatim sa luam stone-ul
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos
                sleep(200);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // ne apropiem de stone
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // luam stone-ul
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam la ceruri stone-ul
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,1,this); //facem strafe ca sa trecem cum trebuie sub bridge
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,150,1f,this); //mergem catre bridge
                sleep(t);
                rb.movement.rotate(-1*(float)Math.PI/2,1f,this); // ne rotim cu piatra
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la piatra din ceruri
                sleep(t);
                rb.movement.rotate((float)Math.PI/2,1f,this); //ne rotim din nou sa trecem sub bridge
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem bratul sa putem trece sub bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,40,1f,this); //mergem sub bridge sa ne parcam
                sleep(t);
                telemetry.addLine("🎉👏🙌😁");
                sleep(t);
//URAAAAA ne-am parcat :P
                break;
            case 0:
                telemetry.addLine("1 sau 4 pe zar skystone catre perete");
                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,200,1f,this); //mergem catre urmatorul skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this);// ne rotim in asa fel sa fim la fel cum am plecat
                sleep(t);
                rb.movement.moveDist(wallDist, rb.frontDist,1,this);//ne corectam cu pereteleca eliminam eroare umana de la inceputul meciului
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this); //ne apropiem de pietre , :) pacalim pe toata lumea ca luam stone dar de aici incepe magia
                sleep(t);
                rb.movement.moveCM(0,70,1f,this); //facem strafe ca nebunii printre cele doua pietre
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.IN); // pornim intake-ul
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,35,.4f,this); //luam skystone-ul like a boss ;)
                sleep(t);
                rb.movement.moveCM((float)Math.PI,60,0.6f,this); //facem strafe ca sa fugim de la locul faptei
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,35,.4f,this); //mergem putin in spate
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.STOP); // oprim intake-ul
                sleep(t);
                rb.movement.rotateIMUAbsolute(Math.PI, 0.7f, this); //ne rotim cu giro sa fim cu fata spre bridge
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,230,1f,this); //mergem in fata catre bridgi
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.OUT); // pornim outtake-ul ca sa scoatem stone-ul
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,50,1f,this); //mergem cu spatele ca sa scoatem stone-ul
                sleep(t);
                rb.prindereCub.SetDirection(PrindereCub.Direction.STOP); //oprim intake-ul
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,.3f,this); //facem strafe sa ne parcam
                sleep(t);
                telemetry.addLine("🎉👏🙌😁");
                sleep(t);


                break;
            default:

                telemetry.addLine("mergem pe mijloc [2 sau 5]");

                telemetry.addData("distance in cm", rb.rightDist.getDistanceCM());
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,160,1f,this); //mergem dupa urmatorul skystone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0,1f,this);//rotim robotul la unghiul dorit
                sleep(t);
                rb.movement.moveDist(6.5f, rb.frontDist,1,this);  // ne calibram in functie de perete pentru a elimina eroarea umana
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE);//pregatim bratul de auto pentru colectare
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.PICKandGO); //pregatim bratul de auto pentru colectare
                sleep(t);
                rb.movement.moveDist(ap, rb.rightDist,1,this);  // ne apropiem la distanta ap fata de skystone , distanta care ne permite efectuarea altor manevre
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // ne asiguram ca nu avem un unghi nedorit
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // ne apropiem de stone pentru al prinde
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul los
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem skystone
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam skystone la cer
                sleep(300);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // ne corectam din nou
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,25,1f,this); //margem pana la urmatorul stone
                sleep(t);
                rb.movement.moveDist(calibrateDist, rb.rightDist,1,this);//ne calibram fata de stone-ul din dreapta
                sleep(t);
                rb.movement.moveCM(3*(float)Math.PI/2,280,1f,this); //mergem sa depozitam stone-ul
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); //lasam bratul jos
                sleep(400);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la skystonel
                sleep(200);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP);//pregatim bratul sa treccem sub pod
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // pregatim bratul sa trecem sub pod
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,185,1f,this); //mergem dupa urmatorul stone
                sleep(t);
                rb.movement.rotateIMUAbsolute(0, 1f, this); // corectam unghiul robotului
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.AUTO); // lasam bratul robotul o idee mai jos
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // deschidembratul
                sleep(t);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.DOWN); // lasam bratul jos de tot
                sleep(t);
                rb.movement.moveDist(stoneDist, rb.rightDist,1,this);  // ne apropiem de stone
                sleep(300);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // prindem stone
                sleep(400);
                rb.stoneArm.armSetPosition(Auto_StoneArm.armPositions.UP); // ridicam stone-ul
                sleep(t);
                rb.movement.moveCM((float)Math.PI,20,1,this); //facem strafe ca sa trecem sub pod
                sleep(t);

                rb.movement.moveCM(3*(float)Math.PI/2,150,1f,this); //mergem in fata catre building zone
                sleep(t);
                rb.movement.rotate(-1*(float)Math.PI/2,1f,this); // ne rotim 90 grade ca sa lasam piatra
                sleep(t);

                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.RELEASE); // dam drumul la piatra
                sleep(t);

                rb.movement.rotate((float)Math.PI/2,1f,this); // rotim robotul cum era inainte
                sleep(t);
                rb.stoneArm.grabberSetPosition(Auto_StoneArm.grabberPositions.CATCH); // inchidem bratul
                sleep(t);
                rb.movement.moveCM((float)Math.PI/2,40,1f,this); //mergem sa ne parcam
                sleep(t);
                telemetry.addLine("🎉👏🙌😁");
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
