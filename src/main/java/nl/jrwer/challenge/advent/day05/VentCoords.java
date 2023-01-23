package nl.jrwer.challenge.advent.day05;

class VentCoords extends Coords {
	int weight = 1;
	int id;

	public VentCoords(int x, int y) {
		super(x, y);

		id = (x << 16) + y;
	}
}
