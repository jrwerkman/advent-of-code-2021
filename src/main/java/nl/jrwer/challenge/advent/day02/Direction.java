package nl.jrwer.challenge.advent.day02;

public enum Direction {
	FORWARD,
	UP,
	DOWN;
	
	public static Direction fromString(String direction) {
		direction = direction.toLowerCase();
		
		switch(direction) {
		case "forward":
			return FORWARD;
		case "up":
			return UP;
		case "down":
			return DOWN;
		default:
			return null;
		}
		
	}
}