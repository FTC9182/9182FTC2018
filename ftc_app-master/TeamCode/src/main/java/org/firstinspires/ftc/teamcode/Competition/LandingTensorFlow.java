package org.firstinspires.ftc.teamcode.Competition;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Library.TournamentAutonomous;

@Autonomous(name = "LandingTensorFlow", group = "Competition")

public class LandingTensorFlow extends TournamentAutonomous {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes




        landing(10);

//        move(0,0,-.5,.3);
//
//        move(-.5, 0, 0, .5);//left
//
//        move(0,0,0,1);//wait
//
//        move(0,.5,0,.2); //forward
//
//        move(.5,0,0,.4);//right
//        //I added the moving back the center
//        //move(0,0,-.5,.3);//rotate adjust
//
//        move(0,-.5,0,.2);// back
//        runLift(1,.5);
//
//        move(0,0,.5,2);// adjust for phone
        //move to landing^
        Tensorflow_driveup();


    }
}
