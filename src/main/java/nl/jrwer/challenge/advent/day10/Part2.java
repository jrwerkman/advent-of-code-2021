package nl.jrwer.challenge.advent.day10;

import java.util.ArrayList;
import java.util.Collections;
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
		List<Line> lines = new InputLoader("input-day-10.txt").getInput();

		long start = System.currentTimeMillis();
		
		List<Long> scores = new ArrayList<>();
		
		for(Line line : lines)
			if(line.isCorrupted() == 0)
				scores.add(line.repair());

		Collections.sort(scores);
		int half = (scores.size() / 2);
		
		System.out.println("Middle score is: " + scores.get(half));
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
