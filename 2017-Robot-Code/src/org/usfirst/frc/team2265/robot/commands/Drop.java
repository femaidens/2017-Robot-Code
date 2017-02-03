package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.subsystems.GearChute;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class Drop extends Command {
	boolean down;
	
	//retracts and extends piston to drop gear
	public Drop(boolean u) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		down = u;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// retract if piston off/extended
		// if (GearChute.gearPiston.get().equals(Value.kForward) ||
		// GearChute.gearPiston.get().equals(Value.kOff)) {
		if (down) {
			GearChute.extend();
			return;

		}
		// extend if piston is retracted
		// if (GearChute.gearPiston.get().equals(Value.kReverse)) {
		if (!down) {
			GearChute.retract();
			return;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// return true means the clause is now true, so the command will stop
		// executing
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}