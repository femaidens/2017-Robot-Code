package org.usfirst.frc.team2265.robot.subsystems;

import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.RobotMap;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;

import com.ctre.CANTalon;

//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// Initialize CANTalons
	public static CANTalon frontLeft = new CANTalon(RobotMap.frontLeftPort);
	public static CANTalon rearLeft = new CANTalon(RobotMap.rearLeftPort);
	public static CANTalon frontRight = new CANTalon(RobotMap.frontRightPort);
	public static CANTalon rearRight = new CANTalon(RobotMap.rearRightPort);
	//initialize joysticks
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);
	// Initialize solenoids
	public static DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.transIn, RobotMap.transOut);
	public static RobotDrive tankDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	
	
	//Initializing encoder 
	public static Encoder encoderLeft = new Encoder(RobotMap.encPort1, RobotMap.encPort2);
	public static Encoder encoderRight = new Encoder (RobotMap.encPort3, RobotMap.encPort4);
	
	public static Ultrasonic ultrasonicLeft = new Ultrasonic(RobotMap.ultraPort1,RobotMap.ultraPort2);
	public static Ultrasonic ultrasonicRight = new Ultrasonic(RobotMap.ultraPort3, RobotMap.ultraPort4);
	
	//converts the number of degrees into ticks
	public static double constant = 8.6; //(2*23.5*pi/360)/(2*2*pi/256)
	
	public Drivetrain() {
	}

	// Teleop
	public void drive() {
		//gets the axis value
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		tankDrive.tankDrive(-leftVal, -rightVal);
	}
	
	// auton
	public static void drive(double l, double r) {
		//sets motors with variables as parameters (called in drive command)
		frontRight.set(-r);
		rearRight.set(-r);
		frontLeft.set(l);
		rearLeft.set(l); 
	}
	
	//helps driver drive straight
	public void driveStraight(){
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		double driveVal = (leftVal + rightVal)/2;
		//uses drive value in tankdrive method
		tankDrive.tankDrive(driveVal, driveVal);	
	}
	
	public void turnDegreesRight(double degrees){
		double originalEncoderVal = encoderRight.get();
		//if distance moved is less than the distance you want to move, then the motors will get set so that it turns right 
		while(Math.abs(encoderRight.get() - originalEncoderVal) < degrees * constant){
			//sets motor values
			drive(0.25, -0.25);
		}
	}
	
	//called in ultrasonic drive
	public void turnDegreesLeft(double degrees){
		double originalEncoderVal = encoderLeft.get();
		//if distance moved is less than the distance you want to move, then the motors will get set so that it turns left
		while(Math.abs(encoderLeft.get() - originalEncoderVal) < degrees * constant){
			drive(-0.25, 0.25);
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveTeleop());

	}
}
