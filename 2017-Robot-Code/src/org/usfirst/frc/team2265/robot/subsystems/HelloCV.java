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
    public static void contouring(Mat image){
        contours = new ArrayList<MatOfPoint>();
        image.convertTo(image, CvType.CV_8UC1);

        Imgproc.findContours(image, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println(contours);
        //ArrayList<MatOfPoint> contours1 = new ArrayList<MatOfPoint>();
        Imgproc.drawContours(image, contours, -1, new Scalar(60, 255, 255), 2);
        ArrayList<Rect> rectList = new ArrayList<Rect>(); 
        
        for(int i = 0; i < contours.size(); i++){
        	MatOfPoint cPoint = contours.get(i);
        	Rect rect = Imgproc.boundingRect(cPoint);
        	rectList.add(rect);   
        }
        System.out.println(rectList);
        
        double midX = (rectList.get(0).x * 2 + rectList.get(1).x)/3.25;
        double midY = rectList.get(0).y + rectList.get(1).y/3;
        
        Imgproc.circle(image, new Point(midX, midY), 1, new Scalar(255, 255, 255), 2);
        Imgproc.circle(image, new Point(326, 157), 1, new Scalar(180, 255, 255), 2);
        Imgproc.circle(image, new Point(138, 152), 1, new Scalar(180, 255, 255), 2);
        //Mat contourImg = new Mat(image.size(), image.type());
        //for (int i = 0; i < contours.size(); i++) {
          //  Imgproc.drawContours(contourImg, contours, i, new Scalar(255, 255, 255), 1);}
    
    }
    
    public static ArrayList<Double> getContourArea(){
    	Iterator<MatOfPoint> contourItr = contours.iterator();
    	ArrayList<Double> area = new ArrayList<Double>();
    	while(contourItr.hasNext()){
    		MatOfPoint p = contourItr.next();
    		area.add(Imgproc.contourArea(p));
    		
    	}
		return area;
    }
    
    public static Mat process(Mat source) {
       // System.loadLibrary("opencv_java320");
        //image = Imgcodecs.imread("D:\\R\\RetroreflectiveTape.png");
    	/*image = source; 
        removeNoise(image);
        Scalar bigMinValues = new Scalar(35,155,165); //placeholder values 
        Scalar bigMaxValues = new Scalar(115,245,255); //placeholder values 
        Core.inRange(blurredImage, bigMinValues, bigMaxValues,bigMask);
        //Scalar smallMinValues = new Scalar(165,200,215); //placeholder values 
       // Scalar smallMaxValues = new Scalar(185,250,235); //placeholder values 
       // Core.inRange(blurredImage, smallMinValues, smallMaxValues,smallMask);
       // Core.addWeighted(bigMask, 1,smallMask,1, 0, finalMask);
        contouring(bigMask);*/
    Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2GRAY, 0);
        return bigMask;
        //System.out.println(getContourArea());
        //Imgcodecs.imwrite("D:\\R\\contouredd.jpg",bigMask);
    }
    /*
     * public static void valuesHSV() { Scalar minValues = new Scalar(100, 100,
     * 100); //placeholder values Scalar maxValues = new Scalar(180,180,180); //
     * placeholder values }
     * 
     */
    // IplImage img = cvLoadImage("E:\\circles.jpg");
    // IplImage imghsv = cvCreateImage(cvGetSize(img), IPL_DEPTH_8U, 3); //don't
    // know anymore
    // Mat img1 = Imgcodecs.imread(frame);
    // Mat imgfin = createImage(getSize(img1), IPL_DEPTH_8U, 3); // don't know
    // anymore
    // cvCvtColor(img1,hsvimg, CV_BGR2HSV);
    // cvInRangeS (imghsv, minValues, maxValues, imgfin);

    // showImage("Original", image);
    // showImage("Final", imgfin);

    // releaseImage(img1);
    // cvReleaseImag(imghsv);

}
