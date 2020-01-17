package org.firstinspires.ftc.teamcode.Alternative;

import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.qualcomm.robotcore.hardware.configuration.annotations.MotorType;

import org.firstinspires.ftc.robotcore.external.navigation.Rotation;

@DeviceProperties(xmlTag="GoBILDA502_435RPM", name="GoBILDA502_435RPM")
@MotorType(ticksPerRev = 383.6, maxRPM = 435, gearing = 13.7, orientation = Rotation.CCW)
public interface GoBILDA5202_435RPM {
}
