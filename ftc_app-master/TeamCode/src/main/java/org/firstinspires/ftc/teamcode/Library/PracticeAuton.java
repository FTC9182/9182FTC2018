package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by mohamedarab on 9/23/18.
 */

public class PracticeAuton extends LinearOpMode {
    private DcMotor FrontLeft = null;
    private DcMotor FrontRight = null;

    @Override
    public void runOpMode() throws InterruptedException {
        FrontLeft  = hardwareMap.get(DcMotor.class, "FrontLeft_drive");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight_drive");

        telemetry.addData("Status", "Bruh");telemetry.update();

    }
}
