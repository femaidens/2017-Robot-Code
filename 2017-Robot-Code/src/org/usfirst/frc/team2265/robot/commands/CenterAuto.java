package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class CenterAuto extends CommandGroup {
	public static Ultrasonic ultrasonicLeft = new Ultrasonic(RobotMap.ultraPort1,RobotMap.ultraPort2);
	
	public CenterAuto() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		addSequential(new EncGyroDriveStraight(9, 1, 1));
		addSequential(new CameraAuto());// aligns the center of the camera screen with the peg
    	addSequential(new UltrasonicDrive());//aligns the robot so that it is parallel to the wall
    	addSequential(new EncGyroDriveStraight((ultrasonicLeft.getRangeInches())/12,0.5,0.5));//drives straight toward the peg
		addParallel(new Drop(false));//drop gear
		addSequential(new RotateGearFlaps(false));
		addSequential(new EncGyroDriveStraight(1, -1, -1));
		addParallel(new Drop(true));
		addSequential(new RotateGearFlaps(true));

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}