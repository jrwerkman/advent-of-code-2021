package nl.jrwer.challenge.advent.day05;

class LineSegment {
	Coords begin;
	Coords end;
	private Direction direction = null;
	
	public LineSegment(String input) {
		String[] split = input.split("->");
		
		if(split.length != 2)
			throw new RuntimeException("Wrong input: " + input);

		begin = new Coords(split[0]);
		end = new Coords(split[1]);
	}
	
	public Direction direction() {
		if(direction != null)
			return direction;
		
		if(begin.x == end.x)
			direction = Direction.HORIZONTAL;
		else if (begin.y == end.y)
			direction = Direction.VERTICAL;
		else
			direction = Direction.DIAGONAL;
		
		return direction;
	}
	
	public boolean sameAxis() {
		return begin.sameAxis(end);
	}
}
