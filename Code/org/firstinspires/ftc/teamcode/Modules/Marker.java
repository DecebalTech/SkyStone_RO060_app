package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Marker {

    public Servo marker;
    
    private String markerName = "Marker";
    
    public void initMarker(HardwareMap hwm) {
        marker = hwm.servo.get(markerName);
    }
}
