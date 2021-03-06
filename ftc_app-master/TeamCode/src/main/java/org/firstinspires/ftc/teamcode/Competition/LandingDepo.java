package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Library.TournamentAutonomous;

/**
 * Created by mohamedarab on 10/27/18.
 */



    @Autonomous(name = "LandingDepo", group = "Competition")
@Disabled
    public class LandingDepo extends TournamentAutonomous {
        @Override


        protected void Autonomous_Mode() { // using Autonomous_Codes



            landing(10);
//
//            move(-.5, 0, 0, 1);//left
//
//            move(0,0,0,1);//wait
//
//            move(0,.5,0,.2); //forward
//
//            move(.5,0,0,.4);//right
            //I added the moving back the center
            //move(0,0,-.5,.3);//rotate adjust

            forward(3, .5);
            marker(true);




        }

    /**
     * Created by mohamedarab on 10/27/18.
     */



    @Autonomous(name = "LandDepotOnly", group = "Competition")

    public static class LandingPixy extends TournamentAutonomous {
        @Override


        protected void Autonomous_Mode() { // using Autonomous_Codes



            landing(10);

            move(0,0,-.5,.3);

            move(-.5, 0, 0, .5);//left

            move(0,0,0,1);//wait

            move(0,.5,0,.2); //forward

            move(.5,0,0,.4);//right
            //I added the moving back the center
            //move(0,0,-.5,.3);//rotate adjust

            move(0,-.5,0,.2);// back

            runLift(.5,.5);

            forward(4,.5);

            marker(true);

            forward(2,-.5);


        }
    }
}


