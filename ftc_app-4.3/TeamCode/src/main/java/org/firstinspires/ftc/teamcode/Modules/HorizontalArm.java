package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.State;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class HorizontalArm
{
    public Basculanta basculanta;
    public Cuva cuva;
    public Matura matura;
    public Rail rail;
    public TouchSensor cuvaDown;
    
    boolean ok = false;

    private String cuvaDownName = "cuvaDown";

    private boolean basculantaIsPressed = false, railOut = false; 
    
    public void updateArm(Gamepad gamepad1, Gamepad gamepad2)
    {
        //Control Basculanta
        if(gamepad2.x && !basculanta.isMoving()) //Mid1; pozitie intermediara, se ridica putin de la sol
            basculanta.setState(Basculanta.State.MID1);
        if(gamepad2.b && !basculanta.isMoving()) //Up; pozitia din care elibereaza mineralele in cuva
            basculanta.setState(Basculanta.State.UP);
        if(gamepad2.a && !basculanta.isMoving()) //Down; lasa basculanta jos
            basculanta.setState(Basculanta.State.DOWN);
        if(gamepad2.y && !basculanta.isMoving()) //Mid2; pozitie intermediara mai inalta decat Mid1
            basculanta.setState(Basculanta.State.MID2);
            
        if(Math.abs(basculanta.getCurrentPosition() - basculanta.lastTarget) < 20 && basculanta.isMoving()) basculanta.moving = false;
        
        //Control Matura
        if(gamepad2.right_trigger > 0.1 && matura.getState() != Matura.State.IN) //matura se invarte spre interior pentru a lua minerale
            matura.setState(Matura.State.IN);
        if(gamepad2.left_trigger > 0.1 && matura.getState() != Matura.State.OUT)  //matura se invarte spre exterior 
            matura.setState(Matura.State.OUT);
        if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) //matura se opreste
            matura.setState(Matura.State.STOP);

        //Control Cuva
        if(gamepad2.left_bumper && !cuva.moving) {
            cuva.setState(Cuva.State.DOWN);
        }
        if(gamepad2.right_bumper && !cuva.moving) {
            if(cuva.currentState == Cuva.State.DOWN)
                cuva.setState(Cuva.State.MID);
            else if(cuva.currentState == Cuva.State.MID)
                cuva.setState(Cuva.State.UP);
        }
        if(!gamepad2.right_bumper && !gamepad2.left_bumper && cuva.moving)
            cuva.moving = false;
        
        if(cuvaDown.isPressed()) { //senzorul de pe basculanta
            basculanta.maxBasculanta = basculanta.basculanta.getCurrentPosition(); //senzorul este apasat atunci cand basculanta ajunge jos
            // pozitia maxBasculanta este recalculata
        }
        
        if(!rail.moving)
            rail.setPower(gamepad2.left_stick_y);
        if(gamepad2.dpad_left)
            rail.zero();
        if(rail.getCurrentPosition() >= 0 && rail.moving) {
            rail.moving = false;
            rail.rail.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        }
    }

    public void initHorizontalArm(HardwareMap hwm) {
        basculanta = new Basculanta(hwm);
        rail = new Rail(hwm);
        cuva = new Cuva(hwm);
        matura = new Matura(hwm);
        cuvaDown = hwm.touchSensor.get(cuvaDownName);
    }
    
    public void autonomousInit() {
        basculanta.basculanta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        basculanta.basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        basculanta.basculanta.setTargetPosition(0);
        basculanta.basculanta.setPower(1);
    }
    
    public static class Basculanta {
        public DcMotor basculanta;
        private String name = "basculanta";
        public int maxBasculanta = -670; //pozitia Down a basculantei
        public double basculantaPower = 0.7;//WAS 0.7
        public enum State {DOWN, MID1, MID2, UP};
        State currentState;
        public int lastTarget;
        public boolean moving;
        
        public Basculanta(HardwareMap hwm) {
            basculanta = hwm.dcMotor.get(name);
            basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            basculanta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            basculanta.setPower(0);
            
            lastTarget = 0;
            //basculanta.setDirection(DcMotorSimple.Direction.REVERSE);
            moving = false;
        }
        
        public int getCurrentPosition() {
            return basculanta.getCurrentPosition();
        }
        
        public void resetPos() {
            basculanta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            basculanta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        
        public void setState(State state) {
            if(moving) return;
            moving = true;
            switch(state) {
                case DOWN://a
                    basculanta.setTargetPosition(maxBasculanta);
                    lastTarget = maxBasculanta;
                    basculanta.setPower(basculantaPower/2);
                    //while(basculanta.isBusy()) { }
                    //basculanta.setPower(0);
                    currentState = state;
                    break;
                case MID1://x
                    basculanta.setTargetPosition(4*maxBasculanta/7); // was maxbasc/2
                    lastTarget = 4*maxBasculanta/7;
                    basculanta.setPower(basculantaPower/2);
                    //while(basculanta.isBusy()) { }
                    currentState = state;
                    break;
                case MID2://y
                    basculanta.setTargetPosition(10*maxBasculanta/45); //was maxbasc/4 
                    lastTarget = 10*maxBasculanta/45;
                    basculanta.setPower(basculantaPower/2);
                    //while(basculanta.isBusy()) { }
                    currentState = state;
                    break;
                case UP://b
                    basculanta.setTargetPosition(0);
                    lastTarget = 0;
                    basculanta.setPower(basculantaPower/2);
                    //while(basculanta.isBusy()) { }
                    currentState = state;
                    break;
                default:
                    break;
            }
        }
        
        public State getState() {
            return currentState;
        }
        
        public boolean isMoving() {
            //return basculanta.isBusy();
            return moving;
        }
    }
    
    public static class Rail {
        
        public DcMotor rail;
        private String name = "rail";
        public int maxRail = -3200;
        public boolean moving = false;
        
        public Rail(HardwareMap hwm) {
            rail = hwm.dcMotor.get(name);
            rail.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rail.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rail.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rail.setDirection(DcMotorSimple.Direction.REVERSE);
            rail.setPower(0);
        }
        
        public int getCurrentPosition() {
            return rail.getCurrentPosition();
        }
        
        public void setPower(float power) {
            power*=0.6;//WAS 0.7
            if(power == 0) {
                rail.setPower(power);
                return;
            }
            if((rail.getCurrentPosition() >= maxRail && rail.getCurrentPosition() <=0) ||
                (rail.getCurrentPosition() < maxRail && power > 0.1) ||
                (rail.getCurrentPosition() > 0 && power < 0.1)) {
                    rail.setPower(power);
            }
        }
        
        public void zero() {
            if(moving) return;
            moving = true;
            rail.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rail.setTargetPosition(0);
            rail.setPower(0.3);
        }
    }
    
    public static class Cuva {
        public Servo cuva1 = null;
        public Servo cuva2 = null;
        public enum State {DOWN, MID, UP};
        public State currentState;
        public double posDown = 0.9, posMid = 0.7,  posUp = 0.25;
        public String name1 = "cuva1";
        public String name2 = "cuva2";
        public boolean moving, auto;
        
        public Cuva(HardwareMap hwm) {
            cuva1 = hwm.servo.get(name2);
            cuva1.setPosition(posDown);
            currentState = State.DOWN;
        }
        
        public void setState(State state) {
            switch (currentState) {
                case DOWN:
                    if(state == State.MID)
                    {
                        for(double i = cuva1.getPosition(); i>=posMid; i-=0.1)
                        {
                            cuva1.setPosition(i);
                        }
                        currentState = State.MID;
                    }
                    else if(state == State.UP)
                    {
                        for(double i = cuva1.getPosition(); i>=posUp; i-=0.1)
                        {
                            cuva1.setPosition(i);
                        }
                        cuva1.setPosition(posUp);
                        currentState = State.UP;
                    }
                    break;
                case MID:
                    if(state == State.DOWN)
                    {
                        for(double i = cuva1.getPosition(); i<=posDown; i+=0.1)
                        {
                            cuva1.setPosition(i);
                        }
                        currentState = State.DOWN;
                    }
                    else if(state == State.UP)
                    {
                        for(double i = cuva1.getPosition(); i>=posUp; i-=0.1)
                        {
                            cuva1.setPosition(i);
                        }
                        currentState = State.UP;
                    }
                    break;
                
                case UP:
                    if(state == State.DOWN)
                    {
                        for(double i = cuva1.getPosition(); i<=posDown; i+=0.1)
                        {
                            cuva1.setPosition(i);
                        }
                        currentState = State.DOWN;
                    }
                    if(state == State.MID)
                    {
                        for(double i = cuva1.getPosition(); i<=posMid; i+=0.1)
                        {
                            cuva1.setPosition(i);
                        }
                        cuva1.setPosition(posMid);
                        currentState = State.MID;
                    }
                    break;
                default:
                    break;
            }
            moving = !moving;
        }
    }
    
    public static class Matura {
        
        public CRServo matura1, matura2;
        private String matura1Name = "matura1";
        private String matura2Name = "matura2";
        public enum State {IN, OUT, STOP};
        State currentState;
        
        public Matura(HardwareMap hwm) {
            matura1 = hwm.crservo.get(matura1Name);
            matura2 = hwm.crservo.get(matura2Name);
        }
        
        public void setState(State state) {
            switch (state) {
                case IN:
                    matura1.setPower(1);
                    matura2.setPower(-1);
                    break;
                case OUT:
                    matura1.setPower(-1);
                    matura2.setPower(1);
                    break;
                case STOP:
                    matura1.setPower(0);
                    matura2.setPower(0);
                    break;
                default:
                    break;
            }
        }
        
        public State getState() {
            return currentState;
        }
    }
}
