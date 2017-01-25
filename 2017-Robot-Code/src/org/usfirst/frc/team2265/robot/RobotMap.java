package org.usfirst.frc.team2265.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

//RobotMap.java
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// Joystick Ports
	public static int driveJoyPort = 2;
	public static int atkJoyPort = 1;

	// Talon Ports
	public static int rearLeftPort = 5;
	public static int rearRightPort = 4;
	public static int frontLeftPort = 6;
	public static int frontRightPort = 2;
	public static int climberPort = 3;


	// Solenoid Ports
	public static int transIn = 2;
	public static int transOut = 3;

	//encoder ports
	public static int climberEncoderPort1 = 3;
	public static int climberEncoderPort2 = 4;	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}