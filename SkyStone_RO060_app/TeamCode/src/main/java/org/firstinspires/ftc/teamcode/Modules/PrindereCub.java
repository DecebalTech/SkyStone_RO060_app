package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PrindereCub {

    private DcMotor GripMotorLeft, GripMotorRight;
    private boolean State;
    private String NameLeft, NameRight;

    private float[] PowerValues = {-.3f, 0, .5f, .4f,.3f, 1};
    public enum Direction {
        IN,
        STOP,
        OUT,
        RF,
        OUTS,
        OUTA
    }
    private Direction direction;

    public void Init(String _NameLeft, String _NameRight, HardwareMap hwm) {
        SetName(_NameLeft, _NameRight);
        try {
            GripMotorLeft = hwm.dcMotor.get(NameLeft);
            GripMotorRight = hwm.dcMotor.get(NameRight);
            GripMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
            State = true;
        }
        catch (Exception ex) {
            State = false;
        }
    }

    public boolean IsOn() {
        return State;
    }

    public void Kill() {
        State = false;
    }

    public void SetName(String _NameLeft, String _NameRight) {
        NameLeft = _NameLeft;
        NameRight = _NameRight;
    }

    public void SwitchState(boolean _State) {
        State = _State;
    }

    public void SetPower(Float Pow) {
        GripMotorLeft.setPower(Pow);
        GripMotorRight.setPower(Pow);
    }
    public void SetPowerRf(Float Pow) {
        GripMotorLeft.setPower(-Pow);
        GripMotorRight.setPower(Pow);
    }
    public void SetDirection(Direction _Direction) {
        direction = _Direction;
        if(direction == Direction.IN)
            SetPower(PowerValues[0]);
        else if (direction == Direction.STOP)
            SetPower(PowerValues[1]);
        else if (direction == Direction.OUT)
            SetPower(PowerValues[2]);
        else if(direction == Direction.RF)
            SetPowerRf(PowerValues[3]);
        else if(direction == Direction.OUTS)
            SetPower(PowerValues[4]);
        else if(direction == Direction.OUTA)
            SetPower(PowerValues[5]);
        else direction = _Direction;
    }

    public String UpdateGrips(Gamepad gamepad2){
        if(gamepad2.left_bumper)
            SetDirection(Direction.OUT);
        else if (gamepad2.right_bumper)
            SetDirection((Direction.IN));
        else if(gamepad2.dpad_left)
            SetDirection((Direction.OUTA));
        else if(gamepad2.dpad_right)
            SetDirection((Direction.OUTS));
       else
            SetDirection(Direction.STOP);

        return "Cube Grip Direction: [" + direction.toString() + "]";
    }
}