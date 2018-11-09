package org.firstinspires.ftc.teamcode.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

public class vuforia_class {
  public class MineralsTest extends LinearOpMode {
      private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
      private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
      private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
      private static final String VUFORIA_KEY = "VUFORIA KEY - ASK ANDREI FOR IT :) ";
      public VuforiaLocalizer vuforia;
      public TFObjectDetector tfod;

      public String scan() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
              telemetry.addData("# Object Detected", updatedRecognitions.size());
              if (updatedRecognitions.size() == 3) {
                int goldMineralX = -1;
                int silverMineral1X = -1;
                int silverMineral2X = -1;
                for (Recognition recognition : updatedRecognitions) {
                  if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                    goldMineralX = (int) recognition.getLeft();
                  } else if (silverMineral1X == -1) {
                    silverMineral1X = (int) recognition.getLeft();
                  } else {
                    silverMineral2X = (int) recognition.getLeft();
                  }
                }
                if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                  if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                    return "left";
                  } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                    return "right";
                  } else {
                    return "center";
                  }
                }
              }
            }
      }

      public void initVuforia() {
          /*
           * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
           */
          VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

          parameters.vuforiaLicenseKey = VUFORIA_KEY;
          parameters.cameraDirection = CameraDirection.FRONT;

          //  Instantiate the Vuforia engine
          vuforia = ClassFactory.getInstance().createVuforia(parameters);

          // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
      }
      public void initTfod() {
          int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
              "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
          TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
          tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
          tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
      }
}
