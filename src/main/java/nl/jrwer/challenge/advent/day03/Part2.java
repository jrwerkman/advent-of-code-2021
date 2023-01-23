package nl.jrwer.challenge.advent.day03;

import java.util.List;

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
		List<Data> dataList = getInput();
		System.out.println("Complete list items, size: " + dataList.size());
		
		LifeSupportRating rating = new LifeSupportRating(dataList);
		Data oxygenResult = rating.getOxygenGeneratorRating();
		Data co2Result = rating.getCO2ScrubberRating();

		System.out.println("oxygenResult: " + oxygenResult.toString());
		System.out.println("co2Result   : " + co2Result.toString());
		System.out.println("Final result: " + (oxygenResult.toInteger() * co2Result.toInteger()));
	}
}
