package nl.jrwer.challenge.advent.day13;

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
		Manual manual = new InputLoader("input-day-13.txt").getInput();

		long start = System.currentTimeMillis();
		manual.foldAll();
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
