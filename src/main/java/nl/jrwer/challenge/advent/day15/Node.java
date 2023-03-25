package nl.jrwer.challenge.advent.day15;

import java.util.Objects;

public class Node implements Comparable<Node> {
	final int gCost; // distance from starting node
	final int hCost; // distance from end node
	final int fCost; // gCost + hCost;
	
	final int x, y, risk;
	final Node parentNode, endNode;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.risk = 0;
		this.parentNode = null;
		this.endNode = null;
		
		this.gCost = 0;
		this.hCost = 0;
		this.fCost = 0;		
	}
	
	public Node(int x, int y, int risk, Node parentNode, Node endNode) {
		this.x = x;
		this.y = y;
		this.risk = risk;
		this.parentNode = parentNode;
		this.endNode = endNode;
		
		this.gCost = parentNode != null ? parentNode.gCost + risk : 0;
		this.hCost = (endNode.x - x + endNode.y - y);
		this.fCost = gCost + hCost;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Node) {
			Node n = (Node) o;
			
			// if fCost of node is smaller, mark as not equal to remove it from te queue and add the shorter path
			return n.x == x && n.y == y && n.fCost <= fCost; 
		}
		
		return false;
	}

	@Override
	public int compareTo(Node n) {
		return Integer.compare(this.fCost, n.fCost);
	}
}
