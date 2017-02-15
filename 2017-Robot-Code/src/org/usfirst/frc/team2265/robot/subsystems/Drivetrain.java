package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2265.robot.commands.CameraAuto;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */

public class Drivetrain extends Subsystem {
	public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	// Put methods for controlling this subsystem
	// here. Call these from Commands
	// Initialize CANTalons
	public static CANTalon frontLeft = new CANTalon(RobotMap.frontLeftPort);
	public static CANTalon rearLeft = new CANTalon(RobotMap.rearLeftPort);
	public static CANTalon frontRight = new CANTalon(RobotMap.frontRightPort);
	public static CANTalon rearRight = new CANTalon(RobotMap.rearRightPort);
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);
	// Initialize solenoids
	public static RobotDrive tankDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

	// Initializing encoder
	//public static Encoder encoderLeft = new Encoder(RobotMap.encPort1, RobotMap.encPort2);
	//public static Encoder encoderRight = new Encoder(RobotMap.encPort3, RobotMap.encPort4);
	public static Encoder encoderLeft = new Encoder(RobotMap.encPort1, RobotMap.encPort2, false, Encoder.EncodingType.k1X);
	public static Encoder encoderRight = new Encoder(RobotMap.encPort3, RobotMap.encPort4, false, Encoder.EncodingType.k1X);
	
	public static double constant = 8.6;

	public Drivetrain() {
		encoderLeft.setMaxPeriod(200);
		encoderRight.setMaxPeriod(200);
	}

	// Teleop
	public void drive() {
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		System.out.println("leftVal: " + encoderLeft.get() + " rightVal: " + encoderRight.get());
		tankDrive.tankDrive(-leftVal*0.8, -rightVal*0.8);

	}

	// auton
	public void drive(double l, double r) {
		frontRight.set(-r);
		rearRight.set(-r);
		frontLeft.set(l);
		rearLeft.set(l);
	}

	// straight
	/*public void driveStraight() {
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		double driveVal = (leftVal + rightVal) / 2;
		tankDrive.tankDrive(driveVal, driveVal);

	}*/
	public void turnDegreesRight(double degrees){
		/*double originalEncoderVal = encoderRight.get();
		while(Math.abs(encoderRight.get() - originalEncoderVal) < degrees * constant){
			frontRight.set(0);
			rearRight.set(0);
			frontLeft.set(0.25);
			rearLeft.set(0.25);
			
		}*/
		double angle = gyro.getAngle();
		while (Math.abs(gyro.getAngle() - angle) < degrees) {
			frontRight.set(0);
			rearRight.set(0);
			frontLeft.set(0.25);
			rearLeft.set(0.25);
		}
	}
	
	public void turnDegreesLeft(double degrees){
		/*double originalEncoderVal = encoderLeft.get();
		while(Math.abs(encoderLeft.get() - originalEncoderVal) < degrees * constant){
			frontRight.set(0.25);
			rearRight.set(0.25);
			frontLeft.set(0);
			rearLeft.set(0);
		}*/
		double angle = gyro.getAngle();
		while (Math.abs(gyro.getAngle() - angle) < degrees) {
			frontRight.set(0.25);
			rearRight.set(0.25);
			frontLeft.set(0);
			rearLeft.set(0);
		}
	}
	
	/*public void autoAlign() {
		while ((!CameraAuto.done) &&(Robot.midX < 285 || Robot.midX > 315)) {
			if (Robot.midX < 285) {
				frontRight.set(-0.1);
				rearRight.set(-0.1);
				frontLeft.set(-0.1);
				rearLeft.set(-0.1);
				System.out.println("<285");
				//turns left
			} else if (Robot.midX > 315) {
				frontRight.set(0.1);
				rearRight.set(0.1);
				frontLeft.set(0.1);
				rearLeft.set(0.1);
				
				//turns right
				System.out.println(">315");
			}
			else {
				break;
			}
			System.out.println("autoaligning, mid X = " + Robot.midX);
			//this will keep running if the midX is not in within 305 and 335
		}

		frontRight.set(0);
		rearRight.set(0);
		frontLeft.set(0);
		rearLeft.set(0);
		return;
	}
*/
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveTeleop());

	}
}
