package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;





/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")


public class testingdrive extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeft = null;
    private DcMotor FrontRight = null;
    private DcMotor BackLeft = null;
    private DcMotor BackRight = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        FrontLeft  = hardwareMap.get(DcMotor.class, "FrontLeft_drive");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight_drive");
        BackLeft  = hardwareMap.get(DcMotor.class, "BackLeft_drive");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight_drive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        BackRight.setDirection(DcMotor.Direction.FORWARD);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double frontleftPower;
            double frontrightPower;
            double backleftPower;
            double backrightPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            //frontleftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            //frontrightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
            //backleftPower    = Range.clip(drive - turn, -1.0, 1.0) ;
            //backrightPower   = Range.clip(drive + turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            frontleftPower  = -gamepad1.left_stick_y ;
            frontrightPower = -gamepad1.right_stick_y ;
            backleftPower  = -gamepad1.left_stick_y ;
            backrightPower = -gamepad1.right_stick_y ;


            //Mechanum Stuff
//            frontleftPower = gamepad1.right_stick_y-gamepad1.right_stick_x;
//            frontrightPower= gamepad1.right_stick_y+gamepad1.right_stick_x;
//            backleftPower = gamepad1.right_stick_y+gamepad1.right_stick_x;
//            backrightPower=gamepad1.right_stick_y-gamepad1.right_stick_x;

            // Send calculated power to wheels
            FrontLeft.setPower(frontleftPower);
            FrontRight.setPower(frontrightPower);
            BackLeft.setPower(frontleftPower);
            BackRight.setPower(frontrightPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", frontleftPower, backleftPower,frontrightPower,backrightPower);
            telemetry.update();
        }
    }
}
