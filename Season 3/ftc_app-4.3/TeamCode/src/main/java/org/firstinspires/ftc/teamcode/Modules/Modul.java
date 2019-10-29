package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface Modul {
    public void Init(HardwareMap hwm);
    public boolean IsOn();
    public void Kill();
    public void SetName(String Name);
    public void SwitchState(boolean State);
}