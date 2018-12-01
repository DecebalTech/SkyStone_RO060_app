package org.firstinspires.ftc.teamcode.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import java.util.ArrayList;
import java.util.List;

public class vuforia_class {

      private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
      private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
      private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
      private static final String VUFORIA_KEY = "ASK ANDREI FOR IT :) ";
      public VuforiaLocalizer vuforia;
      public TFObjectDetector tfod;

      public int scan_minerals(LinearOpMode op) {
            int gold = -1; // pozitia
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
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
                            gold = 0;
                            op.telemetry.addData("LEFT", gold);
                            op.telemetry.update();
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            gold = 2;
                            op.telemetry.addData("RIGHT", gold);
                            op.telemetry.update();
                        } else {
                            gold = 1;
                            op.telemetry.addData("CENTER", gold);
                            op.telemetry.update();
                        }
                    } else { gold = -1; op.telemetry.addData("nope", gold); op.telemetry.update(); }
                  }
                }
              }
          return gold;
      }

      public void initVuforia() {
          /*
           * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
           */
          VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

          parameters.vuforiaLicenseKey = VUFORIA_KEY;
          parameters.cameraDirection = CameraDirection.BACK;

          //  Instantiate the Vuforia engine
          vuforia = ClassFactory.getInstance().createVuforia(parameters);

          // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
      }
      public void initTfod(HardwareMap hwm) {
          int tfodMonitorViewId = hwm.appContext.getResources().getIdentifier(
              "tfodMonitorViewId", "id", hwm.appContext.getPackageName());
          TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
          tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
          tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
      }

      public VuforiaTrackables targetsRoverRuckus;

      VuforiaTrackable blueRover;
      VuforiaTrackable redFootprint;
      VuforiaTrackable frontCraters;
      VuforiaTrackable backSpace;

      public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();

      public void initVuforiaForTarget() {
          targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");

          blueRover = targetsRoverRuckus.get(0);
          blueRover.setName("Blue-Rover");
          redFootprint = targetsRoverRuckus.get(1);
          redFootprint.setName("Red-Footprint");
          frontCraters = targetsRoverRuckus.get(2);
          frontCraters.setName("Front-Craters");
          backSpace = targetsRoverRuckus.get(3);
          backSpace.setName("Back-Space");

          allTrackables.addAll(targetsRoverRuckus);

          targetsRoverRuckus.activate();
      }

      public int scan_for_target(LinearOpMode op){
          int img = -1;
          boolean targetVisible = false;

          for (VuforiaTrackable trackable : allTrackables) {
              if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                  if (trackable.getName() == "Blue-Rover"){
                    img = 0;
                  }
                  if (trackable.getName() == "Red-Footprint"){
                    img = 1;
                  }
                  if (trackable.getName() == "Front-Craters"){
                    img = 2;
                  }
                  if (trackable.getName() == "Back-Space"){
                    img = 3;
                  }
                  targetVisible = true;
              }
          }
          return img;
      }
}
