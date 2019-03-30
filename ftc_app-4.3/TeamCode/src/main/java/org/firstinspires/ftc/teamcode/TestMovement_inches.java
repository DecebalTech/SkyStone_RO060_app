package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;

@Autonomous(name="Test Movement in dist units (cm by default)", group="Teste")

public class TestMovement_inches extends LinearOpMode {
    
    Movement mov = new Movement();
    
    private float MOTOR_TICKS = 560f;//1120f;
    private float WHEEL_DIAMETER = 10.16f; //cm
    private int TICKS_PER_CM = (int)(MOTOR_TICKS / (WHEEL_DIAMETER * Math.PI));
    
    @Override public void runOpMode() {
        mov.initMovement(hardwareMap);
        mov.op = this;
        
        while(!isStarted()) {}
        
        telemetry.addLine("Move 100 cm forward.");
        telemetry.update();
        
        mov.move_forwards_cm(50, 0.3);
        /*
        int pos = (int)(100 * TICKS_PER_CM);
        
        mov.wheels.stopAndResetEncoder();
        
        mov.wheels.runToPosition();

        mov.wheels.setTargetPosition(-pos, pos, -pos, pos);

        mov.wheels.setPower(0.3);

        while(mov.wheels.busy_and()) {
            idle();
        }
        mov.wheels.stop();

        mov.wheels.stopAndResetEncoder();
        */
        
    }
}
