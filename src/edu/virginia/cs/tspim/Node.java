package edu.virginia.cs.tspim;

public class Node {
	int i;
	int j;
	
	Node()
	{
		i = 0;
		j =0;
	}
	
	public void set_i(int x)
	{
		i=x;
	}
	
	public void set_j(int y)
	{
		j = y;
	}
	
	public int get_i()
	{
		return i;
	}
	
	public int get_j()
	{
		return j;
	}

}
