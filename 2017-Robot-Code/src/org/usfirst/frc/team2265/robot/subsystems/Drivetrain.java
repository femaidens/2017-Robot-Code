package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2265.robot.commands.AutoAlign;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */

public class Drivetrain extends Subsystem {
	//public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	// Put methods for controlling this subsystem
	// here. Call these from Commands
	// Initialize TalonSRXs
	public static TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeftPort);
	public static TalonSRX rearLeft = new TalonSRX(RobotMap.rearLeftPort);
	public static TalonSRX frontRight = new TalonSRX(RobotMap.frontRightPort);
	public static TalonSRX rearRight = new TalonSRX(RobotMap.rearRightPort);
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);
	// Initialize solenoids
	//public static RobotDrive tankDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

	// Initializing encoder
	// public static Encoder encoderLeft = new Encoder(RobotMap.encPort1,
	// RobotMap.encPort2);
	// public static d encoderRight = new Encoder(RobotMap.encPort3,
	// RobotMap.encPort4);
	/*
	public static Encoder encoderLeft = new Encoder(RobotMap.encPort1, RobotMap.encPort2, true,
			Encoder.EncodingType.k1X);
	public static Encoder encoderRight = new Encoder(RobotMap.encPort3, RobotMap.encPort4, false,
			Encoder.EncodingType.k1X);

	public static double constant = 8.6;

	public Drivetrain() {
		encoderLeft.setMaxPeriod(2);
		encoderRight.setMaxPeriod(2);
	}

	// Teleop
	public void drive() {
		double leftVal = OI.driveJoystick.getRawAxis(5);
		double rightVal = OI.driveJoystick.getRawAxis(1);
		 //System.out.println("leftVal: " + encoderLeft.get() + " rightVal: " + encoderRight.get());
		System.out.println("Gyro: "+ gyro.getAngle());
		//tankDrive.tankDrive(leftVal * 0.85, rightVal * 0.85);
		tankDrive.tankDrive(leftVal, rightVal);
	}
	
	public void driveSlow() {
		double leftVal = OI.driveJoystick.getRawAxis(5);
		double rightVal = OI.driveJoystick.getRawAxis(1);
		 //System.out.println("leftVal: " + encoderLeft.get() + " rightVal: " + encoderRight.get());
		//System.out.println("Gyro: "+ gyro.getAngle());
		tankDrive.tankDrive(leftVal * 0.85, rightVal * 0.85);
		//tankDrive.tankDrive(leftVa''''''l, rightVal);
	}
	// auton
	public void drive(double l, double r) {
		frontRight.set(-r);
		rearRight.set(-r);
		frontLeft.set(l);
		rearLeft.set(l);
	}

	// straight
	/*
	 * public void driveStraight() { double leftVal =
	 * OI.driveJoystick.getRawAxis(1); double rightVal =
	 * OI.driveJoystick.getRawAxis(5); double driveVal = (leftVal + rightVal) /
	 * 2; tankDrive.tankDrive(driveVal, driveVal);
	 * 
	 * }
	 
	public void turnDegrees(double degrees) {
		/*
		 * double originalEncoderVal = encoderRight.get();
		 * while(Math.abs(encoderRight.get() - originalEncoderVal) < degrees *
		 * constant){ frontRight.set(0); rearRight.set(0); frontLeft.set(0.25);
		 * rearLeft.set(0.25);
		 * 
		 * }
	
		gyro.reset();

		if (degrees > 0) {
			while (gyro.getAngle() < degrees) {

				frontRight.set(-0.25);
				rearRight.set(-0.25);
				frontLeft.set(-0.25);
				rearLeft.set(-0.25);
			}
		} else {
			while (gyro.getAngle() > degrees) {
				frontRight.set(0.25);
				rearRight.set(0.25);
				frontLeft.set(0.25);
				rearLeft.set(0.25);
			}
		}

	}

	/*public void turnDegreesLeft(double degrees) {
		/*
		 * double originalEncoderVal = encoderLeft.get();
		 * while(Math.abs(encoderLeft.get() - originalEncoderVal) < degrees *
		 * constant){ frontRight.set(0.25); rearRight.set(0.25);
		 * frontLeft.set(0); rearLeft.set(0); }
		 
		double angle = gyro.getAngle();
		while (Math.abs(gyro.getAngle() - angle) < degrees) {
			frontRight.set(0.25);
			rearRight.set(0.25);
			frontLeft.set(0);
			rearLeft.set(0);
		}
	}*/
/*
	public void autoAlign() { 
		while ((!AutoAlign.done) &&(Robot.midX < 285 || Robot.midX > 315)) { 
			if (Robot.midX < 285) { 
				frontRight.set(-0.125);
				rearRight.set(-0.125); 
				frontLeft.set(-0.125); 
				rearLeft.set(-0.125);
				System.out.println("<285"); //turns left 
			} else if (Robot.midX > 315) {
				frontRight.set(0.125); 
				rearRight.set(0.125); 
				frontLeft.set(0.125);
				rearLeft.set(0.125);
	  
				//turns right 
				System.out.println(">315"); } 
			else { 
				break; 
			}
			 System.out.println("autoaligning, mid X = " + Robot.midX); //this will keep running if the midX is not in within 305 and 335 }
		}
	 
	  
	  frontRight.set(0); rearRight.set(0); frontLeft.set(0); rearLeft.set(0);
	  return; 
	  
	}
	*/
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveTeleop());

	}
	
}
