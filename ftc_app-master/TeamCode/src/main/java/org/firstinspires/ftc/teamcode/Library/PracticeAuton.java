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
    public PixyCam_Analog PixySampler = null;
    TensorFlow tensorflow;
    ElapsedTime elapsedTime = new ElapsedTime();
    // ------------------------------------------------------------------

    @Override

    // ------------------- below is for all autonomous common to all scenarios -------------
    public void runOpMode() throws InterruptedException {

        // ------- create objects to be edited ------------------
        drive = new DriveTrain(hardwareMap);
        lift = new liftUp(hardwareMap);
        //imu= new Rev_IMU(hardwareMap);
        PixySampler = new PixyCam_Analog(hardwareMap);
        tensorflow= new TensorFlow(hardwareMap);
        //
        // ------------------------------------------------------

        lift.setEncoder(true);

        waitForStart();    // wait until the Start button is pressed
        autonomous_elapsetime.reset();

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

    public void forward(long timer_sec, double power) {

        drive.MecanumDrive(power, 0, 0);

        autonomous_elapsetime.reset();
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        drive.MecanumDrive(0, 0, 0); // zero power to stop

    }

    public void landing(double timer_sec) {
        int intial_position = lift.returnEncoder();

        lift.lift(.5);
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive() && (intial_position-lift.returnEncoder()  ) > -700) { // until it passes 5 seconds

            idle();
            telemetry.addData("Encoder: ", intial_position-lift.returnEncoder());

            telemetry.update();
        }
        lift.lift(0);
        lift.lift(-.5);

        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive() && (intial_position-lift.returnEncoder()  ) < 2500) { // until it passes 5 seconds

            idle();
            telemetry.addData("Encoder: ", intial_position-lift.returnEncoder());

            telemetry.update();
        }
        lift.lift(0);


        move(0,-.5,0,.3);

        lift.lift(.2);

        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive() && (intial_position-lift.returnEncoder()  ) > 2300) { // until it passes 5 seconds

            idle();
            telemetry.addData("Encoder: ", intial_position-lift.returnEncoder());

            telemetry.update();
        }
lift.lift(0);

    }

    public void turnRight(double timer_sec, double power, double turnAngle) {
        drive.MecanumDrive(-power, 0, 1);

        autonomous_elapsetime.reset();
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        drive.MecanumDrive(0, 0, 0); // zero power to stop
    }


    public void sampling(long timer_sec) {
        forward(1,.5);
        forward(1,0);
        telemetry.addData("Object",PixySampler.isObjectDetected());
        telemetry.update();
        if(PixySampler.isObjectDetected()){
          forward(1,.5);
            telemetry.addData("Object",PixySampler.isObjectDetected());
            telemetry.update();


        }
        else{
            move(-.5,0,0,1);
            forward(1,0);
            if(PixySampler.isObjectDetected()){
                forward(1,.5);
            }

        }
    }

    public void marker(boolean isLeft) {

        lift.drop_Marker(true);
    }

    private void latching() {
        lift.setEncoder(true);
        //lift.lock(true);
    }

    public void moveMotor(double power, double seconds) {
        lift.lift(power);
        autonomous_elapsetime.reset();
        while (autonomous_elapsetime.seconds() < seconds && opModeIsActive()) { // until it passes 5 seconds
           // lift.unlock(true);
            sleep(1);
            idle();
        }
    }

    public void  move(double x, double y, double r, double timer_sec) {
        autonomous_elapsetime.reset();
        drive.MecanumDrive(y, x, r);

        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        drive.MecanumDrive(0, 0, 0); // zero power to stop

    }

    public void pixyfinder() {


        while (autonomous_elapsetime.seconds() < 20 && opModeIsActive()) { // until it passes 5 seconds


                if (PixySampler.isObjectDetected()) {
                    double direction = PixySampler.getAnalogRead() - 1.15;
                    drive.MecanumDrive(.6, 0, direction * .2);


                    telemetry.addData("Pixy value: ", PixySampler.getAnalogRead());
                    telemetry.update();
                }
                else {
                    while(PixySampler.isObjectDetected()==false) {
                        move(0, 0, 0, .5);

                        move(0, 0, .2, 1);
                    }
                }

            sleep(1);
            idle();


        }


    }
    public void landingsequence(double timer_sec){
        lift.lift(-1);
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }

    }
    public void isPixy(double timer_sec){



        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            telemetry.addData("object",PixySampler.isObjectDetected());
            telemetry.update();
            sleep(1);
            idle();
        }
    }

    public void TensorFlowA(double timer_sec){
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()){
            elapsedTime.reset();
            telemetry.addData("is phone compatible?:", tensorflow.isPhoneCompatible());
            if (tensorflow.isPhoneCompatible()) {
                int location_direction = tensorflow.runTensorFlow();
                telemetry.addData("Location:", location_direction);
                if (location_direction < 2) { // only -1, 0, 1 for left, middle and right
                    telemetry.addData("Estimate angle: ", tensorflow.angle_gold);
                    if (location_direction < 0) {

                        move(0,0,.5,.3);

                        forward(3,.5);

                        move(0,0,-.5,.3);


                        forward(3,.5);
                        lift.drop_Marker(true);

                        telemetry.addData("Gold location: ", "Left");
                    } else if( location_direction<1) {
                        telemetry.addData("Gold location: ", "Center");

                        forward(4,.5);
                        lift.drop_Marker(true);

                    } else if( location_direction<2) {

                        move(0,0,-.5,.3);
                        forward(3,.5);

                        move(0,0,.5,.3);

                        forward(3,.5);

                        lift.drop_Marker(true);

                        telemetry.addData("Gold location: ", "Right");

                    }

                }
            }
            telemetry.update(); // to actually send to the phone message for debugging purpose
        }
        tensorflow.stopTensorFlow();  // stop it at the end of autonomous
    }
    public void runLift(double timer_sec,double power){
        lift.lift(power);

        autonomous_elapsetime.reset();
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        lift.lift(0); // zero power to stop

    }
    public void tensorflowTest(double timer_sec) {
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) {
            elapsedTime.reset();
//            int Left_count = 0;
//            int Center_count= 0;
//            int Right_count = 0;
//            if(tensorflow.angle_gold>-20&&tensorflow.angle_gold<-15)
//            tensorflow.angle_gold
            forward((long).5,.5);
            if( tensorflow.runTensorFlow()==7){
forward(2,.5);
            }
            else{
                move(0,-.5,0,1);
                if(tensorflow.runTensorFlow()==7){
                    forward(2,.5);
                }
                else{
                    move(0,.5,0,2);
                    forward(2,.5);
                }
            }

        }
    }

}




