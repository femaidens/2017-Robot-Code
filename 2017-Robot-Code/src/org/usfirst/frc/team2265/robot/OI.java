package org.usfirst.frc.team2265.robot;

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

	//public static Button swap = new JoystickButton(driveJoystick, 3);

	public static Button climberButton = new JoystickButton(driveJoystick, 4);
	public static Button driveStraightForwardButton = new JoystickButton(driveJoystick, 5);
	public static Button driveStraightBackButton = new JoystickButton(driveJoystick, 6);
	public static Button compressorButton = new JoystickButton(driveJoystick, 2); //is this being used?
	// creates and calls the bindButtons method (connects it to the 2 button)

	public void bindButtons() {
		//swap.whenPressed(new GearShift());
	}

}
