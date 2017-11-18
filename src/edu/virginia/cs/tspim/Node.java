package edu.virginia.cs.tspim;

import java.util.ArrayList;
import java.util.List;

public class Node {
	int x;
	int y;
	
	private List<Node> children;
	private Node parent;
	
	public Node()
	{
		x = 0;
		y = 0;
		children = new ArrayList<Node>();
		parent = null;
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

	public List<Node> getChildren(){
		return children;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public void addChild(Node c){
		this.children.add(c);
	}
	
	public void setParent(Node p){
		this.parent = p;
	}
	
	@Override
	public String toString(){
		return x + " " + y;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Node) {
			Node node = (Node) o;
			return node.x == this.x && node.y == this.y;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return this.toString().hashCode();
	}
}
