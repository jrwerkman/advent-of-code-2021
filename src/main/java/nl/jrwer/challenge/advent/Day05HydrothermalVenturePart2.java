package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day05HydrothermalVenturePart2 extends Day05HydrothermalVenture {

	/*

		--- Part Two ---
		
		Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture; you need to also consider diagonal lines.
		
		Because of the limits of the hydrothermal vent mapping system, the lines in your list will only ever be horizontal, vertical, or a diagonal line at exactly 45 degrees. In other words:
		
		    An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
		    An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.
		
		Considering all lines from the above example would now produce the following diagram:
		
		1.1....11.
		.111...2..
		..2.1.111.
		...1.2.2..
		.112313211
		...1.2....
		..1...1...
		.1.....1..
		1.......1.
		222111....
		
		You still need to determine the number of points where at least two lines overlap. In the above example, this is still anywhere in the diagram with a 2 or larger - now a total of 12 points.
		
		Consider all of the lines. At how many points do at least two lines overlap?

	 */
	
	public static void main(String[] args) {
		try {
			Day05HydrothermalVenturePart2 day = new Day05HydrothermalVenturePart2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Day05HydrothermalVenturePart2() {
	}
	
	CompleteDiagram hydrothermalMap = new CompleteDiagram();
	
	public void start() {
		getInput();
		
		System.out.println("Start test");
		hydrothermalMap.createDiagram();
		System.out.println("Created map");
		
		System.out.println("number of points where at least two lines overlap: " + hydrothermalMap.overlaps());
	}
	
	@Override
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
	
	class CompleteDiagram extends Diagram {
		@Override
		public void createDiagram() {
			for(LineSegment lineSegment : lineSegments)
				addToMap(lineSegment);
		}
		
		protected void addToMap(LineSegment lineSegment) {
			switch(lineSegment.direction()) {
			case DIAGONAL:
				addDiagonalToMap(lineSegment);
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
		
		private void addDiagonalToMap(LineSegment lineSegment) {
//			System.out.println(lineSegment.begin.x +"," + lineSegment.begin.y 
//					+ " -> " + lineSegment.end.x + "," + lineSegment.end.y);
			
			int x1 = lineSegment.begin.x;
			int y1 = lineSegment.begin.y;
			int x2 = lineSegment.end.x;
			int y2 = lineSegment.end.y;
			
			if(x1 < x2 && y1 < y2)
				addLeftUpToRightDown(x1, x2, y1);

			if(x1 > x2 && y1 > y2)
				addLeftUpToRightDown(x2, x1, y2);
			
			if(x1 < x2 && y1 > y2)
				addLeftDownToRightUp(x1, x2, y2);
			
			if(x1 > x2 && y1 < y2)
				addLeftDownToRightUp(x2, x1, y1);
		}
		
		/**
		 * Or RightDownToLeftUp
		 * 
		 * @param leftTopX
		 * @param bottomRightX
		 * @param leftTopY
		 */
		private void addLeftUpToRightDown(int leftTopX, int bottomRightX, int leftTopY) {
			for(int i=0; i<=(bottomRightX-leftTopX); i++)
				addToMap(new VentCoords(leftTopX + i, leftTopY + i));
		}

		/**
		 * Or RightUpToLeftDown
		 *  
		 * @param leftBottomX
		 * @param rightTopX
		 * @param leftBottomY
		 */
		private void addLeftDownToRightUp(int leftBottomX, int rightTopX, int leftBottomY) {
			for(int i=rightTopX-leftBottomX; i>=0; i--)
				addToMap(new VentCoords(rightTopX - i, leftBottomY + i));
		}
	}
}
