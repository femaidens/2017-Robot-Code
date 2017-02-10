package org.usfirst.frc.team2265.robot.subsystems;

//creates imports
import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * 
 */
// makes gear chute subsystem//
public class GearChute extends Subsystem {

	// intializes doubleSolenoid for the gearChute
	public static DoubleSolenoid gearPistons = new DoubleSolenoid(RobotMap.gearPort1, RobotMap.gearPort2);
	public static DoubleSolenoid basePistons = new DoubleSolenoid(RobotMap.basePort1, RobotMap.basePort2);

	// Extends the piston
	public static void extend() {
		gearPistons.set(DoubleSolenoid.Value.kForward);
		basePistons.set(DoubleSolenoid.Value.kForward);
	}

	// Retracts the piston
	public static void retract() {
		gearPistons.set(DoubleSolenoid.Value.kReverse);
		basePistons.set(DoubleSolenoid.Value.kReverse);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}