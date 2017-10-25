package edu.virginia.cs.tspim;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

class GrayScale_Conversion {
	
	String image_file;
	
	GrayScale_Conversion(String img_file)
	{
		image_file = img_file;
	}
	
	public Mat convert_to_Gray(boolean saveToFile) throws IOException{
        File input = new File(image_file);
        BufferedImage image = ImageIO.read(input);	

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);

        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
        Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
       
        if(saveToFile){
	        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
	        mat1.get(0, 0, data1);
	        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
	        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
	
	        File ouptut = new File(this.image_file + "grayscale.jpg");
	        ImageIO.write(image1, "jpg", ouptut);
        }
        return mat1;       
	}
}
