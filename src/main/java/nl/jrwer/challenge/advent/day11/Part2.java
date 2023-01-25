package nl.jrwer.challenge.advent.day11;

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
		OctopusGrid grid = new InputLoader("input-day-11.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println("Step 0:");
		System.out.println(grid);
		int step = grid.simultaneousFlash();
        System.out.println("Step "+step+":");
		System.out.println(grid);
		System.out.println("First step during which all octopuses flash: " + step);
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
