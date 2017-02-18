package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraAuto extends Command {
	 public static boolean done;
	
	public CameraAuto() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		done = false;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		done = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.autoAlign();
		/*if (Robot.midX < 285) {
			Drivetrain.frontRight.set(-0.55);
			Drivetrain.rearRight.set(-0.55);
			Drivetrain.frontLeft.set(0.55);
			Drivetrain.rearLeft.set(0.55);
			System.out.println("<285");
			//turns left
		} else if (Robot.midX > 315) {
			Drivetrain.frontRight.set(0.55);
			Drivetrain.rearRight.set(0.55);
			Drivetrain.frontLeft.set(0.55);
			Drivetrain.rearLeft.set(0.55);
			
			//turns right
			System.out.println(">315");
		}
		System.out.println("autoaligning, mid X = " + Robot.midX);*/
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return  ((Robot.midX > 285 || Robot.midX < 315) && !done);
		done = true;
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		Drivetrain.frontLeft.set(0);
		Drivetrain.rearLeft.set(0);
		Drivetrain.frontRight.set(0);
		Drivetrain.rearRight.set(0);
		
		done = true; 
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		done = true;
	}
}