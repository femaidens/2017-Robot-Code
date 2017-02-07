
package org.usfirst.frc.team2265.robot;

import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2265.robot.commands.CenterAuto;
import org.usfirst.frc.team2265.robot.commands.LeftAuto;
import org.usfirst.frc.team2265.robot.commands.RightAuto;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2265.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2265.robot.subsystems.GearChute;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static Drivetrain drivetrain;
	public static GearChute gearChute;
	SendableChooser autoChooser;
	CommandGroup autonomousCommand;
	public static I2C i2c;
	public static byte[] toSend;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drivetrain = new Drivetrain();
		gearChute = new GearChute();
		//Drivetrain.encoder.reset();// do we still need another encoder along with the one on the left and right side?
		oi.bindButtons();
		i2c = new I2C(I2C.Port.kOnboard, 84);
	    toSend = new byte[1];
	    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(640, 480);
	    camera.setBrightness(0);
	    CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
	    CvSink cvSink = CameraServer.getInstance().getVideo();
	    
	    
	    new Thread(() -> {
	    			
	    			Mat source = new Mat();
	    			Mat image = new Mat();
	    			Mat image2 = new Mat();
	    
	   			while (!Thread.interrupted()) {
	    				cvSink.grabFrame(source);
	    				Imgproc.cvtColor(source, image, Imgproc.COLOR_BGR2HSV, 0);
	    				//outputStream.putFrame(image);
	    				Imgproc.blur(image, image2, new Size(3, 3));
	    				Scalar bigMinValues = new Scalar(58, 226, 80); // placeholder
	    																// values
	    				Scalar bigMaxValues = new Scalar(108, 255, 150); // placeholder
	    																	// values
	    				Core.inRange(image2, bigMinValues, bigMaxValues, image);
	    				
	    				
	    				ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	    				image.convertTo(image, CvType.CV_8UC1);
	    				Mat mat = new Mat();
	    				Imgproc.findContours(image, contours, mat, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
	    				Imgproc.drawContours(image, contours, -1, new Scalar(60, 255, 255), 2);
	    				
	    				//System.out.println(contours);
	    				Iterator<MatOfPoint> contourItr = contours.iterator();
	    				ArrayList<Double> area = new ArrayList<Double>();
	   				while (contourItr.hasNext()) {
	    					MatOfPoint p = contourItr.next();
	    					area.add(Imgproc.contourArea(p));
	   
	    				}
	    				System.out.println("Area: " + area.toString());
	   				ArrayList<Rect> rectList = new ArrayList<Rect>();
	    				// ArrayList<Double> centerList = new ArrayList<Double>();
	    
	    				for (int i = 0; i < contours.size(); i++) {
	    					MatOfPoint cPoint = contours.get(i);
	    					Rect rect = Imgproc.boundingRect(cPoint);
	    					rectList.add(rect);
	    				}
	    				System.out.println("Rectangles: " + rectList);
	    				if (rectList.size() > 0){
	    				Imgproc.rectangle(image, new Point(rectList.get(0).x, rectList.get(0).y),
	    						new Point(rectList.get(0).x + rectList.get(0).width,
	    								rectList.get(0).y - rectList.get(0).height),
	    						new Scalar(179, 255, 255), 1); 
	    				}
	    				outputStream.putFrame(image);
	    				/*// midX = (rectList.get(0).x + (rectList.get(1).x +
	   				// rectList.get(1).width))/2;
	    
	    				System.out.println(area.toString());
	    				for (int i = 0; i < rectList.size(); i++) {
	    					System.out.print(rectList.get(i).height);
	    				}*/
	    				image.release();
	    				image2.release();
	    				source.release();
	    			}
	    
	    		}).start();
	    
		// instantiate the command used for the autonomous period
		//autonomousCommand = new ExampleCommand();
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Center Mode", new CenterAuto());
		autoChooser.addObject("Left Mode", new LeftAuto());
		autoChooser.addObject("Right Mode", new RightAuto());
		SmartDashboard.putData("Autonomous Mode Selector", autoChooser);
		//autonomousCommand = (CommandGroup) new EncoderAuto();
		//System.out.println("set command to timer");
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (CommandGroup) autoChooser.getSelected();
		autonomousCommand.start();
		System.out.println("Init auton");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder Velocity", Drivetrain.encoderLeft.getRate());
		SmartDashboard.putNumber("Right Encoder Velocity", Drivetrain.encoderRight.getRate());
		SmartDashboard.putNumber("Left Encoder Position", Drivetrain.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right Encoder Position", Drivetrain.encoderRight.getDistance());
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder Velocity", Drivetrain.encoderLeft.getRate());
		SmartDashboard.putNumber("Right Encoder Velocity", Drivetrain.encoderRight.getRate());
		SmartDashboard.putNumber("Left Encoder Position", Drivetrain.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right Encoder Position", Drivetrain.encoderRight.getDistance());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}