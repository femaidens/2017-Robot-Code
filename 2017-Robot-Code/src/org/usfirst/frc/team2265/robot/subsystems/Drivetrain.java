
package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */

public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// Initialize CANTalons
	public static CANTalon frontLeft, rearLeft, frontRight,rearRight;
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);
	// Initialize solenoids
	public static DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.transIn, RobotMap.transOut);
	public static RobotDrive tankDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	// TODO set updates for speeds
	public static double speedRight;
	public static double speedLeft;

	
	
	public Drivetrain() {
		frontLeft = new CANTalon(RobotMap.frontLeftPort);
		rearLeft = new CANTalon(RobotMap.rearLeftPort);
		frontRight = new CANTalon(RobotMap.frontRightPort);
		rearRight = new CANTalon(RobotMap.rearRightPort);
		frontLeft.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		frontLeft.configEncoderCodesPerRev(360);
		frontRight.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		frontRight.configEncoderCodesPerRev(360);
	}

	// Teleop
	public void drive() {
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		System.out.println("leftVal: " + leftVal + "rightVal: " + rightVal);
		tankDrive.tankDrive(-leftVal * 0.6, -rightVal * 0.6);

	}

	// auton
	public void drive(double l, double r) {
		frontRight.set(-r);
		rearRight.set(-r);
		frontLeft.set(l);
		rearLeft.set(l);
		speedLeft = l;
		speedRight = r;
	}
	//straight
	public void driveStraight(){
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		double driveVal = (leftVal + rightVal)/2;
		tankDrive.tankDrive(driveVal, driveVal);
		
	}
	public void shiftToSpeed() {
		gearShifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftToPower() {
		gearShifter.set(DoubleSolenoid.Value.kForward);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveTeleop());

	}
}
