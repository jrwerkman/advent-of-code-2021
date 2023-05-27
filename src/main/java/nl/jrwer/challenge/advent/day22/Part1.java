package nl.jrwer.challenge.advent.day22;

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
		List<RebootStep> steps = new InputLoader("input-day-22.txt").getInput();

		long start = System.currentTimeMillis();
		ProcedureArea pa = new ProcedureArea(new Coord(-50,-50,-50), new Coord(50, 50, 50));
		System.out.println(pa.executeReboot(steps));
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
