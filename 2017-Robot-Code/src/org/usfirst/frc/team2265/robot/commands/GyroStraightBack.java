package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.RobotMap;
//import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.GyroBase;
//import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.AnalogGyro;
//import com.ctre.CANTalon;

/**
 *
 */
public class GyroStraightBack extends Command {
	private AnalogGyro gyro;
	private double angle;
	// private double leftVel = Robot.drivetrain.frontRight.get();
	// private double rightVel = Robot.drivetrain.frontLeft.get();
	private double leftVel;
	private double rightVel;

	// true is forward
	public GyroStraightBack(double v) {
		gyro = new AnalogGyro(RobotMap.gyroPort);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		angle = gyro.getAngle();

		leftVel = v;
		rightVel = v;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		gyro.reset();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (gyro.getAngle() < angle) {
			Drivetrain.frontRight.set(ControlMode.PercentOutput,-rightVel - 0.055);
			Drivetrain.rearRight.set(ControlMode.PercentOutput,rightVel - 0.055);
			Drivetrain.frontLeft.set(ControlMode.PercentOutput,leftVel);
			Drivetrain.rearLeft.set(ControlMode.PercentOutput,leftVel);
		} else if (gyro.getAngle() > angle) {
			Drivetrain.frontLeft.set(ControlMode.PercentOutput,leftVel + 0.055);
			Drivetrain.rearLeft.set(ControlMode.PercentOutput,leftVel + 0.055);
			Drivetrain.rearRight.set(ControlMode.PercentOutput,-rightVel);
			Drivetrain.frontRight.set(ControlMode.PercentOutput,-rightVel);
		}
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
		// toggle runs nothing so it will stop :)
	}
}
