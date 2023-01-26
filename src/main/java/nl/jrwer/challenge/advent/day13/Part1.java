package nl.jrwer.challenge.advent.day13;

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
		Manual manual = new InputLoader("input-day-13.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println(manual.foldOnce());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
