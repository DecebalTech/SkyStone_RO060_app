package org.firstinspires.ftc.teamcode.Modules;

import android.os.Build;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.Comparator;
import java.util.List;

public class Scanner {

    private static final String VUFORIA_KEY = "AbXLjNX/////AAABmd7RkeSKpEklu5OVocY44bJIRu7RtNd3OkIDmFdoeUUQfYIgvhVG4DSt83DCY3sX1zqoC4AkeMz58y+JEtZY8lt5oOea/F4rxQ9/RUavbOVmW8ArTs3CrpKDg/u5Vw8YA280sHCm/3U9L+5tJntAD1w5Yw3ZfAtGN/7C1F8OqU/E5PR1zXAfORaW1hdMeCT0Pq/1EqM71Sci4NUWsoliqrqKDnHcbhvC53taDIh8c55KTuaYfy7UibCyFkWAXod+zMr3fp39MW4HsT75YSE7iV4gB/I536009S39cR0wNa0jbBAEQcde0feNvnDGqx22XkkDTdTn9iJpekBmVJ5ONqPHlhqS4hYPKm1Vtw4socTh";
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public void Init(String webcamName, HardwareMap hwm) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hwm.get(WebcamName.class, webcamName);

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public void initTfod(HardwareMap hwm) {
        if (!ClassFactory.getInstance().canCreateTFObjectDetector()) {
            return;
        }
        int tfodMonitorViewId = hwm.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwm.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.7;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    public boolean activateTfod() {
        if(tfod != null) {
            tfod.activate();
            return true;
        }
        return false;
    }

    public int scan(LinearOpMode op) {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    updatedRecognitions.sort(Comparator.comparing(Recognition::getLeft)); }
                op.telemetry.addData("# Object Detected", updatedRecognitions.size());
                // step through the list of recognitions and display boundary info.
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    op.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    op.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                    op.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                            recognition.getRight(), recognition.getBottom());
                }
                op.telemetry.update();
                if(updatedRecognitions.size() == 3) {
                    if(updatedRecognitions.get(0).getLabel() == LABEL_SECOND_ELEMENT) {
                        return 0;
                    }
                    else if(updatedRecognitions.get(1).getLabel() == LABEL_SECOND_ELEMENT) {
                        return 1;
                    }
                    else return 2;
                }
                else
                    return -1;
            }
            else return -1;
        }
        else return -1;
    }

    public void SortRecognitions(List<Recognition> updatedRecognitions) {
        boolean done = false;
        int index = 0;
        while(!done) {
            done = true;
            if(updatedRecognitions.get(index).getLeft() > updatedRecognitions.get(index+1).getLeft()) {
                Recognition temp = updatedRecognitions.get(index);
                updatedRecognitions.set(index, updatedRecognitions.get(index+1));
                updatedRecognitions.set(index+1, temp);
                done = false;
                index = 0;
            }
            else index++;
        }
    }

    public VuforiaLocalizer getVuforia() {
        return vuforia;
    }
}
