package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by mohamedarab on 9/12/18.
 */

public class DriveTrain {
    private DcMotor FrontLeft = null;
    private DcMotor FrontRight = null;
    private DcMotor BackLeft = null;
    private DcMotor BackRight = null;



    public DriveTrain(HardwareMap hardwareMap) {    // constructor to create object
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft_drive");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight_drive");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft_drive");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight_drive");



        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery


        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        BackRight.setDirection(DcMotor.Direction.FORWARD);



        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void MecanumDrive(double y, double x, double r) {

        double frontleftPower;
        double frontrightPower;
        double backleftPower;
        double backrightPower;


        //frontrightPower = (y + x);
        //frontleftPower = (y - x);
        //backrightPower = (y - x);
        //ckleftPower = (y + x);


        //Calculates the direction based off of the joysticks
        frontrightPower = (y + x - r);
        frontleftPower = (y - x + r);
        backrightPower = (y - x - r);
        backleftPower = (y + x + r);

        double nValue = Math.max(Math.max(Math.max((Math.abs(frontrightPower)),
                (Math.abs(frontleftPower))),Math.abs(backrightPower)),Math.abs(backleftPower));
        //normalizes the power to account for the rotation^
        if(nValue<1) {
            nValue=1;
        }

        FrontLeft.setPower(frontrightPower/nValue);
        FrontRight.setPower(frontleftPower/nValue);
        BackLeft.setPower(backrightPower/nValue);
        BackRight.setPower(backleftPower/nValue);

        //If this doesn't work it is 100% John's fault for taking Mohamed's hot cheetos


    }
     public void TankDrive(double right_y, double left_y){

         double frontleftPower;
         double frontrightPower;
         double backleftPower;
         double backrightPower;

         frontleftPower  = left_y;
            frontrightPower = right_y ;
            backleftPower  =  left_y ;
            backrightPower =  right_y ;

         FrontLeft.setPower(frontrightPower);
         FrontRight.setPower(frontleftPower);
         BackLeft.setPower(backrightPower);
         BackRight.setPower(backleftPower);
     }

}

