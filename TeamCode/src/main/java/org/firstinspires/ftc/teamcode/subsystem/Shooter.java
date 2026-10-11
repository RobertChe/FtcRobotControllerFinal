package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.robot.Robot.primShooter;

import org.firstinspires.ftc.teamcode.util.Subsystem;

public class Shooter implements Subsystem {


    public enum  State{
        ON,OFF
    }
    public State state = State.OFF;
    public void setState(State newState){
        if(state != newState) state = newState;
    }
    public State getState(){
        return state;
    }
    @Override
    public void periodic() {
switch (state){
    case OFF:
        primShooter.setPower(0);
        break;
    case ON:
        primShooter.setPower(1);
        break;
}
    }
}
