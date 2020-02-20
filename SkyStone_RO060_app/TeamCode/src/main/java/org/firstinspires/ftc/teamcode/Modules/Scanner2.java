package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Scanner2 {

    class ClippingMargins {

        public int left, top, right, bottom;

        ClippingMargins (int _left, int _top, int _right, int _bottom) {
            left = _left;
            top = _top;
            right = _right;
            bottom = _bottom;
        }
    }

    private static final String VUFORIA_KEY = "AbXLjNX/////AAABmd7RkeSKpEklu5OVocY44bJIRu7RtNd3OkIDmFdoeUUQfYIgvhVG4DSt83DCY3sX1zqoC4AkeMz58y+JEtZY8lt5oOea/F4rxQ9/RUavbOVmW8ArTs3CrpKDg/u5Vw8YA280sHCm/3U9L+5tJntAD1w5Yw3ZfAtGN/7C1F8OqU/E5PR1zXAfORaW1hdMeCT0Pq/1EqM71Sci4NUWsoliqrqKDnHcbhvC53taDIh8c55KTuaYfy7UibCyFkWAXod+zMr3fp39MW4HsT75YSE7iV4gB/I536009S39cR0wNa0jbBAEQcde0feNvnDGqx22XkkDTdTn9iJpekBmVJ5ONqPHlhqS4hYPKm1Vtw4socTh";
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_STONE_ELEMENT = "Stone";
    private static final String LABEL_SKYSTONE_ELEMENT = "Skystone";
    private ClippingMargins clippingMargins = new ClippingMargins(0, 50, 0, 220);

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
        tfodParameters.minimumConfidence = 0.85;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.setClippingMargins(clippingMargins.left, clippingMargins.top, clippingMargins.right, clippingMargins.bottom);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_STONE_ELEMENT, LABEL_SKYSTONE_ELEMENT);
    }

    public boolean activateTfod() {
        if(tfod != null) {
            tfod.activate();
            return true;
        }
        return false;
    }

    public void stopTfod() {
        tfod.deactivate();
    }

    public int scan(LinearOpMode op) {
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                op.telemetry.update();
                if(updatedRecognitions!=null && updatedRecognitions.size() > 0) {
                    for(Recognition r : updatedRecognitions) {
                        if(r.getLabel() == LABEL_STONE_ELEMENT) continue;

                        float left, right;

                        left = r.getLeft();
                        right = r.getRight();

                        op.telemetry.addData("left", left);
                        op.telemetry.addData("right", right);
                        op.telemetry.update();

                        if(left < 100) return 0;
                        else if (left < 300 && right<610) return 1;
                        else return 2;
                    }
                    return -1;
                }
                else
                    return -1;
            }
            else return -1;
    }

    public VuforiaLocalizer getVuforia() {
        return vuforia;
    }

}
