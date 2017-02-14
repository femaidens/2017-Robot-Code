package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;


/**
 *
 */
public class DriveStraightEncoder extends Command {
	
	public double distanceLeft;
	public double distanceRight;
	public double speedLeft;
	public double speedRight;
	
	
	
    public DriveStraightEncoder() {
    	
    }
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.encoderLeft.reset();
    	Drivetrain.encoderRight.reset();  	
    }
	    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distanceLeft = Drivetrain.encoderLeft.getDistance();
    	distanceRight = Drivetrain.encoderRight.getDistance();
    	
    	while (Drivetrain.encoderLeft.getDistance() > Drivetrain.encoderRight.getDistance()) {
    		System.out.println("Drive Straight: Toggle On");
    		Drivetrain.frontLeft.set(speedLeft - 0.05);
    		Drivetrain.rearLeft.set(speedLeft - 0.05);
    	}
    	while (Drivetrain.encoderLeft.getDistance() < Drivetrain.encoderRight.getDistance()) {
    		System.out.println("Drive Straight: Toggle On");
    		Drivetrain.frontRight.set(speedRight - 0.05);
    		Drivetrain.rearRight.set(speedRight - 0.05);
    	}
    	while (Drivetrain.encoderLeft.getDistance() == Drivetrain.encoderRight.getDistance()) {
    		Drivetrain.frontLeft.set(speedLeft);
    		Drivetrain.frontRight.set(speedRight);
    		Drivetrain.rearLeft.set(speedLeft);
    		Drivetrain.rearRight.set(speedRight);
    	}
    
	System.out.println("Left Encoder Position" + distanceLeft);
	System.out.println("Right Encoder Position" + distanceRight);
	SmartDashboard.putNumber("Left Encoder Position: ", distanceLeft);
	SmartDashboard.putNumber("Right Encoder Position: ", distanceRight);
	
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
