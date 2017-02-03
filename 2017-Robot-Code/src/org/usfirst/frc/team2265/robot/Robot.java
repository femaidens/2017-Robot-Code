
package org.usfirst.frc.team2265.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2265.robot.commands.ExampleCommand;
import org.usfirst.frc.team2265.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2265.robot.subsystems.HelloCV;
//import org.usfirst.frc.team2265.robot.subsystems.Camera;
import org.usfirst.frc.team2265.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

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
	// public static Camera camera;
	public static HelloCV mask;
	Command autonomousCommand;
	// public static Mat image, image2, blurredImage, hsvImage, bigMask,
	// smallMask, finalMask, morphOutput;
	public static Mat image, image2;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drivetrain = new Drivetrain();
		// camera = new Camera();
		Drivetrain.encoderLeft.reset();
		Drivetrain.encoderRight.reset();
		oi.bindButtons();
		// instantiate the command used for the autonomous period
		autonomousCommand = new ExampleCommand();
		//mask = new HelloCV();
		/*
		 * cam = CameraServer.getInstance().startAutomaticCapture();
		 * cam.setBrightness(0); cam.setResolution(640, 480);
		 */
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		camera.setBrightness(0);
		CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
		CvSink cvSink = CameraServer.getInstance().getVideo();
		new Thread(() -> {
			
			Mat source = new Mat();
			image = new Mat();
			image2 = new Mat();

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
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
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
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}