package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 */
public class GyroStraight extends Command {
	private Gyro gyro;
	private double angle;
	
    public GyroStraight() {
    	gyro = new AnalogGyro(RobotMap.gyroPort);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	angle = gyro.getAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
