package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Library.PracticeAuton;

/**
 * Created by mohamedarab on 10/27/18.
 */



    @Autonomous(name = "Depo's", group = "Competition")

    public class Blue_Depot extends PracticeAuton {
        @Override


        protected void Autonomous_Mode() { // using Autonomous_Codes



            landing(10);
            move(-.5, 0, 0, 2);
            forward(5, .5);
            marker(true);




        }
    }


