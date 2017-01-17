package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	double distance, left, right, ticksPassed;
	double ticksPerRev = 1024;
	double r = 2;
	double circ = 2* r * Math.PI;
	//uble left, right,distance,ticksPerRev,circ,ticksPassed;ticksPerRev=1024;circ=4*Math.PI;

	public EncoderDrive(double d, double l, double r) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		distance = d;
		left = l;
		right = r;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Drivetrain.frontRight.setEncPosition(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.drive(left, right);
		ticksPassed = Drivetrain.frontRight.getEncPosition();
		System.out.println("Encoder Position" + Drivetrain.frontRight.getEncPosition());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(ticksPassed) > distance;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drive(0, 0);
		Drivetrain.frontRight.setEncPosition(0); //remove if we want to see how far the encoder has moved AFTER stopping
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}