package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
//command group used if robot is in center position so can just drive straight (rotates gear flaps and drops gear)
public class CenterAuto extends CommandGroup {

	public CenterAuto() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		addSequential(new EncGyroDriveStraight(9, 1, 1));
		addParallel(new Drop(false));
		addSequential(new RotateGearFlaps());
		addSequential(new EncGyroDriveStraight(1, -1, -1));
		addParallel(new Drop(true));
		addSequential(new RotateGearFlaps());

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
