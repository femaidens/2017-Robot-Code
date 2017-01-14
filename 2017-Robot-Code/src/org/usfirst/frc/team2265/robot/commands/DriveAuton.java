package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

/**
 *
 */
public class DriveAuton extends Command {
	private double left, right;

    public DriveAuton(double l, double r) {
        // Use requires() here to declare subsystem dependencies
        
    	left = l;
    	right = r;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(left,right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
