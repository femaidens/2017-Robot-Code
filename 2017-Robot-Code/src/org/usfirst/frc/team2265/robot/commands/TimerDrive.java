package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 */
public class TimerDrive extends Command {
	private double left, right, timePassed, time;
	Timer timer;
	
    public TimerDrive(double l, double r, double seconds) {
        // Use requires() here to declare subsystem dependencies
        time = seconds;
    	left = l;
    	right = r;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timePassed = timer.get();
    	Robot.drivetrain.drive(left,right);
		//if (Drivetrain.encoder.getEncPosition() <= distance*ticksPerRev/circ){
			//Robot.drivetrain.drive(0,0);
		//}	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timePassed > time;
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
