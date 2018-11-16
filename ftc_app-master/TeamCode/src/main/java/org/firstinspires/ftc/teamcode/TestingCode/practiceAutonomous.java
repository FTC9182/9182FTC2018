package org.firstinspires.ftc.teamcode.TestingCode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Library.PracticeAuton;

@Autonomous(name = "PixyTest", group = "Competition")

public class practiceAutonomous extends PracticeAuton {
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
