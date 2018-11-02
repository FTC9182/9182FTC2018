package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class PracticeAuton extends LinearOpMode { // sequence run by autonomous
    protected abstract void Autonomous_Mode();  // All autonomous mode declaration

    // --------------- Declaration to be edited -------------------------
    DriveTrain drive = null;
    ElapsedTime autonomous_elapsetime = new ElapsedTime();
    //Rev_IMU imu = null;
    liftUp lift = null;
    PixyCam_Analog PixySampler= null;
    // ------------------------------------------------------------------

    @Override

    // ------------------- below is for all autonomous common to all scenarios -------------
    public void runOpMode() throws InterruptedException {

        // ------- create objects to be edited ------------------
        drive = new DriveTrain(hardwareMap);
        lift = new liftUp(hardwareMap);
        //imu= new Rev_IMU(hardwareMap);
        PixySampler= new PixyCam_Analog(hardwareMap);
        //
        // ------------------------------------------------------
        latching();
        waitForStart();    // wait until the Start button is pressed


        // Add sequence common to all autonomous at the beginning here, Jewel for example
        // doing jewel
        //

        Autonomous_Mode();       // where you put autonomous sequence for each scenaria
        // You can add a line to stop the robot here if necessary
        while (opModeIsActive()) // after autonomous is done wait for manual stop or stop after the timer
        {
            idle();
        }
    }

    // ========================All autonomous codes below to be edited =========================

    public void forward(long timer_sec, double power,long pause) {

        drive.MecanumDrive(-power,0,0);

        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        drive.MecanumDrive(0,0,0); // zero power to stop
        sleep(pause);
    }

    public void landing(double timer_sec) {

lift.lift(-.2);
        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }

       lift.unlock(true);



        //imu.initialize();
        //after unlatching

        //imu.start();

    }
    public void turnRight(double timer_sec, double power,double turnAngle){
        drive.MecanumDrive(-power,0,1);

        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        drive.MecanumDrive(0,0,0); // zero power to stop
    }


    public void sampling(long timer_sec){
        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
drive.MecanumDrive(-.2,0,.5);

            if( PixySampler.isObjectDetected()){

                double direction = PixySampler.getAnalogRead()-1.15;
                drive.MecanumDrive(-.2,0,direction*.5);

            }
            else{
                drive.MecanumDrive(0,0,0);
            }
            sleep(1);
            idle();
        }
        drive.MecanumDrive(-.2,0,-.5);
        drive.MecanumDrive(0,0,0);


    }
    public void marker(boolean isLeft){
        lift.drop_Marker(true);
    }
    private void latching (){
        lift.setEncoder(true);
        lift.lock(true);
    }
    public void moveMotor(double power, double seconds){
        lift.lift(power);
        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < seconds && opModeIsActive()) { // until it passes 5 seconds
            lift.unlock(true);
            sleep(1);
            idle();
        }
    }
}