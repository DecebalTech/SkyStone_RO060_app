package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name="[Cu Trigonometrie - doar movement]", group = "Teste")

public class ControlTrigonometric extends LinearOpMode {

    private DcMotor stangaSpate=null;
    private DcMotor stangaFata=null;
    private DcMotor dreaptaFata=null;
    private DcMotor dreaptaSpate=null;
    
    public double x, y, r, robotAngle, rightX;
    
    public double v1, v2, v3, v4;
    
    @Override public void runOpMode() {
        initRobot();
        
        while(!isStarted()) idle();
    
        while(opModeIsActive()) {
            x = gamepad1.left_stick_x;
            y = gamepad1.left_stick_y;
            r = java.lang.Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI/4;
            rightX = gamepad1.right_stick_x;
            
            v1=r*Math.cos(robotAngle)+rightX;
            v2=r*Math.sin(robotAngle)-rightX;
            v3=r*Math.sin(robotAngle)+rightX;
            v4=r*Math.cos(robotAngle)-rightX;
            
            stangaFata.setPower(v1);
            dreaptaFata.setPower(v2);
            stangaSpate.setPower(v3);
            dreaptaSpate.setPower(v4);
        }
        
        
        
        
    }
    
    public void initRobot() {
        stangaFata = hardwareMap.dcMotor.get("stangaFata");
        dreaptaFata = hardwareMap.dcMotor.get("dreaptaFata");
        stangaSpate = hardwareMap.dcMotor.get("stangaSpate");
        dreaptaSpate = hardwareMap.dcMotor.get("dreaptaSpate");
        
        stangaFata.setDirection(DcMotor.Direction.FORWARD);
        stangaSpate.setDirection(DcMotor.Direction.FORWARD);
        dreaptaFata.setDirection(DcMotor.Direction.REVERSE);
        dreaptaSpate.setDirection(DcMotor.Direction.REVERSE);
        
        stangaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stangaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
    }
}
