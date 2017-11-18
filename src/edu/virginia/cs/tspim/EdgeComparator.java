
package edu.virginia.cs.tspim;

import java.util.Comparator;

class EdgeComparator implements Comparator<Edge>

{
	@Override
	public int compare(Edge edge1, Edge edge2) {

		if (edge1.weight < edge2.weight)
			return -1;

		if (edge1.weight > edge2.weight)
			return 1;

		return 0;
		//return Double.compare(edge1.weight, edge2.weight);

	}

}