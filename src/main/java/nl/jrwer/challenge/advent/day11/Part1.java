package nl.jrwer.challenge.advent.day11;

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
		OctopusGrid grid = new InputLoader("input-day-11.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println("Step 0:");
		System.out.println(grid);
		int flashes = grid.simulateSteps(100);
		System.out.println("Step 100:");
		System.out.println(grid);
		System.out.println("Flashes are there after 100 steps: " + flashes);
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
