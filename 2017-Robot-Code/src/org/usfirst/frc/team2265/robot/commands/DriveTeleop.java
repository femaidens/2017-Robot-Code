package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

/**
 *
 */

public class DriveTeleop extends Command {


	public DriveTeleop() {
		// Use requires() here to declare subsystem dependencies
		// requires(Robot.exampleSubsystem);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.drive();
		
		//gets the distance
		double distanceLeft = Drivetrain.encoderLeft.getDistance();
		double distanceRight = Drivetrain.encoderRight.getDistance();
		
		//gets encoder values for left and right
		double leftCount = Drivetrain.encoderLeft.get();
		double rightCount = Drivetrain.encoderRight.get();
		
		//prints distance to console and smartdashboard
		System.out.println("Left Encoder Distance: " + distanceLeft);
		System.out.println("Right Encoder Distance: " + distanceRight);
		SmartDashboard.putNumber("Left Encoder Distance: ", distanceLeft);
		SmartDashboard.putNumber("Right Encoder Distance: ", distanceRight);
		
		//prints position to console and smartdashboard
		System.out.println("Left Encoder Position: " + leftCount);
		System.out.println("Right Encoder Position: " + rightCount);
		SmartDashboard.putNumber("Left Encoder Position: ", leftCount);
		SmartDashboard.putNumber("Right Encoder Position: ", rightCount);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
