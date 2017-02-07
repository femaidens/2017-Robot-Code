package org.usfirst.frc.team2265.robot.subsystems;

import org.usfirst.frc.team2265.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Climber extends Subsystem {

	// Put methods for controlling this subsystem

	// here. Call these from Commands.

	// Talon that controls climber motor

   // public static CANTalon climberTalon = new CANTalon(RobotMap.climberPort);
	public static CANTalon climber1 = new CANTalon(RobotMap.climberPort1);
	public static CANTalon climber2 = new CANTalon(RobotMap.climberPort2);
	public static Encoder climberEncoder = new Encoder(RobotMap.climberEncoderPort1, RobotMap.climberEncoderPort2);

	//public static int periodUs = climberTalon.getPulseWidthRiseToRiseUs();
	// makes a climber class

	public Climber() {

		//climberTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		//climberTalon.configEncoderCodesPerRev(360);

	}

	// Sets speed for climber motor

	public void spin(double speed) {

		climber1.set(speed);
		climber2.set(speed);
		// prints the encoder values on the smartDashboard
		//SmartDashboard.putNumber("Encoder Velocity: ", climberEncoder.getRate());

		//SmartDashboard.putNumber("Encoder Position: ", climberEncoder.get());

		//System.out.println("Encoder Velocity: " + climberEncoder.getRate());

		//System.out.println("Encoder Position: " + climberEncoder.get());

	}

	// manually stop
	public void stop() {
		climber1.set(0.0);
		climber2.set(0.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
