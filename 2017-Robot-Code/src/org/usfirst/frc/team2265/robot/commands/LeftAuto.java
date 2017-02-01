package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAuto extends CommandGroup {
    
    public  LeftAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
	addSequential(new EncGyroDriveStraight(9,1,1));
	addSequential(new TurnRight(30));
	addSequential(new EncGyroDriveStraight(3,1,1));
	addParallel(new Drop(false));
	addSequential(new RotateGearFlaps(false));
	addSequential(new EncGyroDriveStraight(1,-1,-1));
	addParallel(new Drop(true));
	addSequential(new RotateGearFlaps(true));

    
// To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }}
