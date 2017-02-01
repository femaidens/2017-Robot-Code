package org.usfirst.frc.team2265.robot.subsystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class HelloCV {
    static Mat image,blurredImage,hsvImage,bigMask,smallMask,finalMask,morphOutput;
    static List<MatOfPoint> contours, contours1;
    static double midX;
	static double midY;
    public static void removeNoise(Mat image) {
        blurredImage = new Mat();
        hsvImage = new Mat();
        bigMask = new Mat();
        smallMask = new Mat();
        finalMask = new Mat();
        Size size = new Size(3,3);
        System.out.println(size.toString());
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV, 0);
        Imgproc.blur(hsvImage, blurredImage, size);
    }
    public static ArrayList<Double> contouring(Mat image){
        contours = new ArrayList<MatOfPoint>();
        image.convertTo(image, CvType.CV_8UC1);

        Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println(contours);
        //ArrayList<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
        Imgproc.drawContours(image, contours, -1, new Scalar(60, 255, 255), 2);
        ArrayList<Rect> rectList = new ArrayList<Rect>(); 
        ArrayList<Double> centerList = new ArrayList<Double>();
        
        for(int i = 0; i < contours.size(); i++){
        	MatOfPoint cPoint = contours.get(i);
        	Rect rect = Imgproc.boundingRect(cPoint);
        	rectList.add(rect);   
        }
        System.out.println(rectList);
        
        midX = (rectList.get(0).x * 2 + rectList.get(1).x)/3.25;
        midY = rectList.get(0).y + rectList.get(1).y/3;
        
        centerList.add(midX);
        centerList.add(midY);
       
        Imgproc.circle(image, new Point(midX, midY), 1, new Scalar(255, 255, 255), 2);
        Imgproc.circle(image, new Point(326, 157), 1, new Scalar(180, 255, 255), 2);
        Imgproc.circle(image, new Point(138, 152), 1, new Scalar(180, 255, 255), 2);
        
        return centerList;
    }

}
    
    
