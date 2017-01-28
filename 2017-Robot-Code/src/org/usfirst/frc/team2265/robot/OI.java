package org.usfirst.frc.team2265.robot;

import org.usfirst.frc.team2265.robot.commands.Drop;
import org.usfirst.frc.team2265.robot.commands.ExampleCommand;
//import org.usfirst.frc.team2265.robot.commands.GearShift
import org.usfirst.frc.team2265.robot.commands.ShiftChute;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick driveJoystick = new Joystick(RobotMap.driveJoyPort);
	public static Joystick atkjoy = new Joystick(RobotMap.atkJoyPort);
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	//swap mode
	//public static Button swap = new JoystickButton(driveJoystick, 3);
	
	//button to drop the gear
	public static Button dropGear = new JoystickButton(driveJoystick, 3);
	
	//button to reset gearchute
	public static Button reset = new JoystickButton(driveJoystick, 2);
	
	public void bindButtons(){
		//swap.whenPressed(new GearShift());
		dropGear.whenPressed(new ShiftChute(false));
		reset.whenPressed(new ShiftChute(true));
	}

	
	
		
	}
	
