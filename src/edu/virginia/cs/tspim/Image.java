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

public class Image {
	int [][] nodes;
	int width;
	int height;
	
	public Image(int width, int height){
		nodes = new int[height][width];
		this.width = width;
		this.height = height;
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				nodes[i][j] = 0xffffffff; //Initialize all pixel as White
			}
		}
	}
	
	public void setNodeInImage(Node n){
		nodes[n.getY()][n.getX()] = 0;
	}
	
	public void drawLine(Node a, Node b){
		Util.logln(a);
		Util.logln(b);
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
			Util.logln(point);
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
	
	public void showImage(){
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
	}
}
