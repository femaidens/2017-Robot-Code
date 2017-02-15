package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.GearChute;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateGearFlaps extends Command {
	boolean open;

	public RotateGearFlaps() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (GearChute.gearPiston1.get().equals(Value.kForward) || GearChute.gearPiston1.get().equals(Value.kOff)) {
			GearChute.retract();
			return;
		}

		if (GearChute.gearPiston2.get().equals(Value.kReverse)) {
			GearChute.extend();
		}

		open = true;
		connectArduino();

	}

	public void connectArduino() {
		if (open)
			Robot.toSend[0] = 76;
		else
			Robot.toSend[0] = 72;
		Robot.i2c.transaction(Robot.toSend, 1, null, 0);
		Timer.delay(0.0005);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		open = false;
		connectArduino();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}