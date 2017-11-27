package org.usfirst.frc.team2265.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2265.robot.Robot;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2265.robot.subsystems.VelocityPIDDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;

/**
 *
 */
public class VelocityDriveStraight extends Command {

	//declaring variables
		double distance, left, right, distanceLeft, distanceRight;
		//instantiates variables
		double ticksPerRev = 256;
	double r = 2;
		double circ = 2* r * Math.PI;
		//uble left, right,distance,ticksPerRev,circ,ticksPassed;ticksPerRev=1024;circ=4*Math.PI;
		//private double angle;
		private double leftVel;
		private double rightVel;
	   private double error = 0;
	   private double period = 0.05;
	   private double lastError, derivative, errorSum, output;
		
	   //public VelocityPIDDrive vpidDrive = new VelocityPIDDrive();

    public VelocityDriveStraight(double d, double v){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	distance = d;
    	leftVel = -v;
    	//the left motors are like turned around so the velocity has to be negated 
    	rightVel = v;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	VelocityPIDDrive.pid.setSetpoint(0);
    	VelocityPIDDrive.pid.setContinuous(true);
    	VelocityPIDDrive.pid.enable();

    	Drivetrain.encoderLeft.reset();
    	Drivetrain.encoderRight.reset();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.frontLeft.set(leftVel);
    	Drivetrain.frontRight.set(rightVel);
    	Drivetrain.rearLeft.set(leftVel);
    Drivetrain.rearRight.set(rightVel);

    		
    /*
    VelocityPIDDrive.pid.calculate();
    vpidDrive.usePIDOutput(PIDDrive.m_pidOutput);
     */

    lastError = error;
    error = VelocityPIDDrive.pid.getError();
    derivative = (error - lastError)/ period;
    errorSum += error*period;
    output = error*VelocityPIDDrive.Kp + derivative*VelocityPIDDrive.Kd + errorSum*VelocityPIDDrive.Ki;
    Robot.vpidDrive.usePIDOutput(output);

    	/*
    if (getPIDController().getError() > 0){
    		Drivetrain.frontRight.set(rightVel + 0.1);
    		Drivetrain.rearRight.set(rightVel + 0.1);
    		Drivetrain.frontLeft.set(leftVel);
    		Drivetrain.rearLeft.set(leftVel);
    		}
    		else if (getPIDController().getError() < 0){
    			Drivetrain.`frontRight.set(leftVel - 0.1); 				
    Drivetrain.rearRight.set(leftVel - 0.1);
    			Drivetrain.frontLeft.set(rightVel);
    			Drivetrain.rearLeft.set(rightVel);
    }
    */

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Drivetrain.encoderLeft.get() >= (distance*236/12))  || (Drivetrain.encoderRight.get() >=  (distance*236/12));
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.frontLeft.set(0);
    	Drivetrain.frontRight.set(0);
    	Drivetrain.rearLeft.set(0);
    Drivetrain.rearRight.set(0);

    Drivetrain.encoderLeft.reset();
    Drivetrain.encoderRight.reset();

    VelocityPIDDrive.pid.reset();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
