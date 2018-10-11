package org.firstinspires.ftc.teamcode.TestingCode;



        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

        import org.firstinspires.ftc.teamcode.Library.PracticeAuton;

@Autonomous(name = "Practice", group = "Competition")

public class practiceAutonomous extends PracticeAuton {
    @Override

    protected void Autonomous_Mode() { // using Autonomous_Codes

        forward(4,1,1000); // use Autonomous_Codes here

        forward(4,-1,1000);
        turnRight(1.25,0);


    }
}
