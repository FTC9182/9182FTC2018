package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;

/**
 * Created by mohamedarab on 10/5/18.
 */

public class liftUp {
    private DcMotor Lifter = null;
    private DcMotor Liftermotor2 = null;
    private DcMotor extender = null;

    private DcMotor intake = null;


    private Servo marker = null;
    private Servo door = null;




    public liftUp(HardwareMap hardwareMap){
        Lifter = hardwareMap.get(DcMotor.class, "Lifter");

        marker = hardwareMap.get(Servo.class, "marker_servo");
        Liftermotor2 = hardwareMap.get(DcMotor.class, "lifter_motor2");

        extender = hardwareMap.get(DcMotor.class, "extender_motor");
        intake = hardwareMap.get(DcMotor.class, "intake_motor");

        door = hardwareMap.get(Servo.class, "door_servo");





        Lifter.setDirection(DcMotor.Direction.FORWARD);


    }
    public void lift(double power){

        Lifter.setPower(power);
        Liftermotor2.setPower(power);
    }
    public void setEncoder(Boolean encoder) {
        if (encoder) {

            Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }

      public void drop_Marker(boolean drop){
        if(drop) {
            marker.setPosition(0.5);
        }
        else{
            marker.setPosition(0.0);
        }
      }
      public int returnEncoder(){
      return Lifter.getCurrentPosition();

      }
      public void extender(float power){
          extender.setPower(power);
      }

      public void intake(float power){
          intake.setPower(power);
      }

      public void intakeDoor(boolean open){
          if(open){
              door.setPosition(0);
          }
          else{
              door.setPosition(1);
          }
      }



}
