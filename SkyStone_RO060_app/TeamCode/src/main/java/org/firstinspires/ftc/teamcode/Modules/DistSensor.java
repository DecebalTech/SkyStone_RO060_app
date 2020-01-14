package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistSensor {

    private DistanceSensor sensorRange;
    Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;

    public void Init(String _sensor, HardwareMap hwm){
        sensorRange = hwm.get(DistanceSensor.class, _sensor);
    }

    public double getDistanceMM(){
        return sensorRange.getDistance(DistanceUnit.MM);
    }

    public double getDistanceCM(){
        return sensorRange.getDistance(DistanceUnit.CM);
    }

    public double getDistanceMETER(){
        return sensorRange.getDistance(DistanceUnit.METER);
    }

    public double getDistanceINCH(){
        return sensorRange.getDistance(DistanceUnit.INCH);
    }

}
