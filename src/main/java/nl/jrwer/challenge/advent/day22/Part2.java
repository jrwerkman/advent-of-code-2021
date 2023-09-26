package nl.jrwer.challenge.advent.day22;

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
		List<RebootStep> steps = new InputLoader("input-day-22.txt").getInput();

		long start = System.currentTimeMillis();
		
		ProcedureArea pa = new ProcedureArea(steps);
		System.out.println(pa.executeCompleteReboot());
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
