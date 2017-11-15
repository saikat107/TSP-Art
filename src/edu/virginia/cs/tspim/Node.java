package edu.virginia.cs.tspim;

public class Node {
	int x;
	int y;
	
	Node()
	{
		x = 0;
		y =0;
	}
	
	public Node(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public String toString(){
		return "(" + x + "," + y + ")";
	}
}
