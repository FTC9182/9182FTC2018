package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Library.TournamentAutonomous;





@Autonomous(name = "Red_Depot", group = "Competition")
@Disabled
public class Red_Depot extends TournamentAutonomous {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes

//moveMotor(.5,.5);


        forward(1,1); // use Autonomous_Codes here
        marker(true);
//
//        forward(4,-1,1000);
//        turnRight(1.25,0,45);


    }
}
