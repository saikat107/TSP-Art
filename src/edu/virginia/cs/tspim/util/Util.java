package edu.virginia.cs.tspim.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	
	public static void viewImage(int[][] arr, String fileName){
		BufferedImage img = new BufferedImage(arr[0].length, arr.length,BufferedImage.TYPE_BYTE_GRAY);
		Util.logln("Writing Sample");
		for(int i=0;i<arr[0].length;i++)
		{
			for(int j=0;j<arr.length;j++)
			{
				img.setRGB(i, j, ( arr[j][i] << 12) | (arr[j][i] << 8) | (arr[j][i] << 4));
			}
		}
		JFrame frame = new JFrame(fileName);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(img));
		frame.add(label);
		frame.setPreferredSize(new Dimension(arr.length, arr[0].length));
		frame.setSize(new Dimension(arr[0].length, arr.length));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void writeImage(int [][]arr, String filename)
	{
		//viewImage(arr, filename);
		BufferedImage img = new BufferedImage(arr[0].length, arr.length,BufferedImage.TYPE_BYTE_GRAY);
		//Util.logln("Writing Sample");
		for(int i=0;i<arr[0].length;i++)
		{
			for(int j=0;j<arr.length;j++)
			{
				img.setRGB(i, j, ( arr[j][i] << 12) | (arr[j][i] << 8) | (arr[j][i] << 4));
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
	
	public static int genarateRandom(int low, int high){
		double prob = Math.random();
		return (int)Math.round(low + (high - low) * prob);
	}
	
	public static void main(String args[]){
		for(int i = 0; i < 10; i++)
			Util.logln(genarateRandom(0, 5));
	}
}
