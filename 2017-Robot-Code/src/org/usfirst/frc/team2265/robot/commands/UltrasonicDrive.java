package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Ultrasonic;


/**
 *
 */
public class UltrasonicDrive extends Command {
	public static Ultrasonic ultrasonicLeft = new Ultrasonic(RobotMap.ultraPort1,RobotMap.ultraPort2);
	public static Ultrasonic ultrasonicRight = new Ultrasonic(RobotMap.ultraPort3, RobotMap.ultraPort4);
	double leftRange, rightRange;
	boolean open;

    public UltrasonicDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void connectArduino() {
    	if (open)
    		Robot.toSend[0] = 80;
    	else
    		Robot.toSend[0] = 84;
    	Robot.i2c.transaction(Robot.toSend,1,null,0);
    	Timer.delay(0.0005);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftRange = ultrasonicLeft.getRangeInches();
    	rightRange = ultrasonicRight.getRangeInches();
    	if(leftRange < rightRange) {
    		Robot.drivetrain.turnDegreesLeft(Math.asin(rightRange/23.5));
    		//the parameter for turnDegreesLeft was determined using trig
    		//23.5 = width of robot 
    	}
    	else if(rightRange < leftRange){
    		Robot.drivetrain.turnDegreesRight(Math.asin(leftRange/23.5));
    		//the parameter for turnDegreesRight was determined using trig
    		//23.5 = width of robot
    	}
    	open = true;
    	connectArduino();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	open = false;
    	connectArduino();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}