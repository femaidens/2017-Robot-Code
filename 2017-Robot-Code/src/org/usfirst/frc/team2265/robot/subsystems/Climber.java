package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */

public class Climber extends Subsystem {

	// Put methods for controlling this subsystem

	// here. Call these from Commands.

	// Talon that controls climber motor

    public static CANTalon climberTalon = new CANTalon(RobotMap.frontLeftPort);
	public static Encoder climberEncoder = new Encoder(RobotMap.climberEncoderPort1, RobotMap.climberEncoderPort2);

	//public static int periodUs = climberTalon.getPulseWidthRiseToRiseUs();
	// makes a climber class

	public Climber() {

		//climberTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		//climberTalon.configEncoderCodesPerRev(360);

	}

	// Sets speed for climber motor

	public void spin(double speed) {

		climberTalon.set(speed);
		// prints the encoder values on the smartDashboard

		SmartDashboard.putNumber("Encoder Velocity: ", climberEncoder.getRate());

		SmartDashboard.putNumber("Encoder Position: ", climberEncoder.get());

		System.out.println("Encoder Velocity: " + climberEncoder.getRate());

		System.out.println("Encoder Position: " + climberEncoder.get());

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
