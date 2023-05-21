package nl.jrwer.challenge.advent.day19;

import java.util.List;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		List<Scanner> scanners = new InputLoader("input-day-19.txt").getInput();
		
		long start = System.currentTimeMillis();
		BeaconMap map = scanners.get(0).getMap();
		map.combine(scanners);

		for(Scanner s : scanners)
			System.out.println(s.id + ": " + s.x + "," + s.y + "," + s.z);
		
		System.out.println(largestManhattanDistance(scanners));
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	public int largestManhattanDistance(List<Scanner> scanners) {
		int largest = 0;
		
		for(Scanner a : scanners)
			for(Scanner b : scanners) {
				int md = a.manhattanDistance(b);
				if(md > largest)
					largest = md;
			}
		
		return largest;
	}}
