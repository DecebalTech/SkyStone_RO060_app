package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Marker implements Modul {

    public Servo Marker;
    private String MarkerName;
    private boolean State = false;

    public void SetName(String Name) {
        markerName = Name;
    }

    public void SwitchState(boolean _State) {
        State = _State
    }

    public boolean IsOn() {
        return State;
    }

    public void Init(HardwareMap hwm) {
        try
        {
            SetName("teamMarker");
            marker = hwm.servo.get(markerName);
            SwitchState();
        }
        catch (exception ex)
        {

        }
    }


    public void up() {
        if(IsOn())
            marker.setPosition(0.45);
    }
    public void down() {
        if(IsOn())
            marker.setPosition(0.9);
    }
}
