package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.teamcode.robot.Robot.imu;
import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.round;
import static java.lang.Math.tan;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.util.Subsystem;

public class Orientare implements Subsystem {
    public enum State{
        OFF,ON
    }
    public State state= State.OFF;
    public void setState(State newState){
        if(state!=newState){
            state=newState;
        }
    }
    public State getState(){
        return state;
    }
    private char Imagine[][]={
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','R','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.'}
    };
    YawPitchRollAngles angle;
    private double unghi;
    private double semnunghi,i=1,j=1;
    private void RadianiTan(){
        unghi=unghi*acos(-1.0)/180;
        unghi=tan(unghi);
    }Telemetry telemetry;
    public Orientare(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        switch (state){
            case OFF:
                break;
            case ON:
                angle=imu.getRobotYawPitchRollAngles();
                unghi=-angle.getYaw(AngleUnit.DEGREES);
                semnunghi=unghi/abs(unghi);
                unghi=abs(unghi);
                Imagine[(int)i][(int)j]='.';
                if(unghi<=45){
                    RadianiTan();
                    unghi=unghi*6;
                    unghi=round(unghi)*semnunghi;
                    i=0;
                    j=unghi+6;
                }else if(unghi<=90){
                    unghi=abs(unghi-90);
                    RadianiTan();
                    unghi=12-unghi*6;
                    unghi=round(unghi)*semnunghi;
                    i=abs(unghi-6*semnunghi);
                    if(semnunghi==-1.0)
                        j=0;
                    else
                        j=12;
                }else if(unghi<=135){
                    unghi=unghi-90;
                    RadianiTan();
                    unghi=12+unghi*6;
                    unghi=round(unghi)*semnunghi;
                    i=abs(unghi-6*semnunghi);
                    if(semnunghi==-1.0)
                        j=0;
                    else
                        j=12;
                }else{
                    unghi=abs(unghi-180);
                    RadianiTan();
                    unghi=24-unghi*6;
                    unghi=round(unghi)*semnunghi;
                    i=12;
                    if(semnunghi==-1.0)
                        j=abs(18*semnunghi-unghi);
                    else
                        j=12-abs(18*semnunghi-unghi);
                }
                Imagine[(int)i][(int)j]='O';
                for(int x=0;x<=12;x++){
                    String linie="";
                    for(int y=0;y<=12;y++){
                        linie=linie+Imagine[x][y]+"  ";
                    }
                    String afis=""+x;
                    telemetry.addData(afis,linie);
                }
                break;
        }
    }
}
