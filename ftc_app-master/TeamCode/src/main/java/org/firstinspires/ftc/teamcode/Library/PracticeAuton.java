package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by mohamedarab on 9/23/18.
 */

public class PracticeAuton extends LinearOpMode {

    private DriveTrain drive = new DriveTrain(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {

        drive.MecanumDrive(1,.5,0);
        wait();


        telemetry.addData("Status", "Bruh");telemetry.update();

    }
}
