package nl.jrwer.challenge.advent.day05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Diagram {
	protected List<LineSegment> lineSegments = new ArrayList<>();
	protected Map<Integer, VentCoords> map = new HashMap<>();
	
	
	// could be refactored to directly convert to Map<Integer, Integer>
	// where first int is coords and second is weight
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
		if(map.containsKey(coords.id))
			map.get(coords.id).weight++;
		else
			map.put(coords.id, coords);
	}
	
	public int overlaps() {
		int totalOverlaps = 0;
		
		for(Entry<Integer, VentCoords> entry : map.entrySet())
			if(entry.getValue().weight > 1)
				totalOverlaps++;
		
		return totalOverlaps;
	}
	
}