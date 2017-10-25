package edu.virginia.cs.tspim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import edu.virginia.cs.tspim.util.Util;


public class HelloCV {
        public static void main(String[] args) throws IOException{
        	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        	System.out.println("Enter File Name: ");
        	Scanner input_sc = new Scanner(System.in);
            String file_name = "images.png";//input_sc.nextLine();
            GrayScale_Conversion con = new GrayScale_Conversion(file_name);
            Mat greyImage = con.convert_to_Gray(true);
            int row = greyImage.rows();
            int col = greyImage.cols();
            Util.logln(row + " " + col + " " + Util.getAverageIntensityFromImage(greyImage));
            input_sc.close();
        }
}

