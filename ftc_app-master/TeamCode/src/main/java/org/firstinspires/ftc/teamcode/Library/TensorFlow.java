package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

// Use this one when setting the PixyMon to "analog/digital x"

public class TensorFlow {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AdksQ3j/////AAAAGVB9GUsSEE0BlMaVB7HcRZRM4Sv74bxusFbCpn3gwnUkr3GuOtSWhrTCHnTU/93+Im+JlrYI6///bytu1igZT48xQ6182nSTpVzJ2ZP+Q/sNzSg3qvIOMnjEptutngqB+e3mQ1+YTiDa9aZod1e8X7UvGsAJ3cfV+X/S3E4M/81d1IRSMPRPEaLpKFdMqN3AcbDpBHoqp82fAp7XWVN3qd/BRe0CAAoNsr26scPBAxvm9cizRG1WeRSFms3XkwFN6eGpH7VpNAdPPXep9RQ3lLZMTFQGOfiV/vRQXq/Tlaj/b7dkA12zBSW81MfBiXRxp06NGieFe7KvXNuu2aDyyXoaPFsI44FEGp1z/SVSEVR4";
    public double angle_gold = 90;
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private boolean isCompatible;
    private float confidence;
    ElapsedTime elapsedTime = new ElapsedTime();
    ElapsedTime elapsedTime2 = new ElapsedTime();

    public TensorFlow(HardwareMap hardwareMap) {
        isCompatible = initVuforia(hardwareMap);
    }

    public boolean isPhoneCompatible() {
        return isCompatible;
    }

    // ================= return -1 = Left side, 0 = Middle, 1 = Right, 2 = not detected
    public int runTensorFlow() {
        confidence=0;
        if (tfod != null) {
            tfod.activate();
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                //telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;

                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                            angle_gold = recognition.estimateAngleToObject(AngleUnit.DEGREES);
                            confidence = recognition.getConfidence();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            return -1;
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                } else {//if less than 3 minerals are recognized
                    int goldMineralX = -1;

                    for (Recognition recognition : updatedRecognitions) {

                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                            angle_gold = recognition.estimateAngleToObject(AngleUnit.DEGREES);
                            confidence = recognition.getConfidence();
                            return 7;

                        }
                    }
                }
            }
        }
        return 2; // when failed to find gold mineral
    }

    // ============ Stop TensorFlow =====================
    public void stopTensorFlow() {
        if (tfod != null) tfod.shutdown();
    }

    // ======================== Initialize to get started ===========================
    private boolean initVuforia(HardwareMap hardwareMap) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod(hardwareMap);
            return true;
        } else {
            return false;
        }
    }
    public float getConfidence(){
     return confidence;
    }
    public boolean checkConfidence() {
        elapsedTime2.reset();
        while(elapsedTime2.seconds()<3){

        if (elapsedTime.milliseconds() > 100) // Update every tenth of a second.
        {

            runTensorFlow();
            if(confidence>.8){
return true;
            }
            elapsedTime.reset();
        }

      }
      return false;
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod(HardwareMap hardwareMap) {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}