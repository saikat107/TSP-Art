package edu.virginia.cs.tspim.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

public class Util {
	
	/**
	 * This method is for logging and debugging.
	 * @param message
	 */
	public static void logln(Object message){
		StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
		System.out.print(caller + " ");
		Util.println(message);
	}
	
	public static void println(Object message){
		Util.print(message);
		System.out.println();
	}
	public static void print(Object message){
		if (message instanceof Collection) {
			Collection msg = (Collection) message;
			System.out.print("[");
			for(Object obj : msg){
				Util.print(obj);
				System.out.print( " ");
			}
			System.out.println("]");
		}
		else if (message instanceof Map) {
			Map map = (Map) message;
			System.out.println("{");
			Set<Object>keys = map.keySet();
			for(Object key : keys){
				Util.print(key);
				System.out.print(" : ");
				Util.print(map.get(key));
			}
			System.out.println("}");
		}
		else{
			System.out.print(message);
		}
	}
	
	public static void writeImage(int [][]arr, String filename)
	{
		BufferedImage img = new BufferedImage(arr[0].length, arr.length,BufferedImage.TYPE_BYTE_GRAY);
		Util.logln("Writing Sample");
		for(int i=0;i<arr[0].length;i++)
		{
			for(int j=0;j<arr.length;j++)
			{
				img.setRGB(i, j, arr[j][i]);
			}
		}
		File f = new File(filename);
		try {
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
