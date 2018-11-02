package org.firstinspires.ftc.teamcode.TestingCode;



        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;

        import org.firstinspires.ftc.teamcode.Library.PracticeAuton;

@Autonomous(name = "Practice", group = "Competition")

public class practiceAutonomous extends PracticeAuton {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes

//moveMotor(.5,.5);
  landing(.5);
//sampling(30);
        //forward(2,1,1000); // use Autonomous_Codes here
        //marker(true);
//
//        forward(4,-1,1000);
//        turnRight(1.25,0,45);


    }
}
