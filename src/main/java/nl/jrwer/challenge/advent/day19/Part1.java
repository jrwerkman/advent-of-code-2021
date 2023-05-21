package nl.jrwer.challenge.advent.day19;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

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

		Queue<BeaconMap> q = new ArrayDeque<>();
		for(int i=1; i<scanners.size(); i++)
			q.add(scanners.get(i).getMap());

		while(!q.isEmpty()) {
			BeaconMap current = q.poll();

			// retry if nog match
			if(!map.combine(current))
				q.add(current);
		}

		System.out.println(map.beacons.size());
		
//		for(Beacon b : map.beacons)
//			System.out.println(b);
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
