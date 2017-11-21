package edu.virginia.cs.tspim.util;


public class Config {
	private double ALPHA;//0.85;
	private int BLOCK_SIZE;//2;
	private String fileName;
	public static final double MAX_VALUE = 999.0;
	private static Config instance = null;
	private int scale;
	
	public int getScale() {
		return scale;
	}


	public void setScale(int scale) {
		this.scale = scale;
	}

	private int imageHeight;
	public int getImageHeight() {
		return imageHeight;
	}


	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}


	public int getImageWidth() {
		return imageWidth;
	}


	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	private int imageWidth;
	
	private Config(){
		ALPHA = 0.85;
		BLOCK_SIZE = 5;
		fileName = "mona-lisa.jpg";
	}
	
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		return instance;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public void setAlpha(double alpha){
		this.ALPHA = alpha;
	}
	
	public void setBlockSize(int bs){
		this.BLOCK_SIZE = bs;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public int getBlockSize(){
		return this.BLOCK_SIZE;
	}
	
	public double getAlpha(){
		return this.ALPHA;
	}
	
	public String toString(){
		return fileName + " " + BLOCK_SIZE + " " + ALPHA;
	}

}

