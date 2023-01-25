package nl.jrwer.challenge.advent.day12;

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
		CaveSystem cave = new InputLoader("input-day-12.txt", true).getInput();

		long start = System.currentTimeMillis();
		System.out.println("Paths through this cave system: " + cave.numberOfPaths());
//		cave.printRoutes();
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
