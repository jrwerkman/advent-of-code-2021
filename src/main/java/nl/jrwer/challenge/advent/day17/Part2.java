package nl.jrwer.challenge.advent.day17;

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
//		TargetArea target = new TargetArea(20, 30, -10, -5);
		TargetArea target = new TargetArea(277, 318, -92, -53);

		long start = System.currentTimeMillis();
		VelocityCalculator calc = new VelocityCalculator(target);
		calc.calculateVelocity();
		System.out.println(calc.possibleVelocities());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
