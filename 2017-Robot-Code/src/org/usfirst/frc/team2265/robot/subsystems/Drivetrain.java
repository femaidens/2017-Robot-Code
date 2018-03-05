package org.usfirst.frc.team2265.robot.subsystems;

import org.usfirst.frc.team2265.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static Encoder encLeft = new Encoder(RobotMap.encPort1, RobotMap.encPort3);
	public static Encoder encRight = new Encoder(RobotMap.encPort2, RobotMap.encPort4);
	
	public static TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeftPort);
	public static TalonSRX frontRight = new TalonSRX(RobotMap.frontRightPort);
	public static TalonSRX rearLeft = new TalonSRX(RobotMap.rearLeftPort);
	public static TalonSRX rearRight = new TalonSRX(RobotMap.rearRightPort);
	
	public int ticks;
	double circumference = 4 * Math.PI;
	double distanceTravelled = (ticks / 360) * circumference;
	
	public Drivetrain() {
	}
	
	public void driveDistance() {
		while (encLeft.get() < ticks && encRight.get() < ticks) {
			frontLeft.set(ControlMode.PercentOutput, 0.75);
			frontRight.set(ControlMode.PercentOutput, 0.75);
			rearLeft.set(ControlMode.PercentOutput, 0.75);
			rearRight.set(ControlMode.PercentOutput, 0.75);
		}
	}
	
	public void stop() {
		frontLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
		rearLeft.set(ControlMode.PercentOutput, 0);
		rearRight.set(ControlMode.PercentOutput, 0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

