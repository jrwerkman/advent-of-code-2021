package nl.jrwer.challenge.advent.day16;

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
		Decoder d = new InputLoader("input-day-16-example.txt").getInput();

		long start = System.currentTimeMillis();


		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
