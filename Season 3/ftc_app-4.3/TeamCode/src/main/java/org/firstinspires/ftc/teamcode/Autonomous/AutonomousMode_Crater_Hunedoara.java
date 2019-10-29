package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Modules.*;

@Autonomous(name="***Crater HUNEDOARA***, dă pă ăsta", group="Autonomie")
public class AutonomousMode_Crater_Hunedoara extends LinearOpMode {

    Movement mov = new Movement();
    Marker marker = new Marker();
    HorizontalArm horizontalArm = new HorizontalArm();
    Hook hook = new Hook();
    
    @Override public void runOpMode() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        marker.initMarker(hardwareMap);
        horizontalArm.initHorizontalArm(hardwareMap);
        horizontalArm.autonomousInit();
        hook.initHook(hardwareMap);
        marker.up();
        
        telemetry.addLine("NO SAMPLING");
        telemetry.addData("hook pos", hook.hook.getCurrentPosition());
        telemetry.update();
        while(!isStarted())
            idle();
        
        hook.moveHookWithEncoders(hook.maxHookPos); //lower robot
        sleep(100);
        mov.move(151, -157, 133, -172, 0.5); //dehatch
        sleep(100);
        mov.move(-434, -1230, 341, 1382, 0.5);
        sleep(100);
       // mov.move(1237, 1191, -1304, -1223, 0.5); //strafe left to distance from lander
        //sleep(100);
       /* mov.move(3246, 3226, 3249, 3267, 0.5); //rotate 180 degrees ccw
        sleep(100);
        mov.move(-3489, 3535, -3489, 3443, 0.5); //move forwards to lane
        sleep(100);
        mov.move(-727, -561, -622, -756, 0.5); //rotate a lil' ol' lil' bit 
        sleep(100);
        mov.move(3477, 3421, -3484, -3446, 0.5); //strafe left to DEPOT
        sleep(100);
        marker.down();
        sleep(1000);
        marker.up();
        sleep(100);
        mov.move(-1204, -1069, -1299, -1558, 0.5); //rotate
        sleep(100);
        mov.move(788 -50, 756 -50, -822 +50, -750 +50, 0.5); //strafe left
        sleep(100);
        //mov.move(-56, -17, -22, -27, 0.5); //error fix
        //sleep(50);
        mov.move(-212, -92, 152, 166, 0.5); //actual error fix
        sleep(50);
        mov.move(-5557, 4592, -4647, 5541, 0.5); //move forward to crater
        sleep(100);*/
        //sleep(100);
        //horizontalArm.basculanta.setState(HorizontalArm.Basculanta.State.DOWN);
    }
}
