package nl.jrwer.challenge.advent.day12;

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
		CaveSystem cave = new InputLoader("input-day-12.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println("Paths through this cave system: " + cave.numberOfPaths());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
