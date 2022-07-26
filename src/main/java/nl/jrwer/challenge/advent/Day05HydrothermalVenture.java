package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day05HydrothermalVenture {

	/*

		--- Day 5: Hydrothermal Venture ---
		
		You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque clouds, so it would be best to avoid them if possible.
		
		They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for you to review. For example:
		
		0,9 -> 5,9
		8,0 -> 0,8
		9,4 -> 3,4
		2,2 -> 2,1
		7,0 -> 7,4
		6,4 -> 2,0
		0,9 -> 2,9
		3,4 -> 1,4
		0,0 -> 8,8
		5,5 -> 8,2
		
		Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both ends. In other words:
		
		    An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
		    An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
		
		For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.
		
		So, the horizontal and vertical lines from the above list would produce the following diagram:
		
		.......1..
		..1....1..
		..1....1..
		.......1..
		.112111211
		..........
		..........
		..........
		..........
		222111....
		
		In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.
		
		To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.
		
		Consider only horizontal and vertical lines. At how many points do at least two lines overlap?

	 */
	
	public static void main(String[] args) {
		try {
			Day05HydrothermalVenture day = new Day05HydrothermalVenture();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Day05HydrothermalVenture() {
		getInput();
	}
	
	Diagram hydrothermalMap = new Diagram();
	
	public void start() {
		System.out.println("Start test");
		hydrothermalMap.createDiagram();
		System.out.println("Created map");
		
		System.out.println("number of points where at least two lines overlap: " + hydrothermalMap.overlaps());
	}

	public void getInput() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-05.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) 
            	hydrothermalMap.addLineSegment(line);
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Diagram {
		protected List<LineSegment> lineSegments = new ArrayList<>();
		protected List<VentCoords> map = new ArrayList<>();
		
		public void addLineSegment(String input) {
			lineSegments.add(new LineSegment(input));
		}
		
		public void addLineSegment(LineSegment line) {
			lineSegments.add(line);
		}
		
		public void createDiagram() {
			for(LineSegment lineSegment : lineSegments)
				addToMap(lineSegment);
		}
		
		private void addToMap(LineSegment lineSegment) {
			switch(lineSegment.direction()) {
			case DIAGONAL:
				break;
			case HORIZONTAL:
				addHorizintalToMap(lineSegment);
				break;
			case VERTICAL:
				addVerticalToMap(lineSegment);
				break;
			default:
				break;
			}
		}
		
		protected void addHorizintalToMap(LineSegment lineSegment) {
			int x = lineSegment.begin.x;
			int y1, y2;
			
			if(lineSegment.begin.y < lineSegment.end.y) {
				y1 = lineSegment.begin.y;
				y2 = lineSegment.end.y;
			} else {
				y1 = lineSegment.end.y;
				y2 = lineSegment.begin.y;
			}
					
			for(int y = y1; y<= y2; y++)
				addToMap(new VentCoords(x, y));
		}
		
		protected void addVerticalToMap(LineSegment lineSegment) {
			int x1, x2;
			int y = lineSegment.begin.y;
			
			if(lineSegment.begin.x < lineSegment.end.x) {
				x1 = lineSegment.begin.x;
				x2 = lineSegment.end.x;
			} else {
				x1 = lineSegment.end.x;
				x2 = lineSegment.begin.x;
			}
					
			for(int x = x1; x<= x2; x++)
				addToMap(new VentCoords(x, y));			
		}
		
		protected void addToMap(VentCoords coords) {
			for(VentCoords ventCoords : map) {
				if(ventCoords.compare(coords)) {
					ventCoords.weight++;
					return;
				}
			}
			
			map.add(coords);
		}
		
		public int overlaps() {
			int totalOverlaps = 0;
			
			for(VentCoords ventCoords : map)
				if(ventCoords.weight > 1)
					totalOverlaps++;
			
			return totalOverlaps;
		}
		
	}
	
	enum Direction {
		HORIZONTAL,
		VERTICAL,
		DIAGONAL
	}
	
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
	
	class VentCoords extends Coords {
		int weight = 1;

		public VentCoords(int x, int y) {
			super(x, y);
		}
	}
	
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
}
