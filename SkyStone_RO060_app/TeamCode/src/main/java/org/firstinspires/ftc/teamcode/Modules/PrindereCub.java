/*package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PrindereCub {

    private DcMotor GripMotorLeft, GripMotorRight;
    private boolean State;
    private String NameLeft, NameRight;

    private float[] posVal = {};
    public enum POS {
        CLOSED,
        OPEN
    }
    private POS pos;

    public void Init(String _NameLeft, String _NameRight, HardwareMap hwm) {
        SetName(_NameLeft, _NameRight);
        try {
            GripMotorLeft = hwm.dcMotor.get(NameLeft);
            GripMotorRight = hwm.dcMotor.get(NameRight);
            State = true;
            SetPos(POS.CLOSED);
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

    public void SetPos(POS _pos) {
        switch (_pos) {
            case POS.CLOSED:
                GripMotorLeft.set
        }
    }

    public String Move(Gamepad gamepad1){
        if(gamepad1.a && )
    }
}
*/