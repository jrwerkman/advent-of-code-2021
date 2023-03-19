package nl.jrwer.challenge.advent.day15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Cavern {
	final int[][] grid;
	final int width, height;
	
	public Cavern(int[][] grid) {
		this.grid = grid;
		
		this.height = grid.length;
		this.width = grid[0].length;
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
