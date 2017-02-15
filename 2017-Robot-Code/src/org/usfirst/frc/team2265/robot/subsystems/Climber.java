package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;

/**
 *
 */

public class Climber extends Subsystem {

	// Put methods for controlling this subsystem

	// here. Call these from Commands.

	// Talon that controls climber motor

	//public static CANTalon climberTalon = new CANTalon(RobotMap.frontLeftPort);
	public static CANTalon climberTalon = new CANTalon(RobotMap.climberPort1);
	public static CANTalon climberTalon2 = new CANTalon(RobotMap.climberPort2);

	public static int periodUs = climberTalon.getPulseWidthRiseToRiseUs();
	// makes a climber class

	public Climber() {

		climberTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		climberTalon.configEncoderCodesPerRev(360);

	}

	// Sets speed for climber motor

	public void spin(double speed) {

		climberTalon.set(speed);
		// prints the encoder values on the smartDashboard

		SmartDashboard.putNumber("Encoder Velocity: ", climberTalon.getEncVelocity());

		SmartDashboard.putNumber("Encoder Position: ", climberTalon.getEncPosition());

		System.out.println("Encoder Position: " + climberTalon.getEncVelocity());

		System.out.println("Encoder Position: " + climberTalon.getEncPosition());

	}

	// manually stop
	public void stop() {
		climberTalon.set(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
