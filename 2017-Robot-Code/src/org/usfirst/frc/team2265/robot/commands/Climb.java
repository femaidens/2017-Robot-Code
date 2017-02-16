package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class Climb extends Command {
	double speed;
	boolean climbing;

	public Climb(double s) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		speed = s;
	}

	// Called just before this Command runs the first time

	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	// starts rolling climber CANTalon
	// sets the speed if the climber to half speed

	protected void execute() {
		Robot.climber.spin(speed);
		climbing = true;
		connectArduino();
	}
	
	public void connectArduino() {
		if (climbing)
			Robot.toSend[0] = 6;
		else
			Robot.toSend[0] = 7;
		Robot.i2c.transaction(Robot.toSend, 1, null, 0);
		Timer.delay(0.0005);
}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climber.stop();
		climbing = false;
		connectArduino();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	// stops CANtalons from rotating
	protected void interrupted() {
		Robot.climber.stop();
		climbing = false;
		connectArduino();
	}

}