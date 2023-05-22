package nl.jrwer.challenge.advent.day20;

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
		Image image = new InputLoader("input-day-20.txt").getInput();
		
		long start = System.currentTimeMillis();
		image.enhance(2);
		System.out.println(image.litPixels());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
