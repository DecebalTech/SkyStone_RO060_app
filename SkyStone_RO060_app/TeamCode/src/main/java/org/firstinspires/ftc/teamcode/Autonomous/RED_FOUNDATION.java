package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.FoundationServos;
import org.firstinspires.ftc.teamcode.Modules.MarkerArm;
import org.firstinspires.ftc.teamcode.Modules.PrindereCub;
import org.firstinspires.ftc.teamcode.Modules.ServoBratePrindere;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous (name = "RED FOUNDATION")
public class RED_FOUNDATION extends LinearOpMode {

    public Robot rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new Robot(hardwareMap, this); //Initialization of the Robot
        int time=10,gtime=1000;
        while(!isStarted()) {
            idle();
        }

       rb.movement.moveCM((float)Math.PI/2, 90, 1f, this);
        sleep(time);
        rb.movement.rotate((float)Math.PI, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/-2, 15, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 35 , 1f, this);
        sleep(time);
        //rb.movement.moveCM((float)Math.PI, 42, 1f, this);
        //rb.movement.moveTICKS((float)Math.PI, 1000, 1f, this);
        //sleep(time);
     /*   rb.movement.moveCM((3 * (float) Math.PI) / 2, 20, 1f, this);
        sleep(time);
        rb.movement.moveCM((3 * (float) Math.PI) / 2, 20, 1f, this);
        sleep(time);
*/

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.DOWN);
        sleep(gtime);
        rb.movement.moveCM((3 * (float) Math.PI) / 2, -45, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -45, 1f, this);
        sleep(time);
        rb.movement.rotate(-4* (float)Math.PI/6, 1f, this);
        sleep(time);
        rb.movement.moveCM((3 * (float) Math.PI) / 2, 20, 1f, this);
        sleep(time);

        rb.foundationServos.SetPosition(FoundationServos.ServoPositions.UP);
        sleep(time);

            rb.movement.moveCM(( (float) Math.PI) , 20, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, -40, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 180, 1f, this);
        sleep(time);

        rb.movement.rotate(-(float)Math.PI/4, 1f, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.IN);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2, 50, 1f, this);
        sleep(333);


        rb.movement.moveCM((float)Math.PI/-2, 50, 1f, this);
        sleep(time);

        rb.movement.rotate((float)Math.PI+(float)Math.PI/4, 1f, this);
        sleep(time);

        rb.prindereCub.SetDirection(PrindereCub.Direction.STOP);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2, 30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, 30, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI, -15, 1f, this);
        sleep(time);
        rb.movement.moveCM((float)Math.PI/2, 140, 1f, this);
        sleep(time);
       rb.markerArm.SetMarkerGrabPosition(MarkerArm.ServoPositions.CLOSED);
       sleep(gtime);
       rb.markerArm.Rotation.SetPower(-.75f);
       sleep(gtime);
       rb.markerArm.Rotation.SetPower(0f);
        sleep(time);
        rb.servoBratePrindere.SetPos(ServoBratePrindere.Position.OPEN);
        sleep(time);
        rb.markerArm.Extender.SetPower(-1f);
        sleep(950);
        rb.markerArm.Extender.SetPower(0f);
        sleep(time);
        rb.markerArm.Rotation.SetPower(.45f);
        sleep(1300);
        rb.markerArm.Rotation.SetPower(0f);
        sleep(time);
        rb.markerArm.Rotation.SetPower(-1f);
        sleep(200);
        rb.markerArm.Rotation.SetPower(0f);
        sleep(time);
        rb.markerArm.Extender.SetPower(+1f);
        sleep(200);
        rb.markerArm.Extender.SetPower(0f);
        sleep(time);
      //  rb.movement.moveCM((float)Math.PI, -7, 1f, this);
       // sleep(time);
        rb.markerArm.SetMarkerGrabPosition(MarkerArm.ServoPositions.OPEN);
        sleep(200);
        rb.markerArm.Rotation.SetPower(-1f);
        sleep(350);
        rb.markerArm.Rotation.SetPower(0f);
        sleep(time);
        rb.markerArm.Extender.SetPower(1f);
        sleep(950);
        rb.markerArm.Extender.SetPower(0f);
        sleep(time);
        rb.markerArm.Rotation.SetPower(.75f);
        sleep(450);
        rb.markerArm.Rotation.SetPower(0f);
        sleep(time);

        rb.movement.moveCM((float)Math.PI/2, -150, 1f, this);
        sleep(time);

    }
}
