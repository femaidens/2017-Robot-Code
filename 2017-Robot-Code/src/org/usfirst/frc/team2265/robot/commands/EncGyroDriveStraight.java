package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

public class EncGyroDriveStraight extends Command {
	double distance, left, right, distanceLeft, distanceRight;
	AnalogGyro gyro;
	double angle;
	double leftVel = Drivetrain.frontRight.get();
	double rightVel = Drivetrain.frontLeft.get();

	public EncGyroDriveStraight(double d, double l, double r) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		distance = d;
		right = r;
		left = l;
		gyro = new AnalogGyro(RobotMap.gyroPort);
		angle = gyro.getAngle();

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();
		gyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//using gyro, the robot would drive straight
		if (angle > 90 && angle < 180) {// turning left
			Drivetrain.frontRight.set(rightVel - 0.1);
			Drivetrain.rearRight.set(rightVel - 0.1);
		} else if (angle < 90 && angle > 0) {
			Drivetrain.frontLeft.set(leftVel - 0.1);
			Drivetrain.rearLeft.set(leftVel - 0.1);
		}
		//using encoders, the robot would travel the desired distance
		distanceLeft = Drivetrain.encoderLeft.getDistance();
		distanceRight = Drivetrain.encoderRight.getDistance();
		System.out.println("Left Encoder Position" + distanceLeft);
		System.out.println("Right Encoder Position" + distanceRight);
		SmartDashboard.putNumber("Left Encoder Position: ", distanceLeft);
		SmartDashboard.putNumber("Right Encoder Position: ", distanceRight);   

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//stop if either side of the robot travels more than the desired distance
		return Math.abs(distanceLeft) > distance || Math.abs(distanceRight) > distance;
	

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drive(0, 0);
		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();// remove if we want to see how far the
										// encoder has moved AFTER stopping

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}