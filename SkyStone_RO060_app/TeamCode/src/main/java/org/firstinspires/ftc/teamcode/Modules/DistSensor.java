package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistSensor implements Modul {

    private DistanceSensor sensorRange;
    private Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;
    private String Name;
    private Boolean State;

    public void Init(String _sensor, HardwareMap hwm){
        SetName(_sensor);

        try {
            sensorRange = hwm.get(DistanceSensor.class, _sensor);
            SwitchState(true);
        }
        catch (Exception ex) {
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

    public String GetName() {return Name;}

    @Override
    public void SwitchState(boolean _State) {
        State = _State;
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
