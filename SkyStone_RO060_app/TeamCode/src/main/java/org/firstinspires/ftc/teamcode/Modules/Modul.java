package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface Modul {
    void Init(String _Name, HardwareMap hwm);
    boolean IsOn();
    void Kill();
    void SetName(String _Name);
    void SwitchState(boolean _State);
}