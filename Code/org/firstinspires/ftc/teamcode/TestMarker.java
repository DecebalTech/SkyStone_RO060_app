package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Modules.*;

@TeleOp(name="Testare Servo TeamMarker", group="Teste")

public class TestMarker extends LinearOpMode {

    Marker marker = new Marker();
    boolean pressed = false;
    double markPos;
    
    @Override public void runOpMode() {
        
        marker.initMarker(hardwareMap);
        markPos = marker.marker.getPosition();
        
        while(!isStarted())
            idle();
            
        while(opModeIsActive()) {
            telemetry.addLine("y - 0 (down)");
            telemetry.addLine("a - 0.5 (up)");
            telemetry.addData("Marker Servo Position", markPos);
            telemetry.update();
            /*
            if(gamepad1.dpad_up && !pressed) {
                markPos+=0.1;
                if(markPos>1)
                    markPos = 1;
                marker.marker.setPosition(markPos);
                pressed = true;
            }
            if(gamepad1.dpad_down && !pressed) {
                markPos-=0.1;
                if(markPos < 0)
                    markPos = 0;
                marker.marker.setPosition(markPos);
                pressed = true;
            }
            if(!gamepad1.dpad_up && !gamepad1.dpad_down)
                pressed = false;
            */
            
            if(gamepad1.a) marker.marker.setPosition(0.5);
            if(gamepad1.y) marker.marker.setPosition(0);
           
        }
        
        
    }
    
}
