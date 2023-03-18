package nl.jrwer.challenge.advent.day15;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Cavern {
	final int[][] grid;
	final int width, height;
	
	final Coord start, finish;
	State bestState;
	
	public Cavern(int[][] grid) {
		this.grid = grid;
		
		this.height = grid.length;
		this.width = grid[0].length;
		
		this.start = new Coord(0,0);
		this.finish = new Coord(width - 1, height - 1);
	}
	
	public int findSafestRoute() {
		this.bestState = new State(start, Integer.MAX_VALUE, 0);
		State startState = new State(start, get(0, 0), 0);
		Queue<State> q = new ArrayDeque<State>();
		Set<State> visited = new HashSet<>();
		
		visited.add(startState);
		q.add(startState);
		
		System.out.println(finish);
		
		while(!q.isEmpty()) {
			State nextState = q.poll();

			if(visited.contains(nextState))
				continue;
			
			if(nextState.currentCoord.equals(finish) && bestState.risk > nextState.risk)
				bestState = nextState;
			
			if(!nextState.currentCoord.equals(finish)) {
				List<Coord> next = next(nextState.currentCoord);

				for(Coord c : next) {
					if(inBounds(c)) {
						State s = new State(c, 
								nextState.risk + get(c), 
								nextState.steps + 1); 
						
						q.add(s);
						visited.add(s);
					}
				}
			}
		}
		
		
		return bestState.risk;
	}
	
	private List<Coord> next(Coord currentCoord) {
		int cX = currentCoord.x;
		int cY = currentCoord.y;

		return List.of(
				new Coord(cX + 1, cY),
				new Coord(cX - 1, cY),
				new Coord(cX, cY + 1),
				new Coord(cX, cY - 1));
	}
	
	private boolean inBounds(Coord c) {
		return c.x >= 0 && c.x < width && c.y >= 0 && c.y < height;
	}
	
	private int get(Coord c) {
		return get(c.x, c.y);
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
