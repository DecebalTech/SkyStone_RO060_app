package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Modules.*;
import org.firstinspires.ftc.teamcode.Vuforia.*;

@Autonomous(name = "Autonomie cu Șustă", group = "Teste")

public class AutonomousModeSusta extends LinearOpMode{
    
    public DcMotor hook, moto1, moto2, moto3, moto4;
    float power = 2.5f;
    int pos = 10, maxHookPos = -22750;
    
    @Override
    public void runOpMode() throws InterruptedException{
        
        hook = hardwareMap.dcMotor.get("hook");
        moto1 = hardwareMap.dcMotor.get("moto1");
        moto2 = hardwareMap.dcMotor.get("moto2");
        moto3 = hardwareMap.dcMotor.get("moto3");
        moto4 = hardwareMap.dcMotor.get("moto4");
        
        telemetry.addData("ce fac acu': " , "iti astept");
        telemetry.update();
        
        waitForStart();
        
        telemetry.addData("ce fac acu': " , "iti cobor");
        telemetry.update();
        
        hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        hook.setTargetPosition(maxHookPos);
        hook.setPower(power);
        while (hook.isBusy()) {}
        hook.setPower(0);
        
        hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        telemetry.addData("ce fac acu': ", "iti merg in spate");
        
        /*moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        moto1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moto4.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moto1.setTargetPosition(-6);
        moto2.setTargetPosition(6);
        moto3.setTargetPosition(-6);
        moto4.setTargetPosition(6);*/

        moto1.setPower(-0.5);
        moto2.setPower(0.5);
        moto3.setPower(-0.5);
        moto4.setPower(0.5);

        //while (moto1.isBusy() && moto2.isBusy() && moto3.isBusy() && moto4.isBusy()){}
        sleep(500);
        
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);

        /*moto1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        moto4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);*/

        //work in progress... desperately needs to be fixed 
        
        /*moto1.setPower(-0.5);
        moto2.setPower(-0.5);
        moto3.setPower(0.5);
        moto4.setPower(0.5);
        
        sleep(4300);
        
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);
        
        moto1.setPower(-0.5);
        moto2.setPower(-0.5);
        moto3.setPower(-0.5);
        moto4.setPower(-0.5);
        
        sleep(450);
        
        moto1.setPower(0.9);
        moto2.setPower(-0.9);
        moto3.setPower(0.9);
        moto4.setPower(-0.9);
        
        sleep(2850);
        
        moto1.setPower(0);
        moto2.setPower(0);
        moto3.setPower(0);
        moto4.setPower(0);*/
        
    }
    
    /*public void initRobotAuto() {
        vuforia.initTfod(hardwareMap);
        mov.initMovement(hardwareMap);
    }*/
    
}
