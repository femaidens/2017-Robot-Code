
package org.usfirst.frc.team2265.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
import org.usfirst.frc.team2265.robot.commands.AutoAlign;
import org.usfirst.frc.team2265.robot.commands.CenterAuto;
import org.usfirst.frc.team2265.robot.commands.DriveDistance;
import org.usfirst.frc.team2265.robot.commands.ExampleCommand;
import org.usfirst.frc.team2265.robot.commands.LeftAuto;
import org.usfirst.frc.team2265.robot.commands.RightAuto;
import org.usfirst.frc.team2265.robot.commands.TurnDegrees;
import org.usfirst.frc.team2265.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2265.robot.subsystems.Climber;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;

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
	public static Climber climber;
	public static Compressor compressette; 
	public static I2C i2c;
	public static byte[] toSend;
	Command autonomousCommand;
	public static int midX;
	public static double distance, d;
	public static boolean slow = false;
	public static Timer timer;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		timer = new Timer();
		drivetrain = new Drivetrain();
		climber = new Climber();
		//Drivetrain.encoder.reset();
		compressette = new Compressor();
		i2c = new I2C(I2C.Port.kOnboard, 84);
	    toSend = new byte[1];
	   
	    oi.bindButtons();
	    distance = 3.0;
	    slow = false;
	    Drivetrain.gyro.calibrate();
	    Drivetrain.gyro.setSensitivity(0.007);
	    Drivetrain.gyro.reset();
	    timer.reset();
	    //Drivetrain.gyro.calibrate();
	    
	    
		
		
		// instantiate the command used for the autonomous period
		
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
		    				Scalar bigMinValues = new Scalar(50, 150, 100); // placeholder 72 160 200
		    																// values
		    				Scalar bigMaxValues = new Scalar(120, 255,255); // placeholder 72 160 200
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
		   				ArrayList<Rect> rectList = new ArrayList<Rect>();
		    				// ArrayList<Double> centerList = new ArrayList<Double>();
		    
		    				for (int i = 0; i < contours.size(); i++) {
		    					MatOfPoint cPoint = contours.get(i);
		    					Rect rect = Imgproc.boundingRect(cPoint);
		    					rectList.add(rect);
		    				}
		    			
		    				if (rectList.size() > 1){
		    				Imgproc.rectangle(image, new Point(rectList.get(0).x, rectList.get(0).y),
		    						new Point(rectList.get(0).x + rectList.get(1).width,
		    								rectList.get(0).y - rectList.get(1).height),
		    						new Scalar(179, 255, 255), 1); 
		    					midX = (rectList.get(0).x + (rectList.get(1).x + rectList.get(1).width))/2;
		    					//System.out.println("midX: " + midX);
		    					Rect maxRect = rectList.get(0);
		    			    	for(int i = 0; i<rectList.size();i++){
		    			    		if(rectList.get(i).height> maxRect.height)
		    			    			maxRect = rectList.get(i);
		    			    	}
		    			    	distance = -.0003502*(Math.pow(maxRect.height, 3)) +.085*(Math.pow(maxRect.height, 2)) - 6.98*maxRect.height+ 253.816;
		    			    	d = distance;
		    			    	//System.out.println("Distance from Peg (robot): "+ distance);
		    			    
		    				}
		    				
		    				outputStream.putFrame(image);
		    				image.release();
		    				image2.release();
		    				source.release();
		    			}
		    
		    		}).start();
		    //autonomousCommand = new RightAuto();
		    autonomousCommand = new CenterAuto();
		    //autonomousCommand = new LeftAuto();
	}
	public static double getDistanceFromPeg(){
		System.out.println("Distance from peg:" + d);
		return d;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		Drivetrain.gyro.reset();
		 timer.start();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		if (timer.get() >= 15.0) {
			autonomousCommand.cancel();
			//AutoAlign.done = true;
			//TurnDegrees.done = true;
		}
			
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//TurnDegrees.done = true;
		if (autonomousCommand != null){
			autonomousCommand.cancel();
			//AutoAlign.done = true;
			//TurnDegrees.done = true;
		}
		
		//Robot.toSend[0] = 88;
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
		//System.out.println(autonomousCommand.isRunning());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}