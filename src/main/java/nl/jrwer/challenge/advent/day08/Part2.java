package nl.jrwer.challenge.advent.day08;

import java.util.List;

public class Part2 extends Part1 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		List<String> input = getInput();
//		List<String> input = getExampleInput();

		long total = 0;
		
		for(String entry : input) {
			CompleteDataEntry dataEntry = new CompleteDataEntry(entry);
			
			total += dataEntry.getCode();
		}
		
		System.out.println("Total is: " + total);
	}
}
