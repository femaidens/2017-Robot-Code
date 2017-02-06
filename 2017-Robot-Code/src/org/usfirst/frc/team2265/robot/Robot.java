
package org.usfirst.frc.team2265.robot;

import org.usfirst.frc.team2265.robot.commands.CenterAuto;
import org.usfirst.frc.team2265.robot.commands.LeftAuto;
import org.usfirst.frc.team2265.robot.commands.RightAuto;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2265.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2265.robot.subsystems.GearChute;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static Drivetrain drivetrain;
	public static GearChute gearChute;
	SendableChooser autoChooser;
	CommandGroup autonomousCommand;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drivetrain = new Drivetrain();
		gearChute = new GearChute();
		//Drivetrain.encoder.reset();// do we still need another encoder along with the one on the left and right side?
		oi.bindButtons();
		// instantiate the command used for the autonomous period
		//autonomousCommand = new ExampleCommand();
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Center Mode", new CenterAuto());
		autoChooser.addObject("Left Mode", new LeftAuto());
		autoChooser.addObject("Right Mode", new RightAuto());
		SmartDashboard.putData("Autonomous Mode Selector", autoChooser);
		//autonomousCommand = (CommandGroup) new EncoderAuto();
		//System.out.println("set command to timer");
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (CommandGroup) autoChooser.getSelected();
		autonomousCommand.start();
		System.out.println("Init auton");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder Velocity", Drivetrain.encoderLeft.getRate());
		SmartDashboard.putNumber("Right Encoder Velocity", Drivetrain.encoderRight.getRate());
		SmartDashboard.putNumber("Left Encoder Position", Drivetrain.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right Encoder Position", Drivetrain.encoderRight.getDistance());
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder Velocity", Drivetrain.encoderLeft.getRate());
		SmartDashboard.putNumber("Right Encoder Velocity", Drivetrain.encoderRight.getRate());
		SmartDashboard.putNumber("Left Encoder Position", Drivetrain.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right Encoder Position", Drivetrain.encoderRight.getDistance());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}