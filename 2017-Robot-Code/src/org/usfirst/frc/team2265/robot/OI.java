package org.usfirst.frc.team2265.robot;

import org.usfirst.frc.team2265.robot.commands.Climb;
import org.usfirst.frc.team2265.robot.commands.GearShift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {

	// Identifies the ports that the Joysticks are connected to

	// creates two new Joysticks (driveJoystick and atkJoy)
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);

	public static Joystick atkJoy = new Joystick(RobotMap.atkJoyPort);

	// makes a new button and sets button number

	public static Button swap = new JoystickButton(driveJoystick, 3);

	public static Button climberButton = new JoystickButton(driveJoystick, 4);
	
	public static Button releaseButton = new JoystickButton(driveJoystick,1);

	// creates and calls the bindButtons method (connects it to the 2 button)

	public void bindButtons() {

		swap.whenPressed(new GearShift());

		climberButton.toggleWhenPressed(new Climb(1.0));// command needed);
		//climberButton.whileHeld(new Climb(0.75));
		releaseButton.toggleWhenPressed(new Climb(-1.0));
	}

}
