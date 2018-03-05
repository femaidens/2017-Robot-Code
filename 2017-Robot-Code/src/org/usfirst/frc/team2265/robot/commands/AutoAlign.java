package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAlign extends Command {
	 public static boolean done;
	
	public AutoAlign() {
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
			if (Robot.midX < 295) { //DON'T FORGET TO CHANGE BOTTOM!!!!!!!! 290 330
				Drivetrain.frontRight.set(ControlMode.PercentOutput, -0.125);
				Drivetrain.rearRight.set(ControlMode.PercentOutput,-0.125); 
				Drivetrain.frontLeft.set(ControlMode.PercentOutput,-0.125); 
				Drivetrain.rearLeft.set(ControlMode.PercentOutput,-0.125);
				System.out.println("<285"); //turns left 
			} else if (Robot.midX > 335) {
				Drivetrain.frontRight.set(ControlMode.PercentOutput, 0.125); 
				Drivetrain.rearRight.set(ControlMode.PercentOutput, 0.125); 
				Drivetrain.frontLeft.set(ControlMode.PercentOutput, 0.125);
				Drivetrain.rearLeft.set(ControlMode.PercentOutput, 0.125);
	  
				//turns right 
				System.out.println(">323"); } 
			else{
				done = true;
				return;
			}
			 System.out.println("autoaligning, mid X = " + Robot.midX); //this will keep running if the midX is not in within 305 and 335 }
		}
	

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return  ((Robot.midX > 285 || Robot.midX < 315) && !done);
		
		return (AutoAlign.done) &&(Robot.midX > 295 && Robot.midX < 335);
	}

	// Called once after isFinished returns true
	protected void end() {
		Drivetrain.frontLeft.set(ControlMode.PercentOutput, 0);
		Drivetrain.rearLeft.set(ControlMode.PercentOutput, 0);
		Drivetrain.frontRight.set(ControlMode.PercentOutput, 0);
		Drivetrain.rearRight.set(ControlMode.PercentOutput, 0);
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		done = true;
	}
}