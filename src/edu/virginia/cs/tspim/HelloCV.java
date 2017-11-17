package edu.virginia.cs.tspim;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.opencv.core.Core;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;


public class HelloCV {
        public static void main(String[] args){
        	//System.load(new File("E:/PhD_Course Materials/opencv/build/java/x64/opencv_java330.dll").getAbsolutePath());
        	System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
        	System.out.println("Enter File Name: ");
        	Scanner input_sc = new Scanner(System.in);
            String file_name = Config.fileName;//input_sc.nextLine();
            GrayScale_Conversion con = new GrayScale_Conversion(file_name);
            con.convert_to_Gray();
            Density_Sampling s = new Density_Sampling(con.get_array(),con.get_threshold());
            s.gen_Sample();
            
            input_sc.close();
        }
}

