package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by mohamedarab on 9/12/18.
 */

public class DriveTrain {
    private DcMotor leftMotor = null;  // set null if no value to prevent unpredictability
    private DcMotor RightMotor = null;  // set null if no value to prevent unpredictability

    public DriveTrain(HardwareMap hardwareMap) {    // constructor to create object
        leftMotor = hardwareMap.dcMotor.get("chassis_left_motor");  // the name assigned on the phone
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //also works when encoder is not used
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }
}
