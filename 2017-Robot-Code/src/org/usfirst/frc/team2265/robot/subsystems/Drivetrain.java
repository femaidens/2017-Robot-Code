package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
//import edu.wpi.first.wpilibj.CANTalon;
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
	public static CANTalon frontLeft = new CANTalon(RobotMap.frontLeftPort);
	public static CANTalon rearLeft = new CANTalon(RobotMap.rearLeftPort);
	public static CANTalon frontRight = new CANTalon(RobotMap.frontRightPort);
	public static CANTalon rearRight = new CANTalon(RobotMap.rearRightPort);
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);
	// Initialize solenoids
	public static DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.transIn, RobotMap.transOut);
	public static RobotDrive tankDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

	public Drivetrain() {
	}

	// Teleop
	public void drive() {
		double leftVal = OI.driveJoystick.getRawAxis(1);
		double rightVal = OI.driveJoystick.getRawAxis(5);
		tankDrive.tankDrive(leftVal, rightVal);
	}

	// auton
	public void drive(double l, double r) {
		frontRight.set(-r);
		rearRight.set(-r);
		frontLeft.set(l);
		rearLeft.set(l);
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