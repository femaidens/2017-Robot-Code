package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Ultrasonic;


/**
 *
 */

public class UltrasonicDrive extends Command {
	//makes new ultrasonics for left and right with ports
	public static Ultrasonic ultrasonicLeft = new Ultrasonic(RobotMap.ultraPort1,RobotMap.ultraPort2);
	public static Ultrasonic ultrasonicRight = new Ultrasonic(RobotMap.ultraPort3, RobotMap.ultraPort4);
	//declares leftRange and rightRange as variables
	double leftRange, rightRange;

    public UltrasonicDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//instantiates variables by using getrangeinches method for each ultrasonic
    	leftRange = ultrasonicLeft.getRangeInches();
    	rightRange = ultrasonicRight.getRangeInches();
    	
    	//if the left side of robot is closer to object than right side, then it turns more left to get straight
    	if(leftRange < rightRange) {
    		Robot.drivetrain.turnDegreesLeft(Math.asin(rightRange/23.5));
    	}
    //the right side of robot is closer to object than left side, then it turns more left to get straight
    	else if(rightRange < leftRange){
    		Robot.drivetrain.turnDegreesRight(Math.asin(leftRange/23.5));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
