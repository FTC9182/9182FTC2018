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
    private Servo lock = null;
    private final static double unlock = 0;
    private Servo marker = null;

    private final static double locked = 0.5;


    public liftUp(HardwareMap hardwareMap){
        Lifter = hardwareMap.get(DcMotor.class, "Lifter");
        lock =  hardwareMap.get(Servo.class, "lock_servo");
        marker = hardwareMap.get(Servo.class, "marker_servo");



        Lifter.setDirection(DcMotor.Direction.FORWARD);


    }
    public void lift(double power){

        Lifter.setPower(power);
    }
    public void setEncoder(Boolean encoder) {
        if (encoder) {

            Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
    public void lock(boolean close) {
        if (close) {
            lock.setPosition(locked);
        }
    }
        public void unlock(boolean open){
          if(open) {
              lock.setPosition(unlock);

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



}
