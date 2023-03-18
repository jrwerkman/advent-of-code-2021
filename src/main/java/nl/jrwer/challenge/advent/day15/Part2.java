package nl.jrwer.challenge.advent.day15;

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
		Cavern c = new InputLoader("input-day-15-example.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println(c);
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
