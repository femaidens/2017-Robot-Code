package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
//makes command 
public class DriveDistanceBack extends Command {
	//declaring variables
	double distance, left, right, distanceLeft, distanceRight;
	//instantiates variables
	double ticksPerRev = 256;
	double r = 2;
	double circ = 2* r * Math.PI;
	//uble left, right,distance,ticksPerRev,circ,ticksPassed;ticksPerRev=1024;circ=4*Math.PI;
	private double angle;
	// private double leftVel = Robot.drivetrain.frontRight.get();
	// private double rightVel = Robot.drivetrain.frontLeft.get();
	private double leftVel;
	private double rightVel;
	
	public DriveDistanceBack(double d, double v) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//instantiates more variables
		distance = d;
		
		leftVel = v;
		rightVel = v;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//resets encoder positions when initialized
		angle = Drivetrain.gyro.getAngle();

		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//gets left and right distances
		/*if (Drivetrain.gyro.getAngle() < angle) {
			Drivetrain.frontRight.set(-rightVel + 0.0575);
			Drivetrain.rearRight.set(-rightVel + 0.0575);
			Drivetrain.frontLeft.set(leftVel);
			Drivetrain.rearLeft.set(leftVel);
		} else if (Drivetrain.gyro.getAngle() > angle) {
			Drivetrain.frontLeft.set(leftVel - 0.0575);
			Drivetrain.rearLeft.set(leftVel - 0.0575);
			Drivetrain.rearRight.set(-rightVel);
			Drivetrain.frontRight.set(-rightVel);
		}*/
		
		//works for second robot?
				if (Drivetrain.gyro.getAngle() < angle) {
					Robot.drivetrain.frontRight.set(rightVel + 0.1);
					Robot.drivetrain.rearRight.set(rightVel + 0.1);
					Robot.drivetrain.frontLeft.set(-leftVel);
					Robot.drivetrain.rearLeft.set(-leftVel);
					System.out.println("Left:"  + Drivetrain.gyro.getAngle());
				} else if (Drivetrain.gyro.getAngle() > angle) {
					Robot.drivetrain.frontLeft.set(-leftVel - 0.1);
					Robot.drivetrain.rearLeft.set(-leftVel- 0.1);
					Robot.drivetrain.rearRight.set(rightVel);
					Robot.drivetrain.frontRight.set(rightVel);
					System.out.println("Right: "+Drivetrain.gyro.getAngle());
				}
	

		distanceLeft = Drivetrain.encoderLeft.get();
		distanceRight = Drivetrain.encoderRight.get();
		
		//prints positions to console and smart dashboard
	//	System.out.println("Left Encoder Distance" + distanceLeft * 12/236);
	//	System.out.println("Right Encoder Distance" + distanceRight*12/236);
		SmartDashboard.putNumber("Left Encoder Position: ", distanceLeft);
		SmartDashboard.putNumber("Right Encoder Position: ", distanceRight);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//if distance on either right or left is greater than what we want, robot stops
		return ((distanceLeft * 12/236) < (distance ) || ((distanceRight* 12/236) < distance ) || (DriveDistance.timer.get() < 10));
	}

	// Called once after isFinished returns true
	protected void end() {
		//stops motors and resets encoders
		Robot.drivetrain.drive(0, 0);
		Drivetrain.encoderLeft.reset(); 
		Drivetrain.encoderRight.reset(); //remove if we want to see how far the encoder has moved AFTER stopping
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}