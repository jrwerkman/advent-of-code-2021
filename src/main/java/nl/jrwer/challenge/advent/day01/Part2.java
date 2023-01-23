package nl.jrwer.challenge.advent.day01;

public class Part2 extends Part1 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public void start() {
		System.out.println("\nStart measurement");
		int total = 0;
		
		Coords coords = new Coords(getInput());
		int currentMeasurement = coords.nextCoords();
		int nextMeasurement = 0;
		
		while(coords.hasNext()) {
			nextMeasurement = coords.nextCoords();
			
			if(currentMeasurement < nextMeasurement)
				total++;
			
			currentMeasurement = nextMeasurement;
		}
		
		System.out.println("Measured [" + total + "] larger than previous");
	}
}
