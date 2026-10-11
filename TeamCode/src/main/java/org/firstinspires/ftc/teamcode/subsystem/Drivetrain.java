package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.robot.Robot.leftFront;
import static org.firstinspires.ftc.teamcode.robot.Robot.leftRear;
import static org.firstinspires.ftc.teamcode.robot.Robot.rightFront;
import static org.firstinspires.ftc.teamcode.robot.Robot.rightRear;
import static org.firstinspires.ftc.teamcode.subsystem.Drivetrain.State.OFF;

import org.firstinspires.ftc.teamcode.util.Subsystem;
import org.firstinspires.ftc.teamcode.util.Vector;

public class Drivetrain implements Subsystem {
    public enum State {
        OFF, DRIVE
    }
     public State state = State.OFF;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if(this.state!= state)
        this.state = state;
    }
 double powerArray[] =new double[4];
    double lateralMultiplier = 1.05;
    public void setPowerVector(Vector powerVector){
        powerVector = new Vector (powerVector.getX()*lateralMultiplier, powerVector.getY(), powerVector.getZ());
        double d = Math.max(Math.abs(powerVector.getX())+ Math.abs(powerVector.getY())+ Math.abs(powerVector.getZ()),1 );
        powerArray[0]=(powerVector.getY()+ powerVector.getX()+ powerVector.getZ())/d;
        powerArray[1]=(powerVector.getY()- powerVector.getX()+ powerVector.getZ())/d;
        powerArray[2]=(powerVector.getY()-powerVector.getX()-powerVector.getZ())/d;
        powerArray[3]=(powerVector.getY()+powerVector.getX()-powerVector.getZ())/d;

    }
    @Override
    public void periodic() {
        switch (state){
            case OFF:
                break;
            case DRIVE:
                leftFront.setPower(powerArray[0]);
                leftRear.setPower(powerArray[1]);
                rightFront.setPower(powerArray[2]);
                rightRear.setPower(powerArray[3]);
                break;
        }


    }
}
