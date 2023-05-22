package nl.jrwer.challenge.advent.day20;

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
		Image image = new InputLoader("input-day-20.txt").getInput();
		
		long start = System.currentTimeMillis();
		image.enhance(50);
		System.out.println(image.litPixels());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
