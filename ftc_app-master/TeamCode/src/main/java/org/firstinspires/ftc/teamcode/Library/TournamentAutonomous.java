package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class TournamentAutonomous extends LinearOpMode { // sequence run by autonomous
    protected abstract void Autonomous_Mode();  // All autonomous mode declaration

    // --------------- Declaration to be edited -------------------------
    //ModernRoboticsI2cRangeSensor rangeSensor;
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
        //PixySampler = new PixyCam_Analog(hardwareMap);

        //
        // ------------------------------------------------------
        //rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");
        lift.setEncoder(true);
        drive.enableEncoder();

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

        lift.lift(.9);
        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive() && (intial_position-lift.returnEncoder()  ) > -800) { // until it passes 5 seconds

            idle();
            telemetry.addData("Encoder: ", intial_position-lift.returnEncoder());

            telemetry.update();
        }
        lift.lift(0);
        lift.lift(-.5);

        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive() && (intial_position-lift.returnEncoder()  ) < 1800) { // until it passes 5 seconds

            idle();
            telemetry.addData("Encoder: ", intial_position-lift.returnEncoder());

            telemetry.update();
        }
        lift.lift(0);


        move(0,-.5,0,.3);

        //lift.lift(.2);

//        while (autonomous_elapsetime.seconds() < timer_sec && opModeIsActive() && (intial_position-lift.returnEncoder()  ) > 2300) { // until it passes 5 seconds
//
//            idle();
//            telemetry.addData("Encoder: ", intial_position-lift.returnEncoder());
//
//            telemetry.update();
//        }
//lift.lift(0);


        move(0,0,-.5,.15);

        move(-.5, 0, 0, .5);//left

        //move(0,0,0,1);//wait

        move(0,.5,0,.2); //forward

        move(.5,0,0,.4);//right
        //I added the moving back the center
        //move(0,0,-.5,.3);//rotate adjust

        move(.5,0,0,.3); //right again

        move(0,-.5,0,.2);// back


        runLift(1,.5);

//        move(0,0,.5,2);// adjust for phone
//        move(0,-.5,0,.2);


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




    public void marker(boolean isLeft) {

        lift.drop_Marker(true);
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
//            forward((long).5,.5);
//            if( tensorflow.runTensorFlow()==7){
//forward(2,.5);
//            }
//            else{
//                move(0,-.5,0,1);
//                if(tensorflow.runTensorFlow()==7){
//                    forward(2,.5);
//                }
//                else{
//                    move(0,.5,0,2);
//                    forward(2,.5);
//                }
//            }
//
            if (tensorflow.runTensorFlow() == 7) {
                if (tensorflow.angle_gold > -28 && tensorflow.angle_gold < -10) {
                    //move(0,0,-.5,1.4); //face forward
                    move(0, 0, -.5, 1);
                    move(0, .5, 0, 1);
                    telemetry.addData("left", tensorflow.angle_gold);// if it sees the cube between -28 and -10
                } else if (tensorflow.angle_gold > -10 && tensorflow.angle_gold < 10) {
                    move(0, 0, -.5, 1.7); //face forward
                    move(0, .5, 0, 2);
                    telemetry.addData("center", tensorflow.angle_gold);//if it sees the cube between -10 and 10
                } else if (tensorflow.angle_gold > -10 && tensorflow.angle_gold < 10) {
                    move(0, 0, -.5, 2.2); //face forward
                    move(0, 0, -.5, 2);
                    telemetry.addData("right", tensorflow.angle_gold);// if it sees the cube between -10 and 28
                } else {

                }
            }
            telemetry.update();


        }
    }
        public void Tensorflow_driveup(){
            tensorflow= new TensorFlow(hardwareMap);

            move(0,.5,0,1.2);//.6 for closer//.9
            move(0, 0, .5, 1.7); //face side
            //move(0,0,0,3.5);//wait
            telemetry.addData("Cube: ",tensorflow.runTensorFlow() );
            telemetry.addData("confidence",tensorflow.getConfidence());
            telemetry.update();
            if(tensorflow.checkConfidence()){//center
             telemetry.addData("Cube is seen: ", true);
             telemetry.update();
                move(0, 0, -.5, 1.7); //face forward
                move(0,.5,0,4); //hit maker and drive to depot
                marker(true);
                move(0,-.5,0,1.5);




            }
            else{
                move(0,.5,0,1.2);//left
                //move(0,0,0,3.5);
                telemetry.addData("Cube: ",tensorflow.runTensorFlow()==7 );
                telemetry.update();
                if(tensorflow.checkConfidence()){
                    telemetry.addData("Cube is seen: ", true);
                    telemetry.update();
                    move(0, 0, -.5, 1.86); //face forward
                    move(0,.5,0,2);// hit marker
                    move(0,0,0,0); //pause
                    move(0,0,-.5,.6);// rotate to face depot
                    move(0,.5,0,2);// drive toward depot
                    marker(true);
                    move(0,-.5,0,1.5);



                }
                else{//right
                    move(0,-.5,0,2.5);//drive to the right
                    move(0, 0, -.5, 1.7); //face forward
                    move(0,.5,0,2);//hit mineral
                    move(0,0,.5,.6);// rotate to face depot
                    move(0,.5,0,2);//drive toward depot
                    marker(true);
                    move(0,-.5,0,1.5);
                }

            }
    }
    public void TensorFlowCrater(){
        tensorflow= new TensorFlow(hardwareMap);
        move(0,.5,0,1.2);//.6 for closer//.9
        move(0, 0, .5, 1.7); //face side
        //move(0,0,0,3.5);//wait
        telemetry.addData("Cube: ",tensorflow.runTensorFlow() );
        telemetry.addData("confidence",tensorflow.getConfidence());
        telemetry.update();
        if(tensorflow.checkConfidence()){//center
            telemetry.addData("Cube is seen: ", true);
            telemetry.update();
            move(0, 0, -.5, 1.7); //plow towards crater
            move(0,.5,0,.95); //hit maker and drive to crater

            move(0,-.5,0,.75);//back up
            move(0,0,.5,3.4);//turn around

            move(0,-.5,0, 1.7);// up and park




            //marker(true);
            //move(0,-.5,0,1.5);




        }
        else{
            move(0,.5,0,1.2);//left
            //move(0,0,0,3.5);
            telemetry.addData("Cube: ",tensorflow.runTensorFlow()==7 );
            telemetry.update();
            if(tensorflow.checkConfidence()){
                telemetry.addData("Cube is seen: ", true);
                telemetry.update();
                move(0, 0, -.5, 1.86); //face forward
                move(0,.5,0,.95);// hit marker

                move(0,-.5,0,.75);//back up
                move(0,0,.5,3.4);//turn around

                move(0,-.5,0, 1.7);// up and park



            }
            else{//right
                move(0,-.5,0,2.5);//drive to the right
                move(0, 0, -.5, 1.7); //face forward
                move(0,.5,0,.95);//hit mineral
                move(0,-.5,0,.75);//move back

                move(0,-.5,0,.75);//back up
                move(0,0,.5,3.4);//turn around

                move(0,-.5,0, 1.7);// up and park


            }

        }
    }
    public void Tensorflowmarker(){
        tensorflow= new TensorFlow(hardwareMap);
        move(0,1,0,.6);//.6 for closer//.9
        move(0, 0, .5, 1.7); //face side ** changed from 1.7 &&
        //move(0,0,0,3.5);//wait
        telemetry.addData("Cube: ",tensorflow.runTensorFlow() );
        telemetry.addData("confidence",tensorflow.getConfidence());
        telemetry.update();
        if(tensorflow.checkConfidence()){//center
            telemetry.addData("Cube is seen: ", true);
            telemetry.update();
            move(0, 0, -1, .89); //plow towards crater &&
            move(0,.5,0,.9); //hit maker and drive to crater

            move(0,-.5,0,.8);//move back

            move(0, 0, .5, 1.7);//face wall &&

            move(0, 1, 0, 1.95);//move to wall**orignal 1.75

            move(0,0,.5,.83);// rotate to face depot

            move(0,1,0,1.25);//zoom

            move(0,0,0,.5);


            marker(true);

            move(0,0,0,.5);

            move(0,-1,0,2.6);







        }
        else{
            move(0,.5,0,1.2);//left
            //move(0,0,0,3.5);
            telemetry.addData("Cube: ",tensorflow.runTensorFlow()==7 );
            telemetry.update();
            if(tensorflow.checkConfidence()){
                telemetry.addData("Cube is seen: ", true);
                telemetry.update();
                move(0, 0, -1, .93); //face forward// 1.86
                move(0,.5,0,.9);// hit marker

                move(0,-.5,0,.8);//move back

                move(0, 0, 1, .86);//face wall//&&

                move(0, 1, 0, 1.15);// move to wall

                move(0,0,1,.515);// rotate to face depot

                move(0,1,0,1.35);//zoom

                marker(true);

                move(0,0,0,.5);

                move(0,-1,0,2.3);




            }
            else{//right
                move(0,-1,0,1.35);//drive to the right
                move(0, 0, -1, .86); //face forward
                move(0,.5,0,1.0);//hit mineral
                move(0,-.5,0,.8);//move back

                move(0, 0, 1, .82);//face wall *small change 1.7

                move(0, 1, 0, 3);// move to wall


                move(0,0,1,.43);// rotate to face depot&&

                move(0,1,0,1.25);//zoom

                marker(true);

                move(0,0,0,.5);

                move(0,-1,0,2.6);
            }

        }

    }
    public void Turn(){
        move(0,0,1,.86);
    }

    public void DepotSidePark(){
        tensorflow= new TensorFlow(hardwareMap);

        move(0,1,0,.6);//.6 for closer//.9
        move(0, 0, .5, 1.7); //face side ** changed from 1.7 &&
        //move(0,0,0,3.5);//wait
        telemetry.addData("Cube: ",tensorflow.runTensorFlow() );
        telemetry.addData("confidence",tensorflow.getConfidence());
        telemetry.update();
        if(tensorflow.checkConfidence()){//center
            telemetry.addData("Cube is seen: ", true);
            telemetry.update();
            move(0, 0, -1, .89); //face mineral
            move(0,.5,0,4); //hit maker and drive to depot
            marker(true);
            move(0,0,-1,.43);
            move(0,1,0,.5);
            move(0,0,1,.86);
            move(0,1,0,2.4);




        }
        else{
            move(0,1,0,.6);//left
            //move(0,0,0,3.5);
            telemetry.addData("Cube: ",tensorflow.runTensorFlow()==7 );
            telemetry.update();
            if(tensorflow.checkConfidence()){
                telemetry.addData("Cube is seen: ", true);
                telemetry.update();
                move(0, 0, -1, .89); //plow towards crater &&
                move(0,.5,0,2);// hit marker
                move(0,0,-.5,.6);// rotate to face depot
                move(0,.5,0,2);// drive toward depot
                marker(true);
                move(0,0,-1,.26);
                move(0,-1,0,2.4);



            }
            else{//right
                move(0,-.5,0,2.5);//drive to the right
                move(0, 0, -1, .89); //plow towards crater &&
                move(0,.5,0,2);//hit mineral
                move(0,0,.5,.6);// rotate to face depot
                move(0,.5,0,2);//drive toward depot
                marker(true);
                move(0,0,1,.26);
                move(0,-1,0,2.4);

            }

        }
    }
    }







