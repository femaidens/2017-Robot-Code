package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.commands.DriveTeleop;
import org.usfirst.frc.team2265.robot.OI;
import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.RobotMap;
import com.ctre.CANTalon;
//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;


/**
 *
 */
public class DriveStraightEncoder extends Command {
	double speedLeft, speedRight;
    public DriveStraightEncoder(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	speedLeft = speed;
    	speedRight= speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while (Drivetrain.frontLeft.getEncVelocity() > Drivetrain.frontRight.getEncVelocity()) {
    		System.out.println("Drive Straight: Toggle On");
    		Drivetrain.frontLeft.set(speedLeft - 0.05);
    		Drivetrain.rearLeft.set(speedLeft - 0.05);
    	}
    	while (Drivetrain.frontLeft.getEncVelocity() < Drivetrain.frontRight.getEncVelocity()) {
    		System.out.println("Drive Straight: Toggle On");
    		Drivetrain.frontRight.set(speedRight - 0.05);
    		Drivetrain.rearRight.set(speedRight - 0.05);
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
