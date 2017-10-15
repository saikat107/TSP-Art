
import java.util.Scanner;

import org.opencv.core.Core;


public class HelloCV {
        public static void main(String[] args){
        	System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        	System.out.println("Enter File Name: ");
        	Scanner input_sc = new Scanner(System.in);
            String file_name = input_sc.nextLine();
            GrayScale_Conversion con = new GrayScale_Conversion(file_name);
            con.convert_to_Gray();
            input_sc.close();
        }
}

