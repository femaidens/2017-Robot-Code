package org.usfirst.frc.team2265.robot;

import org.usfirst.frc.team2265.robot.commands.ExampleCommand;
import org.usfirst.frc.team2265.robot.commands.GearShift;
import org.usfirst.frc.team2265.robot.commands.Climb;

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

	// creates and calls the bindButtons method (connects it to the 2 button)

	public void bindButtons() {

		swap.whenPressed(new GearShift());

		climberButton.toggleWhenPressed(new Climb());// command needed);

	}

}
