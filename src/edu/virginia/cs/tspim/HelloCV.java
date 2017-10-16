package edu.virginia.cs.tspim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.opencv.core.Core;

import edu.virginia.cs.tspim.util.Util;


public class HelloCV {
        public static void main(String[] args){
        	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        	System.out.println("Enter File Name: ");
        	Scanner input_sc = new Scanner(System.in);
            String file_name = "images.png";//input_sc.nextLine();
            GrayScale_Conversion con = new GrayScale_Conversion(file_name);
            con.convert_to_Gray();
            input_sc.close();
        }
}

