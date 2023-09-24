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
		List<RebootStep> steps = new InputLoader("input-day-22-example2.txt").getInput();

		long start = System.currentTimeMillis();
		
		ProcedureArea pa = new ProcedureArea(steps,
				new Coord(-50,-50,-50), new Coord(50, 50, 50));
		System.out.println(pa.executeCompleteReboot());
		System.out.println("2758514936282235");
		
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
