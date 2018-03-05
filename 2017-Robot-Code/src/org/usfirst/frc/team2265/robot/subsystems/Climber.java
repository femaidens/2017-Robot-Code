package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

 
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem

	// here. Call these from Commands.

	// Talon that controls climber motor

   	public static TalonSRX climberTalon = new TalonSRX(RobotMap.climberPort1);
   	public static TalonSRX climberTalon2 = new TalonSRX(RobotMap.climberPort2);
	
	public Climber() {
	}

	// Sets speed for climber motor

	public void spin(double speed) {
		climberTalon.set(ControlMode.PercentOutput, speed);
		climberTalon2.set(ControlMode.PercentOutput, -speed);
	}

	public void stop() {
		climberTalon.set(ControlMode.PercentOutput, 0.0);
		climberTalon2.set(ControlMode.PercentOutput, 0.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
