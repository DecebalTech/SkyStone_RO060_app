package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Scanner {

    private static final String VUFORIA_KEY = "AZCSKh//////AAAAGUd9HlnvcUptlccwBSszfyF+1O9yZ+N86t+CTRBRH5xnTa5hqW7Zj82WqVub6npiiOvaeVPqFO0DAbJOVK7mOU2n1v0h8zWl8I9T8s8CdYC+J68fX31cXCYUUiKPzK/HEqBde7NW6dVDTdUtwDe7suukDd2/WZo1anlmKa4+RcVhwLV1uzC1//mhQPbFcVcE3BcnmbmgBURFV1fhr/vDavoQmvG5jkZiZIg5QGfbIVlc5kQbDBdEyVBNHWyNpRoXEIctQt5DPUFN8MMoJ+UXswkc6gIqC9iPoGM31qYDzx8VCPTzXRZ9eC4Z4CRNL745R9z34gcFlHjTDsdtLBcWUy04SNOnwyREAq1GJO+urHZM";

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
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    public void scan(LinearOpMode op) {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
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
            }
        }
    }
}
