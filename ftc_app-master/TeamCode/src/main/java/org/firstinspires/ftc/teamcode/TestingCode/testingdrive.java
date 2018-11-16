package org.firstinspires.ftc.teamcode.TestingCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Library.DriveTrain;
import org.firstinspires.ftc.teamcode.Library.PixyCam_Analog;
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
    PixyCam_Analog pixyAnalogue=null;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        newDrive = new DriveTrain(hardwareMap);
        lift = new liftUp(hardwareMap);
        //Rev_IMU gps = new Rev_IMU(hardwareMap);
        pixyAnalogue= new PixyCam_Analog(hardwareMap);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            //gps.initialize();


            //Mecanum Stuff
            if ((gamepad1.right_trigger + gamepad1.left_trigger) > 1) {
                //Ultimate

            } else {
                r = gamepad1.right_trigger - gamepad1.left_trigger;
            }



            newDrive.MecanumDrive((-gamepad1.right_stick_y), gamepad1.right_stick_x, r);
            lift.setEncoder(false);
            lift.lift(-gamepad2.left_stick_y);
            //lift.lock(gamepad2.a);
            //lift.unlock(gamepad2.x);



            // Show the elapsed game time and wheel power.
            telemetry.addData("PixyValue: ",pixyAnalogue.getAnalogRead() );
            telemetry.addData("PixyDigital: ",pixyAnalogue.isObjectDetected());


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
