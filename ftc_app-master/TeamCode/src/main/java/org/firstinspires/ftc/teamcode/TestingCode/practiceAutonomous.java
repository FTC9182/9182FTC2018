package org.firstinspires.ftc.teamcode.TestingCode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Library.TournamentAutonomous;

@Autonomous(name = "PixyTest", group = "Competition")
@Disabled
public class practiceAutonomous extends TournamentAutonomous {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes

//sampling(30);

        pixyfinder();
//        landing(10);
//        move(-.5, 0, 0, 2);
//        forward(5, .5);
//        marker(true);


    }
}
