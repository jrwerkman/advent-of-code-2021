package nl.jrwer.challenge.advent.day19;

import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
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
		System.out.println(map.beacons.size());
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
