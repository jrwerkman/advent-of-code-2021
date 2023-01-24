package nl.jrwer.challenge.advent.day10;

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
		List<Line> lines = new InputLoader("input-day-10.txt").getInput();

		long start = System.currentTimeMillis();
		
		int syntaxErrorScore = 0;
		for(Line line : lines)
			syntaxErrorScore += line.isCorrupted();
		
		System.out.println("Total syntax error score: " + syntaxErrorScore);
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
