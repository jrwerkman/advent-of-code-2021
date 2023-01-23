package nl.jrwer.challenge.advent.day05;

class Coords {
	int x;
	int y;
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coords(String input) {
		String[] split = input.split(",");
		
		if(split.length != 2)
			throw new RuntimeException("Wrong input: " + input);
		
		x = Integer.parseInt(split[0].trim());
		y = Integer.parseInt(split[1].trim());
	}
	
	public boolean sameAxis(Coords compare) {
		return x == compare.x || y == compare.y;
	}
	
	public boolean compare(Coords coords) {
		return x == coords.x && y == coords.y;
	}
}