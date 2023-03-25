package nl.jrwer.challenge.advent.day16;

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
		Decoder d = new InputLoader("input-day-16-example.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println(d.sumVersionNumbers());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
