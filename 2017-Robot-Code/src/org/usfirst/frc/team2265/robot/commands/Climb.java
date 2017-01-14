package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Climber;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class Climb extends Command {

	public Climb() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		
	}

	// Called repeatedly when this Command is scheduled to run
	// starts rolling climber CANTalon
	// sets the speed if the climber to half speed
	protected void execute() {
		System.out.println("Encoder Position: " + Robot.drivetrain.rearRight.getEncPosition());

		if (Robot.drivetrain.rearRight.getEncPosition() > -256) { // 1080 is a
			//getAnalogEncPosition														// place
																	// holder
			Robot.drivetrain.drive(0.25,0.25);
	
		}

		else {
			Robot.climber.stop();
			return;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Reached 1462 ticks");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	// stops CANtalons from rotating
	protected void interrupted() {
		Robot.climber.stop();
	}
}
