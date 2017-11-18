package edu.virginia.cs.tspim;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import edu.virginia.cs.tspim.util.Util;


public class TourExtractor {
	//static int []x = new int[]{350,	200, 200, 300, 300, 50,  50,  10,  50,  60,  60,  50,  300, 350, 350, 400, 350, 200, 200, 100, 100, 100, 200, 150, 200, 200, 350, 400, 400, 500, 500, 250, 350, 400, 400, 600, 600, 550, 550, 300, 600, 650, 650, 595, 595, 400, 595, 600, 600, 698};																																						
	//static int []y = new int[]{70, 	80,  80,  140, 140, 210, 210, 210, 210, 240, 240, 280, 140, 210, 210, 210, 210, 280, 280, 280, 280, 350, 280, 350, 280, 350, 210, 280, 280, 280, 280, 350, 70,  180, 180, 292, 292, 468, 468, 648, 292, 492, 492, 468, 468, 648, 468, 692, 292, 548};
	
	public static Image getTour(ArrayList<TreeEdges> edges){
		//Util.logln(edges.size());
		Map<String, Node> nodesMap = new HashMap<String, Node>();
		Image img = new Image(700, 700);
		
		for(TreeEdges edge : edges){
			Node a = edge.getNode_s();
			Node b = edge.getNode_d();
			a.addChild(b);
			b.setParent(a);
			nodesMap.put(a.toString(), a);
			nodesMap.put(b.toString(), b);
		}
		
		Set<String> keys = nodesMap.keySet();
		Util.logln(keys.size());
		int root_count = 0;
		Node root = null;
		
		for(String key : keys){
			Node n = nodesMap.get(key);
			if (n.getParent() == null){
				root = n;
				root_count++;
				//break;
			}
		}
		Util.logln("Root Count:"+ root_count);
		Node previousDrawn = null;
		Stack<Node> st = new Stack<Node>();
		st.push(root);
		while(!st.isEmpty()){
			Node top = st.pop();
			if(previousDrawn != null){
				img.drawLine(previousDrawn, top);
			}
			previousDrawn = top;
			List<Node> children = top.getChildren();
			for(Node child : children){
				st.push(child);
			}
		}
		if (previousDrawn != null){

			img.drawLine(previousDrawn, root);
		}
		img.showImage("TOURIMG");
		return img;
	}
	
	public static Image extractTourImage(ArrayList<TreeEdges> edgeList) throws IOException{
		int numEdges = edgeList.size();
		Writer wr = new FileWriter("Coordinates.csv");
		//Util.logln(numEdges);
		Set<TreeEdges> edgeSet= new HashSet<TreeEdges>();
		edgeSet.addAll(edgeList);
		//Util.logln(edgeSet.size());
		
		int []xCordinates = new int[2*numEdges];
		int []yCordinates = new int[2*numEdges];
		int x = 0, y = 0;
		Image img = new Image(700, 700);
		for(TreeEdges edge : edgeList){
			xCordinates[x++] = edge.s.getX();
			yCordinates[y++] = edge.s.getY();
			xCordinates[x++] = edge.d.getX();
			yCordinates[y++] = edge.d.getY();
			img.drawLine(edge.s, edge.d);
			wr.write(String.valueOf(edge.s.getX()));
			wr.write(" ");
			wr.write(String.valueOf(edge.s.getY()));
			wr.write(",");
			wr.write(String.valueOf(edge.d.getX()));
			wr.write(" ");
			wr.write(String.valueOf(edge.d.getY()));
			wr.write("\n");
			
		}
		wr.close();
		img.showImage("MSTIMG");
		return getTour(edgeList);
	}
	/*public static void main(String[] args) {
		Node src = new Node(1,2);
		Node dest = new Node(3,4);
		TreeEdges edge = new TreeEdges();
		edge.s = src;
		edge.d = dest;
		
		TreeEdges edge1 = new TreeEdges();
		edge1.s = dest;
		edge1.d = src;
		
		Util.logln(edge.toString() == edge1.toString());
		Set<TreeEdges> edges = new HashSet<>();
		edges.add(edge);
		edges.add(edge1);
		Util.logln(edges.size());
	}*/
}
