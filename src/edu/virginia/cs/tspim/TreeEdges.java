package edu.virginia.cs.tspim;

public class TreeEdges {
	Node s;
	Node d;
	double weight;
	
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
		return this.s.toString() + " -> " + this.d.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof TreeEdges) {
			TreeEdges edge = (TreeEdges) o;
			if(this.s.equals(edge.s)){
				return this.d.equals(edge.d);
			}
			else if(this.s.equals(edge.d)) {
				return this.d.equals(edge.s);
			}
			return false;
		}
		return false;
	}

}
