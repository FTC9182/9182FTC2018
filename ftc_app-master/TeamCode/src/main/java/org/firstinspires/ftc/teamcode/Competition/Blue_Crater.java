package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Library.PracticeAuton;

/**
 * Created by mohamedarab on 10/27/18.
 */



@Autonomous(name = "ForwardDrop", group = "Competition")

public class Blue_Crater extends PracticeAuton {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes




        forward(2,1); // use Autonomous_Codes here
        marker(true);




    }
}


