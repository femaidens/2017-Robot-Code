package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraAuto extends Command {
	boolean done = false;

	public CameraAuto() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.autoAlign();
		connectArduino();
		done = true;
	}

	public void connectArduino() {
		if (!done)
			Robot.toSend[0] = 3;
		else
			Robot.toSend[0] = 4;
		Robot.i2c.transaction(Robot.toSend, 1, null, 0);
		Timer.delay(0.0005);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;

	}

	// Called once after isFinished returns true
	protected void end() {
		connectArduino();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		done = true;
		connectArduino();
	}

}