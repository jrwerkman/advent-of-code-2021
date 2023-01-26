package nl.jrwer.challenge.advent.day13;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Manual {
	private final Set<Coord> initialCoords;
	private final List<Fold> folds;
	
	public Manual(Set<Coord> coords, List<Fold> folds) {
		this.initialCoords = coords;
		this.folds = folds;
	}
	
	public void foldAll() {
		Set<Coord> currentCoord = initialCoords;

		for(Fold f: folds) 
			currentCoord = fold(currentCoord, f);
		
		System.out.println(toString(currentCoord));
	}
	
	public int foldOnce() {
		return fold(initialCoords, folds.get(0)).size();
	}
	
	private Set<Coord> fold(Set<Coord> coords, Fold fold) {
		return fold.axis == Axis.Y ? foldY(coords, fold.position) : foldX(coords, fold.position);
	}
	
	private Set<Coord> foldY(Set<Coord> coords, int position) {
		Set<Coord> newCoords = new HashSet<>();
		
		for(Coord c : coords) {
			if(c.y > position)
				newCoords.add(new Coord(c.x, position - (c.y - position)));
			else if(c.y < position)
				newCoords.add(c);
		}
		
		return newCoords;
	}

	private Set<Coord> foldX(Set<Coord> coords, int position) {
		Set<Coord> newCoords = new HashSet<>();

		for(Coord c : coords) {
			if(c.x > position)
				newCoords.add(new Coord(position - (c.x - position), c.y));
			else if(c.x < position)
				newCoords.add(c);
		}		

		return newCoords;
	}
	
	public String toString(Set<Coord> coords) {
		StringBuilder sb = new StringBuilder();
		
		int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
		for(Coord c : coords) {
			if(c.x < minX)
				minX = c.x;
			if(c.x > maxX)
				maxX = c.x;
			if(c.y < minY)
				minY = c.y;
			if(c.y > maxY)
				maxY = c.y;
		}
		
		for(int y=minY; y<=maxY; y++) {
			for(int x=minX; x<=maxX; x++)
				sb.append(coords.contains(new Coord(x,y)) ? '#' : '.');

			sb.append('\n');
		}
		
		
		return sb.toString();
	}
}
