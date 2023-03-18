package nl.jrwer.challenge.advent.day15;

public class Coord {
	final int x, y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
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
