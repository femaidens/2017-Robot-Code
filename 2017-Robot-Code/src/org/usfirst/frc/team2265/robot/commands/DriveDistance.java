package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	double distance, leftVel, rightVel;

    public DriveDistance(double d, double l, double r) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	distance = d;
    	leftVel = l;
    	rightVel = r;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.drive(leftVel, rightVel);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Drivetrain.ultrasonicLeft.getRangeInches() <= distance && Drivetrain.ultrasonicRight.getRangeInches() <= distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.drive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
