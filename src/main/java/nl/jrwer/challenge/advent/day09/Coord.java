package nl.jrwer.challenge.advent.day09;

import java.util.List;

public class Coord {
	final int x, y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord up() {
		return new Coord(x, y - 1);
	}
	
	public Coord down() {
		return new Coord(x, y + 1);
	}
	
	public Coord left() {
		return new Coord(x - 1, y);
	}
	
	public Coord right() {
		return new Coord(x + 1, y);
	}
	
	public List<Coord> adjoining() {
		return List.of(up(), down(), left(), right());
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof Coord) {
			Coord c = (Coord) obj;
			
			return x == c.x && y == c.y;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return x << 8 | y;
	}
	
	@Override
	public String toString() {
		return x + "," + y; 
	}
}
