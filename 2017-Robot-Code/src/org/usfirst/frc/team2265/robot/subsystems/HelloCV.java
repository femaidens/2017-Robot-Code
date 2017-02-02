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
    public static Mat image;
	public static Mat blurredImage;
	static Mat hsvImage;
	static Mat bigMask;
	static Mat smallMask;
	static Mat finalMask;
	static Mat morphOutput;
    static List<MatOfPoint> contours, contours1;
    static double midX,midY,k,b;
    public static ArrayList<Rect> rectList;
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
    public static double getDistance(ArrayList<Rect> rectList){
    	Rect maxRect = rectList.get(0);
    	for(int i = 0; i<rectList.size();i++){
    		if(rectList.get(i).height> maxRect.height)
    			maxRect = rectList.get(i);
    	}
    	double distance=(maxRect.height-b)/k;
    	
    	/*for(int i = 0; i<rectList.size();i++){
    		if(rectList.get(i).area()> maxRect.area())
    			maxRect = rectList.get(i);
    	}
    	double distance=(Math.sqrt(maxRect.area())-b)/k;
    	*/
    	return distance;
    	
    }
    public static double contouring(Mat image){
        contours = new ArrayList<MatOfPoint>();
        image.convertTo(image, CvType.CV_8UC1);

        Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println(contours);
        //ArrayList<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
        Imgproc.drawContours(image, contours, -1, new Scalar(60, 255, 255), 2);
        rectList = new ArrayList<Rect>(); 
        //ArrayList<Double> centerList = new ArrayList<Double>();
        
        for(int i = 0; i < contours.size(); i++){
        	MatOfPoint cPoint = contours.get(i);
        	Rect rect = Imgproc.boundingRect(cPoint);
        	rectList.add(rect);   
        }
        System.out.println(rectList);
        
        midX = (rectList.get(0).x + (rectList.get(1).x + rectList.get(1).width))/2;
        //midY = (rectList.get(0).y + rectList.get(0).height)/2;
        
        //centerList.add(midX);
        //centerList.add(midY);
       
        return midX;
    }
    	
}
    
    
