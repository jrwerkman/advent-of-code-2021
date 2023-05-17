package nl.jrwer.challenge.advent.day18;

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
		List<String> lines = new InputLoader("input-day-18.txt").getInput();
		long start = System.currentTimeMillis();

		SnailFishNumber sfn = new SnailFishNumber(lines.get(0));
		
		for(int i=1; i<lines.size(); i++) {
			SnailFishNumber sfnAdd = new SnailFishNumber(lines.get(i));
			sfn = SnailFishNumber.addition(sfn, sfnAdd);
			sfn.reduce();
		}
		
		System.out.println(sfn);
		System.out.println(sfn.magnitude());
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
