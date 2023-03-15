package nl.jrwer.challenge.advent.day14;

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
		Polymerization p = new InputLoader("input-day-14.txt").getInput();

		long start = System.currentTimeMillis();
		p.process(10);
		System.out.println(p.toString().length());
		System.out.println(p.result());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
