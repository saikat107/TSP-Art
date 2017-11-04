package edu.virginia.cs.tspim;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

class GrayScale_Conversion {
	
	String image_file;
	int sum;
	float average_threshold;
	int [][]sample_pre_array;
	
	GrayScale_Conversion(String img_file)
	{
		image_file = img_file;
		sum =0;
		average_threshold =0;
	}
	
	public float get_threshold()
	{
		return average_threshold;
	}
	
	
	public void convert_to_Gray(){
	try {
        
        File input = new File(image_file);
        BufferedImage image = ImageIO.read(input);	

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);
                
        Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
        //Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(mat,mat1, 127,255, Imgproc.THRESH_BINARY);

        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
        mat1.get(0, 0, data1);
         
        BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
        
     
       Mat mat2 = new Mat(image1.getHeight(),image1.getWidth(),CvType.CV_8UC1);
       Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
       //Imgproc.threshold(mat1,mat2, 127,255, Imgproc.THRESH_BINARY);

       byte[] data2 = new byte[mat2.rows() * mat2.cols() * (int)(mat1.elemSize())];
       mat2.get(0, 0, data2);
       sample_pre_array = new int [mat2.rows()][mat2.cols()];
       
       for(int i=0;i<mat2.rows();i++)
       {
       	for(int j=0;j<mat2.cols();j++)
       	{
       		int a = data2[i+j] & 0xff;
       		//System.out.print(a+" "); 
       		sample_pre_array[i][j]=a;
       	}
       	//System.out.println();
       }
       
       for(int i=0;i<mat2.rows();i++)
       {
       	for(int j=0;j<mat2.cols();j++)
       	{
       		sum+=sample_pre_array[i][j]; 
       	}
       }
      
       average_threshold = sum/4;
       
       Density_Sampling s = new Density_Sampling(sample_pre_array,average_threshold);
       s.gen_Sample();
       
       File ouptut = new File("graymona.jpg");
       ImageIO.write(image1, "jpg", ouptut);
        
           
     } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
     }
	}

	public int[][] get_array() {
		
		return sample_pre_array;
	}
}
