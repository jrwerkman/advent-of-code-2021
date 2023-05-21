package nl.jrwer.challenge.advent.day19;

public class Beacon extends Coord{

	public Beacon(int x, int y, int z) {
		super(x, y, z);
	}

	public Beacon shift(int x, int y, int z) {
		return new Beacon(this.x + x, this.y + y, this.z + z);
	}
}
