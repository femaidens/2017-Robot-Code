package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDCommand;
//import org.usfirst.frc.team2265.robot.RobotMap;
//import com.ctre.CANTalon;
//import edu.wpi.first.wpilibj.AnalogGyro;
//import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;


/**
 *
 */
public class VelocityPIDDrive extends PIDSubsystem {

    // Initialize your subsystem here
	public static double Kp = 0.05;
	public static double Ki = 0.01;
	public static double Kd = 0.0;
	public static double absValue = 0.01;

		
	/*
	public static CANTalon frontleft = new CANTalon(RobotMap.frontLeftPort);
	public static CANTalon rearLeft = new CANTalon(RobotMap.rearLeftPort);
	public static CANTalon frontRight = new CANTalon(RobotMap.frontRightPort);
	public static CANTalon rearRight = new CANTalon(RobotMap.rearRightPort);

	public static Encoder leftEncoder = new Encoder(RobotMap.Encoders.FrontLeftDriveA, RobotMap.Encoders.FrontLeftDriveB);
	public static Encoder rightEncoder = new Encoder(RobotMap.Encoders.FrontRightDriveA, RobotMap.Encoders.FrontRightDriveB);
	*/

	//public static PIDController pidLeft = new PIDController(Kp, Ki, Kd, gyro, Drivetrain.frontLeft );
    	//public static PIDController pidRight = new PIDController(Kp, Ki, Kd, gyro, Drivetrain.frontRight);
	//public static PIDController pid = getPIDController();
	public static PIDController pid = new PIDController(Kp, Ki, Kd, Drivetrain.encoderLeft, Drivetrain.frontLeft);

	
    public VelocityPIDDrive() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	//Kp, Ki, Kd are the PID constants
    	super(Kp, Ki, Kd);  
    	setAbsoluteTolerance(absValue); 	//absValue = limit in error made
   		pid.setContinuous(false);
    	//pidLeft.setContinuous(false);
    	//pidRight.setContinuous(false);

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        
    	return Drivetrain.encoderLeft.getRate() - Drivetrain.encoderRight.getRate(); 
    	//get the input from the gyro (input = current value for pid loop)

    	//return 0.0;
    }

    public void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	// this is where the computed output value from the PIDController is applied to the motors
    	//output = error value
    	 
    		Drivetrain.frontLeft.set(output);
    		Drivetrain.rearLeft.set(output);
    		Drivetrain.frontRight.set(output);
    		Drivetrain.rearLeft.set(output);

    }
}
