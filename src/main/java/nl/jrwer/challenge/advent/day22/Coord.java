package nl.jrwer.challenge.advent.day22;

import java.util.Objects;

public class Coord {
	final int x, y, z;
	
	public Coord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coord(Coord copy) {
		this.x = copy.x;
		this.y = copy.y;
		this.z = copy.z;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, x);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord) {
			Coord c = (Coord) obj;
			
			return c.x == x && c.y == y && c.z == z;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x+","+y+","+z;
	}
}
