package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.GearChute;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateGearFlaps extends Command {

	public RotateGearFlaps() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	//if the pistons are extended it retracts them and then the chute is open (true)
	protected void execute() {
		if (GearChute.gearPiston1.get().equals(Value.kForward) || GearChute.gearPiston1.get().equals(Value.kOff)) {
			GearChute.retract();
			Robot.chuteOpen = true;
			Robot.connectArduino();
			return;
		}
//if the pistons are retracted then extend them, and means chute is closed (false)
		if (GearChute.gearPiston2.get().equals(Value.kReverse)) {
			GearChute.extend();
			Robot.chuteOpen = false;
			Robot.connectArduino();
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	//if auto aligning is finished (2) and the chute is open (true) then assume that gear is on peg and set auto align back to not auto aligning
	protected void end() {
		if(Robot.autoAligning == 2 && Robot.chuteOpen == true)
			Robot.autoAligning = 0;
		Robot.connectArduino();
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}