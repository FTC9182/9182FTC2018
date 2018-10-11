package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

/**
 * Created by mohamedarab on 10/5/18.
 */

public class liftUp {
    private DcMotor Lifter = null;
    private Servo lock = null;
    private final static double unlock = 0.0;

    private final static double locked = 0.5;

    public liftUp(HardwareMap hardwareMap){
        Lifter = hardwareMap.get(DcMotor.class, "Lifter");
        lock = new Servo() {
            @Override
            public ServoController getController() {
                return null;
            }

            @Override
            public int getPortNumber() {
                return 0;
            }

            @Override
            public void setDirection(Direction direction) {

            }

            @Override
            public Direction getDirection() {
                return null;
            }

            @Override
            public void setPosition(double position) {

            }

            @Override
            public double getPosition() {
                return 0;
            }

            @Override
            public void scaleRange(double min, double max) {

            }

            @Override
            public Manufacturer getManufacturer() {
                return null;
            }

            @Override
            public String getDeviceName() {
                return null;
            }

            @Override
            public String getConnectionInfo() {
                return null;
            }

            @Override
            public int getVersion() {
                return 0;
            }

            @Override
            public void resetDeviceConfigurationForOpMode() {

            }

            @Override
            public void close() {

            }
        };


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
    public void lock(boolean close){
        if( close)
        lock.setPosition(locked);

    }
    public void unlock(boolean open){
        if(open)
        lock.setPosition(unlock);
    }


}
