package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {
	double degrees;
	public static boolean done;
    public TurnDegrees(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	degrees = d;
    	done = false;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.gyro.reset();
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Gyro: " + Drivetrain.gyro.getAngle());
    	System.out.println("Turning");
		if (degrees > 0) {
			while (Drivetrain.gyro.getAngle() < degrees && TurnDegrees.done == false) {

				Drivetrain.frontRight.set(ControlMode.PercentOutput,0.25);
				Drivetrain.rearRight.set(ControlMode.PercentOutput,0.25);
				Drivetrain.frontLeft.set(ControlMode.PercentOutput,0.25);
				Drivetrain.rearLeft.set(ControlMode.PercentOutput,0.25);
				
			}
			done = true;
		} else {
			while (Drivetrain.gyro.getAngle() > degrees && TurnDegrees.done == false) {
				Drivetrain.frontRight.set(ControlMode.PercentOutput,-0.25);
				Drivetrain.rearRight.set(ControlMode.PercentOutput,-0.25);
				Drivetrain.frontLeft.set(ControlMode.PercentOutput,-0.25);
				Drivetrain.rearLeft.set(ControlMode.PercentOutput,-0.25);
				
			}
			done = true;
		}
		done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Gyro: " + Drivetrain.gyro.getAngle());
    	Drivetrain.gyro.reset();
    	Drivetrain.frontRight.set(ControlMode.PercentOutput, 0.0);
		Drivetrain.rearRight.set(ControlMode.PercentOutput, 0.0);
		Drivetrain.frontLeft.set(ControlMode.PercentOutput, 0.0);
		Drivetrain.rearLeft.set(ControlMode.PercentOutput, 0.0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	done = true;
    }
}
