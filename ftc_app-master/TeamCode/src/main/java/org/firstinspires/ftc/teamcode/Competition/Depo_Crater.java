package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Library.TournamentAutonomous;

/**
 * Created by mohamedarab on 10/27/18.
 */



@Autonomous(name = "DepotCrater", group = "Competition")
@Disabled
public class Depo_Crater extends TournamentAutonomous {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes





        forward(4, .6);
        marker(true);
        forward(2, -.4);





        move(0,0,.3,2);
        forward(6, -.5);




    }
}


