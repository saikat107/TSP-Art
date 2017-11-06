package edu.virginia.cs.tspim;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

public class KruskalAlgorithm 

{

	List<Edge> edges;
	int numberOfVertices;
	int visited[];
	double spanning_tree[][];
	ArrayList<TreeEdges> tree = new ArrayList<TreeEdges>();
	
	public KruskalAlgorithm(int numberOfVertices) {

		this.numberOfVertices = numberOfVertices;
		edges = new LinkedList<Edge>();
		visited = new int[this.numberOfVertices];
		spanning_tree = new double[numberOfVertices][numberOfVertices];

	}

	public void kruskalAlgorithm(double adjacencyMatrix[][], ArrayList<Node> nodeList) throws Exception

	{
		FileOutputStream fos = new FileOutputStream("Output");
	    DataOutputStream dos = new DataOutputStream(fos);

		boolean finished = false;

		for (int source = 0; source < numberOfVertices; source++) {
			for (int destination = 0; destination < numberOfVertices; destination++) {

				if (adjacencyMatrix[source][destination] != Config.MAX_VALUE && source != destination)

				{

					Edge edge = new Edge();

					edge.v1 = source;

					edge.v2 = destination;

					edge.weight = adjacencyMatrix[source][destination];

					adjacencyMatrix[destination][source] = Config.MAX_VALUE;

					edges.add(edge);

				}

			}

		}

		Collections.sort(edges, new EdgeComparator());
		Util.logln("After sort");

		CheckCycle checkCycle = new CheckCycle();
		//int l =0;
		for (Edge edge : edges)

		{
			//Util.logln(l);
			//l++;
			// Util.logln("Here");

			spanning_tree[edge.v1][edge.v2] = edge.weight;

			spanning_tree[edge.v2][edge.v1] = edge.weight;

			if (checkCycle.checkCycle(spanning_tree, edge.v1))

			{

				spanning_tree[edge.v1][edge.v2] = 0;

				spanning_tree[edge.v2][edge.v1] = 0;

				edge.weight = -1;

				continue;

			}

			visited[edge.v1] = 1;

			visited[edge.v2] = 1;
			
			//Util.logln(visited.length);
			
			for (int i = 0; i < visited.length; i++)

			{
				//Util.logln(i);

				if (visited[i] == 0)

				{
					finished = false;
					break;

				} else

				{

					finished = true;

				}

			}

			if (finished) {
				Util.logln("FInished");
				break;
			}

		}

		System.out.println("The spanning tree is ");

		for (int i = 0; i < numberOfVertices; i++)

			System.out.print("\t" + i);

		System.out.println();

		for (int source = 0; source < numberOfVertices; source++)

		{
			System.out.print(source + "\t");

			for (int destination = 0; destination < numberOfVertices; destination++)

			{
				if(spanning_tree[source][destination]!=0.0)
				{
					TreeEdges e = new TreeEdges();
					e.set_s(nodeList.get(source));
					e.set_d(nodeList.get(destination));
					e.set_weight(spanning_tree[source][destination]);
					tree.add(e);
				}
				System.out.print(spanning_tree[source][destination] + "\t");
				dos.writeInt(source);
				dos.writeChar(' ');
				dos.write(destination);
				dos.writeChar(':');
				dos.writeDouble(spanning_tree[source][destination]);
				dos.writeChar('\n');

			}

			System.out.println();

		}
		dos.close();

	}
/*
	public static void main(String args[]) {
        int adjacency_matrix[][];

        int number_of_vertices;

 

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of vertices");

        number_of_vertices = scan.nextInt();

        adjacency_matrix = new int[number_of_vertices][number_of_vertices];

 

        System.out.println("Enter the Weighted Matrix for the graph");

        for (int i = 0; i <number_of_vertices; i++)

        {

            for (int j = 0; j < number_of_vertices; j++)

            {

                adjacency_matrix[i][j] = scan.nextInt();

                if (i == j)

                {

                    adjacency_matrix[i][j] = 0;

                    continue;

                }

                if (adjacency_matrix[i][j] == 0)

                {

                    adjacency_matrix[i][j] = Config.MAX_VALUE;

                }

            }

        }
        for(int i=0;i<number_of_vertices;i++)
        {
        	for(int j=0;j<number_of_vertices;j++)
        		System.out.print(adjacency_matrix[i][j]+ "\t");
        	System.out.println();
        }
        
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(number_of_vertices);

        kruskalAlgorithm.kruskalAlgorithm(adjacency_matrix);

        scan.close();

    }*/

}
/*

0 6 8 6 0 0
6 0 0 5 10 0
8 0 0 7 5 3
6 5 7 0 0 0
0 10 5 0 0 3
0 0 3 0 3 0
*/