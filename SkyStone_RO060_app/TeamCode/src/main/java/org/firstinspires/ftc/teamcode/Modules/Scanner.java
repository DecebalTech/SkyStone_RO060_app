package org.firstinspires.ftc.teamcode.Modules;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Scanner {

    public static class ClippingMargins {

        public int left, top, right, bottom;

        public ClippingMargins(int _left, int _top, int _right, int _bottom) {
            left = _left;
            top = _top;
            right = _right;
            bottom = _bottom;
        }
    }

    private static final String VUFORIA_KEY = "AbXLjNX/////AAABmd7RkeSKpEklu5OVocY44bJIRu7RtNd3OkIDmFdoeUUQfYIgvhVG4DSt83DCY3sX1zqoC4AkeMz58y+JEtZY8lt5oOea/F4rxQ9/RUavbOVmW8ArTs3CrpKDg/u5Vw8YA280sHCm/3U9L+5tJntAD1w5Yw3ZfAtGN/7C1F8OqU/E5PR1zXAfORaW1hdMeCT0Pq/1EqM71Sci4NUWsoliqrqKDnHcbhvC53taDIh8c55KTuaYfy7UibCyFkWAXod+zMr3fp39MW4HsT75YSE7iV4gB/I536009S39cR0wNa0jbBAEQcde0feNvnDGqx22XkkDTdTn9iJpekBmVJ5ONqPHlhqS4hYPKm1Vtw4socTh";
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private ClippingMargins clippingMargins = new ClippingMargins(0, 50, 0, 220);
    private ClippingMargins LEFT_MARGINS = new ClippingMargins(0, 50, 426, 220);
    private ClippingMargins MIDDLE_MARGINS = new ClippingMargins(213, 50, 213, 220);
    private ClippingMargins RIGHT_MARGINS = new ClippingMargins(426, 50, 0, 220);
    public ClippingMargins TWO_STONES_MARGINS = new ClippingMargins(0, 50, 213, 220);

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private Image grayScaleImage;



    public void Init(String webcamName, HardwareMap hwm) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hwm.get(WebcamName.class, webcamName);


        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        vuforia.setFrameQueueCapacity(1);
    }

    public void initTfod(HardwareMap hwm) {
        if (!ClassFactory.getInstance().canCreateTFObjectDetector()) {
            return;
        }
        int tfodMonitorViewId = hwm.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hwm.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.54 ;//0.7
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.setClippingMargins(clippingMargins.left, clippingMargins.top, clippingMargins.right, clippingMargins.bottom);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
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
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    updatedRecognitions.sort(Comparator.comparing(Recognition::getLeft)); }
                op.telemetry.addData("# Object Detected", updatedRecognitions.size());
                // step through the list of recognitions and display boundary info.
                int i = 0;
//                for (Recognition recognition : updatedRecognitions) {
//                    if(!op.opModeIsActive()) return -1;
//                    op.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
//                    op.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
//                            recognition.getLeft(), recognition.getTop());
//                    op.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
//                            recognition.getRight(), recognition.getBottom());
//                }
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

    public void SortRecognitions(List<Recognition> updatedRecognitions, LinearOpMode op) {
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

    public int scanWithAverage(LinearOpMode op) {
        try {
            VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
            Image img = null;
            for(int i=0; i<frame.getNumImages(); i++) {
                Image image = frame.getImage(i);
                if(image.getFormat() == PIXEL_FORMAT.RGB565) {
                    img = image;
                    break;
                }
            }
            if(img==null) return -1;
            Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
            bm.copyPixelsFromBuffer(img.getPixels());

            double averageLeft = 0, averageCenter = 0, averageRight = 0;
            int pixelCount = bm.getWidth() * bm.getHeight();

            //bordare top
            for (int i = 0; i < bm.getWidth(); i++) {
                for (int j = 0; j < clippingMargins.top; j++) {
                    bm.setPixel(i, j, 0);
                }
            }

            //bordare bottom
            for (int i = 0; i < bm.getWidth(); i++) {
                for (int j = bm.getHeight() - clippingMargins.bottom; j < bm.getHeight(); j++) {
                    bm.setPixel(i, j, 0);
                }
            }

            //calculare avg left
            for (int i = 0; i < bm.getWidth() / 3; i++) {
                for (int j = 0; j < bm.getHeight(); j++) {
                    if (op.isStopRequested()) return -1;
                    int p = bm.getPixel(i, j);

                    averageLeft += (((p & 0xff0000) >> 16 + (p & 0xff00) >> 8 + p & 0xff)) / 3;
                }
            }

            averageLeft /= pixelCount/3;


            // calculare avg center
            for (int i = (int) (bm.getWidth() / 3); i < 2 * bm.getWidth() / 3; i++) {
                for (int j = 0; j < bm.getHeight(); j++) {
                    if (op.isStopRequested()) return -1;
                    int p = bm.getPixel(i, j);

                    averageCenter += (((p & 0xff0000) >> 16 + (p & 0xff00) >> 8 + p & 0xff)) / 3;
                }
            }

            averageCenter /= pixelCount/3;


            //calculare avg right
            for (int i = (int) (2 * bm.getWidth() / 3); i < bm.getWidth(); i++) {
                for (int j = 0; j < bm.getHeight(); j++) {
                    if (op.isStopRequested()) return -1;
                    int p = bm.getPixel(i, j);

                    averageRight += (((p & 0xff0000) >> 16 + (p & 0xff00) >> 8 + p & 0xff)) / 3;
                }
            }

            averageRight /= pixelCount/3;

            double maximum = Math.max(averageLeft, Math.max(averageCenter, averageRight));

            if (maximum == averageLeft) return 0;
            else if (maximum == averageRight) return 2;
            else return 1;
        }
        catch (Exception ex) {
            return -1;
        }
    }

    public int scanFirstTwo(LinearOpMode op) {
        if(tfod!=null) {
            tfod.setClippingMargins(TWO_STONES_MARGINS.left, TWO_STONES_MARGINS.top, TWO_STONES_MARGINS.right, TWO_STONES_MARGINS.bottom);
            List<Recognition> updateRecognition = tfod.getUpdatedRecognitions();

            if (updateRecognition != null && updateRecognition.size() == 2) {
                    if(updateRecognition.get(0).getLeft() > updateRecognition.get(1).getLeft()) {
                        Recognition temp = updateRecognition.get(0);
                        updateRecognition.set(0, updateRecognition.get(1));
                        updateRecognition.set(1, temp);
                    }
                    if (updateRecognition.get(0).getLabel() == LABEL_SECOND_ELEMENT)
                        return 0;
                    else if (updateRecognition.get(1).getLabel() == LABEL_SECOND_ELEMENT)
                        return 1;
                    else return 2;
            } else return -1;
        }
        else return -1;
    }

    public int scanWithCrop(LinearOpMode op) {
        if(tfod!=null) {
            //scanare prima fasa
            tfod.setClippingMargins(LEFT_MARGINS.left, LEFT_MARGINS.top, LEFT_MARGINS.right, LEFT_MARGINS.bottom);

            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

            if(updatedRecognitions!=null && updatedRecognitions.size() > 0) {
                if(updatedRecognitions.get(0).getLabel() == LABEL_SECOND_ELEMENT)
                    return 0;
            }

            //scanare a doua fasa

            tfod.setClippingMargins(MIDDLE_MARGINS.left, MIDDLE_MARGINS.top, MIDDLE_MARGINS.right, MIDDLE_MARGINS.bottom);

            updatedRecognitions = tfod.getUpdatedRecognitions();

            if(updatedRecognitions!=null && updatedRecognitions.size() > 0) {
                if(updatedRecognitions.get(0).getLabel() == LABEL_SECOND_ELEMENT)
                    return 1;
            }


            return 2;
        }

        return -1;
    }

    public void setCropping(ClippingMargins margins) {
        tfod.setClippingMargins(margins.left, margins.top, margins.right, margins.bottom);
    }

}
