package nl.jrwer.challenge.advent.day17;

public class Velocity {
	final int x, y;
	
	public Velocity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return x << 8 | y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Velocity) {
			Velocity v = (Velocity) o;
			
			return v.x == x && v.y == y;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x+","+y;
	}
}
