package nl.jrwer.challenge.advent.day15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Cavern {
	final int[][] grid;
	final int width, height;
	final int endX, endY;
	
	public Cavern(int[][] grid) {
		this.grid = grid;
		
		this.height = grid.length;
		this.width = grid[0].length;
		this.endX = this.width - 1;
		this.endY = this.height - 1;
	}
	
	public Node aStar() {
		Node endNode = new Node(endX, endY);
		Node startNode = new Node(0, 0 ,0 , null, endNode);
		
		PriorityQueue<Node> open = new PriorityQueue<Node>();
		Set<Node> closed = new HashSet<>();
		
		open.add(startNode);
		
		while(!open.isEmpty()) {
			Node current = open.poll();
			closed.add(current);
			
			if(current.equals(endNode))
				return current;
			
			List<Node> neighbors = getNeighbors(current, endNode);
			
			for(Node neighbor : neighbors) {
				if(closed.contains(neighbor))
					continue;

				if(!open.contains(neighbor)) {
					open.add(neighbor);
				}
			}
			
		}
		
		return null;
	}

	private List<Node> getNeighbors(Node node, Node endNode) {
		List<Node> nodes = new ArrayList<>();
		int cX = node.x;
		int cY = node.y;

		if(inBounds(cX + 1, cY)) nodes.add(new Node(cX + 1, cY, get(cX + 1, cY), node, endNode));
		if(inBounds(cX - 1, cY)) nodes.add(new Node(cX - 1, cY, get(cX - 1, cY), node, endNode));
		if(inBounds(cX, cY + 1)) nodes.add(new Node(cX, cY + 1, get(cX, cY + 1), node, endNode));
		if(inBounds(cX, cY - 1)) nodes.add(new Node(cX, cY - 1, get(cX, cY - 1), node, endNode));
		
		return nodes;
	}
	
	public int findSafestRoute() {
		State bestState = new State();
		State startState = new State(0, 0, 0);
		Queue<State> q = new ArrayDeque<State>();
		Set<State> visited = new HashSet<>();
		
		visited.add(startState);
		q.add(startState);
		
		while(!q.isEmpty()) {
			State nextState = q.poll();
			
			if(nextState.risk > bestState.risk)
				continue;

			if(nextState.isCoord(width - 1, height - 1) && bestState.risk > nextState.risk) {
				bestState = nextState;
			} else {
				List<State> next = next(nextState);

				for(State s : next) 
					if(!visited.contains(s)) {
						q.add(s);
						visited.add(s);
					}
			}
		}
		
		
		return bestState.risk;
	}
	
	private List<State> next(State s) {
		List<State> states = new ArrayList<>();
		int cX = s.x;
		int cY = s.y;

		if(inBounds(cX + 1, cY)) states.add(new State(cX + 1, cY, s.risk + get(cX + 1, cY)));
		if(inBounds(cX - 1, cY)) states.add(new State(cX - 1, cY, s.risk + get(cX - 1, cY)));
		if(inBounds(cX, cY + 1)) states.add(new State(cX, cY + 1, s.risk + get(cX, cY + 1)));
		if(inBounds(cX, cY - 1)) states.add(new State(cX, cY - 1, s.risk + get(cX, cY - 1)));
		
		return states;
	}
	
	private boolean inBounds(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}
	
	private int get(int x, int y) {
		return grid[x][y];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) 
				sb.append(grid[y][x]);
		
			sb.append("\r\n");
		}
		
		return sb.toString();
	}
}
