package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
 
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem

	// here. Call these from Commands.

	// Talon that controls climber motor

   	public static CANTalon climberTalon = new CANTalon(RobotMap.climberPort1);
   	public static CANTalon climberTalon2 = new CANTalon(RobotMap.climberPort2);
	
	public Climber() {
	}

	// Sets speed for climber motor

	public void spin(double speed) {
		climberTalon.set(-speed);
		climberTalon2.set(-speed);
	}

	public void stop() {
		climberTalon.set(0.0);
		climberTalon2.set(0.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
