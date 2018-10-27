package org.firstinspires.ftc.teamcode.TestingCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Library.DriveTrain;
import org.firstinspires.ftc.teamcode.Library.Rev_IMU;
import org.firstinspires.ftc.teamcode.Library.liftUp;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Mechanum_Drive", group = "Linear Opmode")


public class testingdrive extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DriveTrain newDrive = null;
    private double r;
    private liftUp lift = null;
    //private Rev_IMU;
    I2cDeviceSynch pixy;
    

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //** Pixy Stuff**
        //setting up Pixy to the hardware map
        pixy = hardwareMap.i2cDeviceSynch.get("pixy");

        //setting Pixy's I2C Address
        pixy.setI2cAddress(I2cAddr.create7bit(0x54));

        I2cDeviceSynch.ReadWindow readWindow = new I2cDeviceSynch.ReadWindow (1, 26,
                I2cDeviceSynch.ReadMode.REPEAT);
        pixy.setReadWindow(readWindow);

        //** Pixy End**

        newDrive = new DriveTrain(hardwareMap);
        lift = new liftUp(hardwareMap);
        //Rev_IMU gps = new Rev_IMU(hardwareMap);

        pixy.engage();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {



            //gps.initialize();



            //Mecanum Stuff
            if((gamepad1.right_trigger+gamepad1.left_trigger)>1){
                //Ultimate
            }
            else{
                r= gamepad1.right_trigger-gamepad1.left_trigger;
            }


            newDrive.MecanumDrive((-gamepad1.right_stick_y), gamepad1.right_stick_x,r );
            lift.lift(-gamepad2.left_stick_y);
            lift.lock(gamepad2.a);
            lift.unlock(gamepad2.x);
            lift.drop_Marker(gamepad2.b);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Byte 0", pixy.read8(0));
            telemetry.addData("Byte 1", pixy.read8(1));
            telemetry.addData("Byte 2", pixy.read8(2));
            telemetry.addData("Byte 3", pixy.read8(3));
            telemetry.addData("Byte 4", pixy.read8(4));
            telemetry.addData("Byte 5", pixy.read8(5));
            telemetry.addData("Byte 6", pixy.read8(6));
            telemetry.addData("Byte 7", pixy.read8(7));
            telemetry.addData("Byte 8", pixy.read8(8));
            telemetry.addData("Byte 9", pixy.read8(9));
            telemetry.addData("Byte 10", pixy.read8(10));
            telemetry.addData("Byte 11", pixy.read8(11));
            telemetry.addData("Byte 12", pixy.read8(12));
            telemetry.addData("Byte 13", pixy.read8(13));

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", frontleftPower, backleftPower,frontrightPower,backrightPower);
            telemetry.update();
        }
    }
}
