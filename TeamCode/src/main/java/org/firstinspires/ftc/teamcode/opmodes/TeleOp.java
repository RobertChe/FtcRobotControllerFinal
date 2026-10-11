package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.robot.Robot.imu;
import static org.firstinspires.ftc.teamcode.robot.Robot.initHardware;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Orientare;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;
import org.firstinspires.ftc.teamcode.util.Vector;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        initHardware(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Orientare orientare = new Orientare(telemetry);
        Shooter shooter = new Shooter();
        Drivetrain drivetrain = new Drivetrain();
        orientare.setState(Orientare.State.ON);
        imu.resetYaw();
        double loopTime = 0;
        waitForStart();
        while(opModeIsActive()){
            orientare.periodic();
            shooter.setState(Shooter.State.ON);
            shooter.periodic();
            Vector controller = new Vector(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            drivetrain.setPowerVector(controller);
            drivetrain.periodic();
            double loop = System.nanoTime();
            telemetry.addData("hz ", 1000000000 / (loop - loopTime));
            loopTime = loop;
            telemetry.update();
        }
    }
}
