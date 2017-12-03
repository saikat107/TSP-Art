package edu.virginia.cs.tspim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.virginia.cs.tspim.util.Util;


public class TourExtractor {
	//static int []x = new int[]{350,	200, 200, 300, 300, 50,  50,  10,  50,  60,  60,  50,  300, 350, 350, 400, 350, 200, 200, 100, 100, 100, 200, 150, 200, 200, 350, 400, 400, 500, 500, 250, 350, 400, 400, 600, 600, 550, 550, 300, 600, 650, 650, 595, 595, 400, 595, 600, 600, 698};																																						
	//static int []y = new int[]{70, 	80,  80,  140, 140, 210, 210, 210, 210, 240, 240, 280, 140, 210, 210, 210, 210, 280, 280, 280, 280, 350, 280, 350, 280, 350, 210, 280, 280, 280, 280, 350, 70,  180, 180, 292, 292, 468, 468, 648, 292, 492, 492, 468, 468, 648, 468, 692, 292, 548};
	private int width, height, scale;
	Image mstImg ;
	Image tourImg ;
	Image swpImg ;	
	public TourExtractor(int width, int height, int scale){
		this.width = width;
		this.height = height;
		this.scale = scale;
		mstImg = new Image(this.width, this.height, scale, "MST_IMG");
		tourImg = new Image(this.width, this.height, scale, "TOUR_IMAGE");
		swpImg = new Image(this.width, this.height, scale, "SWAPPED_IMAGE");
	}
	
	public  void getTour(List<TreeEdges> edges, int scale, boolean swap, int iter){
		//Util.logln(edges.size());
		Map<String, Node> nodesMap = new HashMap<String, Node>();
		
		
		for(TreeEdges edge : edges){
			Node a = edge.getNode_s();
			Node b = edge.getNode_d();
			a.addChild(b);
			b.addChild(a);
			nodesMap.put(a.toString(), a);
			nodesMap.put(b.toString(), b);
		}
		
		Set<String> keys = nodesMap.keySet();
		Util.logln(keys.size());
		int root_count = 0;
		Node root = null;
		
		for(String key : keys){
			Node n = nodesMap.get(key);
			if (n.getChildren().size() == 1){
				root = n;
				root_count++;
				break;
			}
		}
		Util.logln("Root Count:"+ root_count);
		List<TreeEdges> tourEdges = new ArrayList<>();
		List<Node> visitedNodes = new ArrayList<Node>();
		Node previousDrawn = null;
		Node r = root;//nodesMap.get(key);
		Stack<Node> st = new Stack<Node>();
		st.push(r);
		while(!st.isEmpty()){
			Node top = st.pop();
			if(visitedNodes.contains(top)){
				continue;
			}
			visitedNodes.add(top);
			if(previousDrawn != null){
				TreeEdges nEdge = new TreeEdges(previousDrawn, top);
				tourEdges.add(nEdge);
				tourImg.drawLine(previousDrawn, top);
			}
			previousDrawn = top;
			List<Node> children = top.getChildren();
			for(Node child : children){
				st.push(child);
			}
		}
		if (previousDrawn != null){
			TreeEdges nEdge = new TreeEdges(previousDrawn, root);
			tourEdges.add(nEdge);
			tourImg.drawLine(previousDrawn, root);
		}		
		//tourImg.showImage();
		if(swap){
			int edgeNumber = tourEdges.size();
			Util.logln(edgeNumber);
			for(int k = 0; k < iter; k++){
				//Util.logln("Iteration Number : " + k);
				for(int i = 0; i < edgeNumber; i++){
					TreeEdges edge1 = tourEdges.get(i);
					for(int j = i+1; j < edgeNumber; j++){
						TreeEdges edge2 = tourEdges.get(j);
						//Util.logln(edge1);
						if(Util.isLineCrossing(edge1, edge2)){
							Util.swapDestinationPointInEdges(edge1, edge2);
							Util.logln(edge1.toString() + " ==> " + edge2 + " -- -- -- " + k);
							int minIdx = i + 1;
							int maxIdx = j - 1;
							while(maxIdx > minIdx){
								TreeEdges one = tourEdges.get(minIdx).reverseEdge();
								TreeEdges two = tourEdges.get(maxIdx).reverseEdge();
								tourEdges.set(minIdx, two);
								tourEdges.set(maxIdx, one);
								minIdx++;
								maxIdx--;
							}
							if(minIdx == maxIdx){
								tourEdges.set(minIdx, tourEdges.get(minIdx).reverseEdge());
							}
						}	
					}
				}
			}
			for(int i = 0; i < edgeNumber; i++){
				TreeEdges edge1 = tourEdges.get(i);
				swpImg.drawLine(edge1.s, edge1.d);
			}
			//swpImg.showImage();
		}
	
		Util.logln("TourImage Printed");
	}
	
	public void extractTourImage(List<TreeEdges> edgeList, boolean mst, boolean tour, boolean swap, int iter) throws IOException{
		if(mst){
			for(TreeEdges edge : edgeList){
				mstImg.drawLine(edge.s, edge.d);			
			}
		}
		if(tour){
			getTour(edgeList, scale, swap, iter);
		}
	}
	
	public Image getMSTImage(){
		return this.mstImg;
	}
	
	public Image getSwappedImage(){
		return this.swpImg;
	}
	
	public Image getTourImage(){
		return this.tourImg;
	}
	/*public static void main(String[] args) {
		Node src = new Node(100,200);
		Node dest = new Node(3,4);
		TreeEdges edge = new TreeEdges();
		edge.s = src;
		edge.d = dest;
		
		TreeEdges edge1 = new TreeEdges();
		edge1.s = dest;
		edge1.d = src;
		
		Util.logln(edge.equals(edge1));
		Set<TreeEdges> edges = new HashSet<>();
		edges.add(edge);
		edges.add(edge1);
		Util.logln(edges.size());
	}*/
}
