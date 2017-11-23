package edu.virginia.cs.tspim.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.virginia.cs.tspim.MST;
import edu.virginia.cs.tspim.Node;
import edu.virginia.cs.tspim.TourExtractor;
import edu.virginia.cs.tspim.TreeEdges;

public class Util {
	
	private static final int INFINITE = ~(1<<31);
	
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
	
	
	 public static double getSlope(Node n1, Node n2){
         int ydiff = n2.getY() - n1.getY();
		 int xdiff = n2.getX() - n1.getX();
		 if(xdiff == 0){                 
			 return (double)INFINITE;
		 }
		 else{
		      return (double)ydiff / xdiff;
		 }
	}
	
	public static boolean isParallelLine(TreeEdges edge1, TreeEdges edge2){
		 Node s1 = edge1.getNode_s();
		 Node s2 = edge1.getNode_d();
		 double slope1 = getSlope(s1, s2);
		 //Util.logln(slope1);
		 Node s3 = edge2.getNode_s();
		 Node s4 = edge2.getNode_d();
		 double slope2 = getSlope(s3, s4);
		 //Util.logln(slope2);
		 return Math.abs(slope1 - slope2) < 1e-10;
	}
	
	
	public static double min(double a, double b){
		return a < b?a:b;
	}
			       
	public static double max(double a, double b){
		return a > b? a : b;
	}
		       
	public static boolean isLineCrossing(TreeEdges edge1, TreeEdges edge2){
		if(isParallelLine(edge1, edge2)){
			return false;
		}
		else{
			Node s1 = edge1.getNode_s();
			Node s2 = edge1.getNode_d();
			Node s3 = edge2.getNode_s();
			Node s4 = edge2.getNode_d();
			int x1 = s1.getX();
			int y1 = s1.getY();
			int x2 = s2.getX();
			int y2 = s2.getY();
			int x3 = s3.getX();
			int y3 = s3.getY();
			int x4 = s4.getX();
			int y4 = s4.getY();
			double a1 = y1 - y2;
			double b1 = x2 - x1;
			double c1 = -a1*x1 - b1*y1;	                       
			double a2 = y3 - y4;
			double b2 = x4 - x3;
			double c2 = -a2*x3 - b2*y3;
			double det = a1* b2 - a2 * b1;
			double x = (b1 * c2 - b2 * c1) / det;
			double y = (c1 * a2 - c2 * a1) / det;
			//Util.logln(x + " " + y);
			boolean insideLine1 = x > min(x1, x2) && x < max(x1, x2) && y > min(y1, y2) && y < max(y1, y2);
			boolean insideLine2 = x > min(x3, x4) && x < max(x3, x4) && y > min(y3, y4) && y < max(y3, y4);
			return insideLine1 && insideLine2;
		}
	}
	
	
	public static void swapDestinationPointInEdges(TreeEdges edge1, TreeEdges edge2){
		if(isLineCrossing(edge1, edge2)){
			Node d1 = edge1.getNode_d();
			Node d2 = edge2.getNode_d();
			edge1.set_d(d2);
			edge2.set_d(d1);
		}
	}
	
	public static void main(String args[]){
		for(int i = 0; i < 10; i++)
			Util.logln(genarateRandom(0, 5));
	}
	
	public static List<TreeEdges> generateMST(List<Node> nodeList) throws IOException {
		MST gr = new MST(nodeList.size(), ((nodeList.size() * (nodeList.size() - 1)) / 2), nodeList);
		int l = 0;
		for (int i = 0; i < nodeList.size() - 1; i++) {
			Node v1 = nodeList.get(i);
			for (int j = i + 1; j < nodeList.size(); j++) {
				Node v2 = nodeList.get(j);
				double distance = Math.sqrt(((v1.getX()-v2.getX())*(v1.getX()-v2.getX()))+((v1.getY()-v2.getY())*(v1.getY()-v2.getY())));
				if(i!=j){
					gr.getEdge(l).setSrc(i);
					gr.getEdge(l).setDest(j);
					gr.getEdge(l).setWeight(distance);
					l++;
				}
			}
		}
		Util.logln(l);
		List<TreeEdges> tree = gr.KruskalMST();
		return tree;
	}
}
