package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {
	double degrees;
    public TurnDegrees(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	degrees = d-1;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	

		if (degrees > 0) {
			while (Drivetrain.gyro.getAngle() < degrees) {

				Drivetrain.frontRight.set(0.25);
				Drivetrain.rearRight.set(0.25);
				Drivetrain.frontLeft.set(0.25);
				Drivetrain.rearLeft.set(0.25);
				System.out.println(Drivetrain.gyro.getAngle());
			}
		} else {
			while (Drivetrain.gyro.getAngle() > degrees) {
				Drivetrain.frontRight.set(-0.25);
				Drivetrain.rearRight.set(-0.25);
				Drivetrain.frontLeft.set(-0.25);
				Drivetrain.rearLeft.set(-0.25);
				System.out.println(Drivetrain.gyro.getAngle());
			}
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.gyro.reset();
    	Drivetrain.frontRight.set(0.0);
		Drivetrain.rearRight.set(0.0);
		Drivetrain.frontLeft.set(0.0);
		Drivetrain.rearLeft.set(0.0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
