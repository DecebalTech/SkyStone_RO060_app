package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MagneticSwitch implements Modul {

    private boolean State;
    private String Name;
    private DigitalChannel magneticSwitch;

    @Override
    public void Init(String _Name, HardwareMap hwm) {
        SetName(_Name);
        try {
            magneticSwitch = hwm.digitalChannel.get(Name);
            magneticSwitch.setMode(DigitalChannel.Mode.INPUT);
            SwitchState(true);
        }
        catch (Exception e) {
            SwitchState(false);
        }
    }

    @Override
    public boolean IsOn() {
        return State;
    }

    @Override
    public void Kill() {

    }

    @Override
    public void SetName(String _Name) {
        Name = _Name;
    }

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
    }

    public boolean getState() {
        return magneticSwitch.getState();
    }


}
