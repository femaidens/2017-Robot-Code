package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShift extends Command {

	boolean shifted;
    public GearShift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
protected void execute() {
    	
    	if (Robot.drivetrain.gearShifter.get().equals(DoubleSolenoid.Value.kForward)|| Robot.drivetrain.gearShifter.get().equals(DoubleSolenoid.Value.kOff)){
    		Robot.drivetrain.shiftToSpeed();
    		shifted = true;
    		return;
    	}
    	
    	if(Robot.drivetrain.gearShifter.get().equals(DoubleSolenoid.Value.kReverse)|| Robot.drivetrain.gearShifter.get().equals(DoubleSolenoid.Value.kOff)){
    		Robot.drivetrain.shiftToPower(); 
    		shifted = true; 
    		return;
    	}
    }
    protected boolean isFinished() {
        return shifted;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
