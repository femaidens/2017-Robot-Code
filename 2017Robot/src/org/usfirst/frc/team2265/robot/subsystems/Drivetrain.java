package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */

public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// Initialize talons
	public static Talon frontLeft = new Talon(RobotMap.frontLeftPort);
	public static Talon rearLeft = new Talon(RobotMap.rearLeftPort);
	public static Talon frontRight = new Talon(RobotMap.frontRightPort);
	public static Talon rearRight = new Talon(RobotMap.rearRightPort);

	// Initialize solenoids
	public static DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.transIn, RobotMap.transOut);
	public static RobotDrive tankDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

	public Drivetrain() {
	}

	// Teleop
	public void drive() {
		double leftVal = Robot.driveJoystick.getRawAxis(1);
		double rightVal = Robot.driveJoystick.getRawAxis(5);
		tankDrive.tankDrive(leftVal, rightVal);
	}

	// auton
	public void drive(double l, double r) {
		frontRight.set(-r);
		rearRight.set(-r);
		frontLeft.set(l);
		rearLeft.set(l);
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
