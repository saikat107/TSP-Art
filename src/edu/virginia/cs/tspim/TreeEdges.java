package edu.virginia.cs.tspim;

public class TreeEdges {
	Node s;
	Node d;
	double weight;
	
	public TreeEdges(Node s, Node d){
		this.s = s;
		this.d = d;
	}
	
	public TreeEdges(){
		
	}
	
	public void set_s(Node x)
	{
		s = x;
	}
	public void set_d(Node y)
	{
		d = y;
	}
	public void set_weight(double w)
	{
		weight = w;
	}
	
	public Node getNode_s()
	{
		return s;
	}
	
	public Node getNode_d()
	{
		return d;
	}
	
	public double getWeight()
	{
		return weight;
	}

	public String toString(){
		return s.toString() + " -> " + d.toString();
	}
}
