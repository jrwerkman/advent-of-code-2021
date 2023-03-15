package nl.jrwer.challenge.advent.day14;

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
		PolymerizationBig p = new InputLoaderBig("input-day-14.txt").getInput();

		long start = System.currentTimeMillis();
		p.process(40);
		System.out.println(p.result());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
