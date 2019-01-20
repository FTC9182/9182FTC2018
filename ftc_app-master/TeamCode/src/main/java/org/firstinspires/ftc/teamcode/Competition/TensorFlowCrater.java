package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Library.TournamentAutonomous;





@Autonomous(name = "TensorFlowCrater", group = "Competition")

public class TensorFlowCrater extends TournamentAutonomous {
    @Override


    protected void Autonomous_Mode() { // using Autonomous_Codes

        landing(10);
        TensorFlowCrater();


    }
}
