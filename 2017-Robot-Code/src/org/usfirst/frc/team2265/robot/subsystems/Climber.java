package org.usfirst.frc.team2265.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2265.robot.RobotMap;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static CANTalon climberTalon = new CANTalon(RobotMap.climberPort);
	public static int periodUs = climberTalon.getPulseWidthRiseToRiseUs();
	
	public Climber(){
		climberTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder); 
		climberTalon.configEncoderCodesPerRev(360);
		//place holder
	}
	
	public void spin(double speed){
		climberTalon.set(speed);
		SmartDashboard.putNumber("Encoder Velocity: ", climberTalon.getEncVelocity());
		SmartDashboard.putNumber("Encoder Position: ", climberTalon.getEncoderPos());
		System.out.println("Encoder Position: " + climberTalon.getEncVelocity());
		System.out.println("Encoder Position: " + climberTalon.getEncPos());
	}
	
	//manually stop
	public void stop(){
		climberTalon.set(0);
	}
	
	//autostop
	//public void autoStop(){
	//}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

