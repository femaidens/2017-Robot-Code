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
	double speed;
	
	public Climb(double s) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		speed = s;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	// starts rolling climber CANTalon
	// sets the speed if the climber to half speed
	protected void execute() {
		System.out.println("Encoder Position: " + Robot.drivetrain.rearRight.getEncPosition());
		// if (Climber.climberEncoder.get() < 1080) { // 1080 is a place holder
		Robot.climber.spin(speed);
	}
	/*
	 * else { Robot.climber.stop(); return; }
	 */

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Climber.climberServo.getAngle() == 720) {
		return true;
		}
		else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		//angle not ticks
		System.out.println("Reached 720 degree angle");
		Robot.climber.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	// stops CANtalons from rotating
	protected void interrupted() {
		Robot.climber.stop();
	}
}
