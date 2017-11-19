package edu.virginia.cs.tspim;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.virginia.cs.tspim.util.Util;

public class Image {
	int [][] nodes;
	int width;
	int height;
	int scale;
	
	public Image(int width, int height, int scale){
		this.scale = scale;
		nodes = new int[height * scale][width *scale];
		this.width = width;
		this.height = height;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				nodes[i][j] = 0xffffffff; //Initialize all pixel as White
			}
		}
	}
	
	public void setNodeInImage(Node n){
		nodes[n.getX()][n.getY()] = 0;
	}
	
	public void drawLine(Node a1, Node b1){

//		Util.logln(a);
//		Util.logln(b);
		Node a = new Node(scale * a1.getX(), scale * a1.getY());
		Node b = new Node(scale * b1.getX(), scale * b1.getY());
		int dx = b.getX() - a.getX();
		int dy = b.getY() - a.getY();
		int steps = 0;
		if(Math.abs(dx) > Math.abs(dy)){
			steps = Math.abs(dx);
		}
		else{
			steps = Math.abs(dy);
		}
		double xImcr = (double)dx / steps;
		double yIncr = (double)dy / steps;
		double x = a.getX();
		double y = a.getY();
		for(int i = 0; i < steps; i++){
			Node point = new Node((int)Math.round(x), (int)Math.round(y));
			setNodeInImage(point);
			x += xImcr;
			y += yIncr;
		}
	}
	
	public BufferedImage extractImage(){
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				img.setRGB(j, i, nodes[i][j]);
			}
		}
		return img;
	}
	
	public void writeImageToDisk(String path) throws IOException{
		File file = new File(path);
		ImageIO.write(extractImage(), "jpg", file);
	}
	

	/*public void showImage(){

		BufferedImage img = extractImage();
		JFrame frame = new JFrame("Nodes Image");
		frame.setIconImage(img);
		frame.setSize(width, height);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(img));
		label.setPreferredSize(new Dimension(height, width));
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}*/
	
	public void showImage(String windowTitle){
		BufferedImage img = extractImage();
		JFrame frame = new JFrame(windowTitle);
		frame.setIconImage(img);
		frame.setSize(scale * width, scale * height);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(img));
		label.setPreferredSize(new Dimension(height, width));
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
