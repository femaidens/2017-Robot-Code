package org.usfirst.frc.team2265.robot.subsystems;

import org.usfirst.frc.team2265.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static Encoder encoderLeft = new Encoder(RobotMap.encPort1, RobotMap.encPort2, true,
			Encoder.EncodingType.k1X);
	public static Encoder encoderRight = new Encoder(RobotMap.encPort3, RobotMap.encPort4, false,
			Encoder.EncodingType.k1X);
	
	public static TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeftPort);
	public static TalonSRX frontRight = new TalonSRX(RobotMap.frontRightPort);
	public static TalonSRX rearLeft = new TalonSRX(RobotMap.rearLeftPort);
	public static TalonSRX rearRight = new TalonSRX(RobotMap.rearRightPort);
	
	public int ticks;
	
	public Drivetrain() {
	}
	
	public void driveDistance(int distanceTravelled) {
		ticks = (236 / 12) * distanceTravelled;
		while (encoderLeft.get() < ticks && encoderRight.get() < ticks) {
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

