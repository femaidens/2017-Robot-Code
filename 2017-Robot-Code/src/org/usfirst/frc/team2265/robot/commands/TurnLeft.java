package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnLeft extends Command {
	//declares degrees variable
	double degrees;

	//instantiates variable
    public TurnLeft(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis)
    	degrees = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    //calls drivetrain to use the turndegreesleft method and is called in right auto command group
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.turnDegreesLeft(degrees);
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
