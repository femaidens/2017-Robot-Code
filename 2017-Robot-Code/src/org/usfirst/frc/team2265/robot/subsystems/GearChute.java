package org.usfirst.frc.team2265.robot.subsystems;

//creates imports
import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * 
 */
// makes gear chute subsystem//
public class GearChute extends Subsystem {

	// intializes doubleSolenoid and servos (type of motor) for the gearChute
	public static DoubleSolenoid gearPiston = new DoubleSolenoid(RobotMap.gearPort1, RobotMap.gearPort2);
	public static Servo gearServo1 = new Servo(RobotMap.gearServoPort1);
	public static Servo gearServo2 = new Servo(RobotMap.gearServoPort2);

	public static void turnFlaps(boolean reset) {
		// if reset is true then it does the while loop)
		if (reset) {
			//while the angle is less then 90 then set both motors (will turn in opposite direction)
			while (gearServo1.getAngle() < 90) {
				gearServo1.set(0.5);
				gearServo2.set(-0.5);
			}
			//if the angle is not less then 90 then set motors to 0
			gearServo1.set(0.0);
			gearServo2.set(0.0);
			//if reset is false then it runs this while loop
		} else {
			//while the angle is less then -90, then set both motors (will turn in opposite direction)
			while (gearServo1.getAngle() < -90) {
				gearServo1.set(-0.5);
				gearServo2.set(0.5);
			}
		}
		//if angle is not less then -90 then it will set both motors to 0
		gearServo1.set(0.0);
		gearServo2.set(0.0);
	}

	// Extends the piston
	public static void extend() {
		gearPiston.set(DoubleSolenoid.Value.kForward);
	}

	// Retracts the piston
	public static void retract() {
		gearPiston.set(DoubleSolenoid.Value.kReverse);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}