
package org.usfirst.frc.team2265.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2265.robot.commands.CenterAuto;
import org.usfirst.frc.team2265.robot.commands.ExampleCommand;
import org.usfirst.frc.team2265.robot.commands.LeftAuto;
import org.usfirst.frc.team2265.robot.commands.RightAuto;
import org.usfirst.frc.team2265.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2265.robot.subsystems.Climber;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
	public static Climber climber;
	public static Compressor compressette; 
	public static I2C i2c;
	public static byte[] toSend;  
	public static SendableChooser<Object> autoChooser; 
	Command autonomousCommand;
	public static int midX;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		i2c = new I2C(I2C.Port.kOnboard, 84);
	    toSend = new byte[1];
		oi = new OI();
		drivetrain = new Drivetrain();
		climber = new Climber();
		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();
		compressette = new Compressor();
		oi.bindButtons();
		// instantiate the command used for the autonomous period
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Center Mode", new CenterAuto());
		autoChooser.addObject("Left Mode", new LeftAuto());
		autoChooser.addObject("Right Mode", new RightAuto());
		SmartDashboard.putData("Autonomous Mode Selector", autoChooser);
		
		autonomousCommand = new ExampleCommand();
		CameraServer.getInstance().startAutomaticCapture();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
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
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}