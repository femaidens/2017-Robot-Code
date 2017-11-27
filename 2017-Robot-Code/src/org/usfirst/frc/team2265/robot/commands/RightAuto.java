package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAuto extends CommandGroup {
	public static double d;
    public RightAuto() {
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
    	

    	addSequential(new DriveDistance(60.0, 0.4)); //99 originally
    	addSequential(new TurnDegrees(-40)); //supposed to be -30
    	
       addSequential(new DriveDistance(48.0, 0.4));
    	addSequential(new AutoAlign());
    	System.out.println("Aligning");
    	/*double distance = Robot.getDistanceFromPeg();
    	System.out.println("Distance:" + Robot.getDistanceFromPeg());
    	addSequential(new DriveToPeg(0.375));
    	System.out.println("Driving Forward");*/
    	addSequential(new DriveDistance(29.0, 0.675));
    	/*addSequential(new ShiftChute(false));
    	System.out.println("Dropping Gear");
    	addSequential(new DriveDistanceBack(-12.0, -0.4));
    	System.out.println("Driving Back");*/
    	//addSequential(new ShiftChute(true));
    	//addSequential(new TurnDegrees(60));
    	//addSequential(new DriveDistance(60, 0.5));*/
    	
    	
    	//IGNORE?
    	/*//addSequential(new DriveDistance(50.0, 0.675));
    	addSequential(new DriveDistance(42.0, 0.675));
    	//System.out.println("Driving Forward");
    	addSequential(new TurnDegrees(-30));
    	//System.out.println("Turning");
    	addSequential(new DriveDistance(8.0, 0.675));
    	/*addSequential(new AutoAlign());
    	System.out.println("Aligning");
    	//double distance = Robot.getDistanceFromPeg();
    	System.out.println("Distance:" + Robot.getDistanceFromPeg());
    	addSequential(new DriveToPeg(0.375));
    	System.out.println("Driving Forward");
    	//addSequential(new ShiftChute(false));
    	System.out.println("Dropping Gear");
    	//addSequential(new DriveDistanceBack(-24, -0.4));
    	System.out.println("Driving Back");
    	//addSequential(new ShiftChute(true));
    	//addSequential(new TurnDegrees(60));
    	//addSequential(new DriveDistance(60, 0.5));*/
    }
}
