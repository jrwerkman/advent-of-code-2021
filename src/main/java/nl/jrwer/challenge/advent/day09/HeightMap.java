package nl.jrwer.challenge.advent.day09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HeightMap {
	final byte[][] map;
	final int width, height;
	public HeightMap(byte[][] map) {
		this.map = map;
		
		this.height = map.length;
		this.width = map[0].length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++)
				sb.append(map[y][x]);
			
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	public int sumRiksLevels() {
		int sum = 0;
		
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				if(isLowPoint(x, y))
					sum += (get(x, y) + 1);
		
		return sum;
	}
	
	private boolean isLowPoint(int x, int y) {
		byte point = get(x, y);
		
		if(point < get(x - 1, y) && point < get(x, y - 1) 
				&& point < get(x + 1, y) && point < get(x, y + 1))
			return true;
		
		return false;
	}
	
	public int multiplicationLargestBasins() {
		List<Set<Coord>> basins = new ArrayList<>(); 
		List<Coord> lowPoints = getLowpoints();
		
		for(Coord lowPoint : lowPoints)
			basins.add(getBasin(lowPoint));
		
		// reverse sort
		Collections.sort(basins, new Comparator<Set<Coord>>() {
			@Override
			public int compare(Set<Coord> o1, Set<Coord> o2) {
				return o2.size() - o1.size();
			}
		});
		
		int multi = 1;
		
		for(int i=0; i<3; i++) {
			System.out.println(basins.get(i).size());
			multi *= basins.get(i).size();
		}
		
		return multi;
	}
	
	private List<Coord> getLowpoints() {
		List<Coord> lowPoints = new ArrayList<>();
		
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				if(isLowPoint(x, y))
					lowPoints.add(new Coord(x, y));
		
		return lowPoints;
	}
	
	private Set<Coord> getBasin(Coord c) {
		Set<Coord> basinElements = new HashSet<>();
		getBasinSize(c, basinElements);

		return basinElements;
	}
	
	private void getBasinSize(Coord c, Set<Coord> basinElements) {
		int point = get(c);
		
		if(point > 8 || basinElements.contains(c))
			return;
		
		basinElements.add(c);
		
		getBasinSize(c.up(), basinElements);
		getBasinSize(c.down(), basinElements);
		getBasinSize(c.left(), basinElements);
		getBasinSize(c.right(), basinElements);
	}
	
	public byte get(Coord c) {
		return get(c.x, c.y);
	}
	
	public byte get(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Byte.MAX_VALUE;
		
		return map[y][x];
	}
}
