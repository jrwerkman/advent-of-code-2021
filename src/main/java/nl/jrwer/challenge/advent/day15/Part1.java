package nl.jrwer.challenge.advent.day15;

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
		Cavern c = new InputLoader("input-day-15-example.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println(c.findSafestRoute());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
		// 421 is to high
	}
}
