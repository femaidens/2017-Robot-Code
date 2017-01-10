package org.usfirst.frc.team2265.robot.subsystems;

import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
// makes gear chute subsystem
//
public class GearChute extends Subsystem {
	public static DoubleSolenoid gearPiston = new DoubleSolenoid(RobotMap.gearport1, RobotMap.gearport2);
	public static DoubleSolenoid gearPiston2 = new DoubleSolenoid (RobotMap.gearport3, RobotMap.gearport4);
		public void extend() {
		gearPiston.set(DoubleSolenoid.Value.kForward);
		gearPiston2.set(DoubleSolenoid.Value.kForward);
	}
    
public void retract() {
		gearPiston.set(DoubleSolenoid.Value.kReverse);
		gearPiston2.set(DoubleSolenoid.Value.kReverse);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

public void initDefaultCommand() {
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


}


