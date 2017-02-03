package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.AnalogGyro;
import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.usfirst.frc.team2265.robot.subsystems.HelloCV;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

//makes the command and 
public class EncGyroDriveStraight extends Command {
	//declaring variables
	double distance, left, right, distanceLeft, distanceRight;
	AnalogGyro gyro;
	double angle;
	
	//instantiating variables
	static ArrayList<Rect> rectList = HelloCV.rectList;
	static Mat image = HelloCV.image;
	static Mat blurredImage = HelloCV.blurredImage;
	double leftVel = Drivetrain.frontRight.get();
	double rightVel = Drivetrain.frontLeft.get();

	//can be used to hardcode the distance
	public EncGyroDriveStraight(double d, double l, double r) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		//instantiating variables
		distance = d;
		right = r;
		left = l;
		gyro = new AnalogGyro(RobotMap.gyroPort);
		angle = gyro.getAngle();
	}
	
	//gets distance from helloCV (camera)
	public EncGyroDriveStraight(double l, double r){
		HelloCV.removeNoise(image);
		HelloCV.contouring(blurredImage);
		distance = HelloCV.getDistance(rectList);
		right = r;
		left = l;
		gyro = new AnalogGyro(RobotMap.gyroPort);
		angle = gyro.getAngle();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//resets encoder and gyro position
		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();
		gyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (angle > 90 && angle < 180) {// turning left
			Drivetrain.frontRight.set(rightVel - 0.1);
			Drivetrain.rearRight.set(rightVel - 0.1);
		} else if (angle < 90 && angle > 0) { //turning right
			Drivetrain.frontLeft.set(leftVel - 0.1);
			Drivetrain.rearLeft.set(leftVel - 0.1);
		}
		
		//encoders get distance
		distanceLeft = Drivetrain.encoderLeft.getDistance();
		distanceRight = Drivetrain.encoderRight.getDistance();
		
		//prints encoder position to console and smart dashboard
		System.out.println("Left Encoder Position" + distanceLeft);
		System.out.println("Right Encoder Position" + distanceRight);
		SmartDashboard.putNumber("Left Encoder Position: ", distanceLeft);
		SmartDashboard.putNumber("Right Encoder Position: ", distanceRight);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//if distance is greater than desired distance, then robot stops
		return Math.abs(distanceLeft) > distance || Math.abs(distanceRight) > distance;

	}

	// Called once after isFinished returns true
	protected void end() {
		//stops motors and resets encoders
		Robot.drivetrain.drive(0, 0);
		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();// remove if we want to see how far the
										// encoder has moved AFTER stopping

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
