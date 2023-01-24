package nl.jrwer.challenge.advent.day09;

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
		HeightMap map = new InputLoader("input-day-09.txt").getInput();

		long start = System.currentTimeMillis();
//		System.out.println(map);
		System.out.println("The sum of the risk levels: " + map.sumRiksLevels());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
