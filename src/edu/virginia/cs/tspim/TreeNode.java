package edu.virginia.cs.tspim;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private Node node;
	private List<TreeNode> children;
	private TreeNode parent;
	
	public TreeNode(Node n){
		this.node = n;
		this.children = new ArrayList<TreeNode>();
	}
	
	public List<TreeNode> getChildren(){
		return this.children;
	}
	
	public void addChild(TreeNode t){
		children.add(t);
	}
	
	public void setParent(TreeNode p){
		this.parent = p;
	}
	
	public TreeNode getParent(){
		return this.parent;
	}

	public String toString(){
		return node + " " + children.size() + " " + parent;
	}
	
	public Node getNode(){
		return this.node;
	}
	
	public boolean equals(TreeNode a){
		return this.node.equals(a);
	}
}
