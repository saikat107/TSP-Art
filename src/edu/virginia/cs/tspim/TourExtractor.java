package edu.virginia.cs.tspim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class TourExtractor {
	static int []x = new int[]{350,	200, 200, 300, 300, 50,  50,  10,  50,  60,  60,  50,  300, 350, 350, 400, 350, 200, 200, 100, 100, 100, 200, 150, 200, 200, 350, 400, 400, 500, 500, 250, 350, 400, 400, 600, 600, 550, 550, 300, 600, 650, 650, 595, 595, 400, 595, 600, 600, 698};																																						
	static int []y = new int[]{70, 	80,  80,  140, 140, 210, 210, 210, 210, 240, 240, 280, 140, 210, 210, 210, 210, 280, 280, 280, 280, 350, 280, 350, 280, 350, 210, 280, 280, 280, 280, 350, 70,  180, 180, 292, 292, 468, 468, 648, 292, 492, 492, 468, 468, 648, 468, 692, 292, 548};
	
	public void getTour(int []x, int []y){
		assert x.length == y.length && x.length%2 == 0;
		Util.logln(x.length);
		Map<String, TreeNode> nodesMap = new HashMap<String, TreeNode>();
		Image img = new Image(700, 700);
		
		for(int i = 0; i < x.length; i+=2){
			Node a = new Node(x[i], y[i]);
			Node b = new Node(x[i+1], y[i+1]);
			TreeNode ta = nodesMap.get(a.toString());
			if(ta == null){
				ta = new TreeNode(a);
			}
			TreeNode tb = nodesMap.get(b.toString());
			if(tb == null){
				tb = new TreeNode(b);
			}
			ta.addChild(tb);
			tb.setParent(ta);
			nodesMap.put(a.toString(), ta);
			nodesMap.put(b.toString(), tb);
		}
		
		Set<String> nodes = nodesMap.keySet();
		TreeNode root = null;
		for(String n : nodes){
			TreeNode a = nodesMap.get(n);
			if (a.getParent() == null){
				root = a;
				Util.logln("Found Root");
			}
		}
		
		TreeNode previousDrawn = null;
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		while(!st.isEmpty()){
			TreeNode top = st.pop();
			if(previousDrawn != null){
				img.drawLine(previousDrawn.getNode(), top.getNode());
			}
			previousDrawn = top;
			List<TreeNode> children = top.getChildren();
			for(TreeNode child : children){
				st.push(child);
			}
		}
		if (previousDrawn != null){
			img.drawLine(previousDrawn.getNode(), root.getNode());
		}
		img.showImage();
	}
	
	public void getTour(List<TreeEdges>)
	
	/*public static void main(String[] args) {
		Main main = new Main();
		main.getTour(x, y);
	}*/
}
