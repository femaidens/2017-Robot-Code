package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.*;

/**
 *
 */
public class DriveDistance extends Command {

	public int ticks;
	public int distanceTravelled;
    
	public DriveDistance(int x) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		distanceTravelled = x;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.encoderLeft.reset();
    	Drivetrain.encoderRight.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveDistance(distanceTravelled);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.encoderLeft.reset();
    	Drivetrain.encoderRight.reset();
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
