package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

//drive straight 77 inches
/**
 *
 */
//alliance wall to peg distance is 6 ft 6 1/2 inches
public class CenterAuto extends CommandGroup {
	public static double d;
    public CenterAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

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
    	
    	//addSequential(new DriveDistance(2.0, 1.0));
    	addSequential(new DriveDistance(81.0, 0.25));
    	
      	//addSequential(new AutoAlign());
     
    	//addSequential(new DriveToPeg(0.4));
    	
    	addSequential(new ShiftChute(false));
    	
    	addSequential(new DriveDistanceBack(-12.0, -0.25));
    	
    }
}
