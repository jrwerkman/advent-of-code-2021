package nl.jrwer.challenge.advent.day02;

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
		List<Data> course = getInput();
		
		int horizontal = 0;
		int depth = 0;
		int aim = 0;
		
		for(Data step : course) {
			switch(step.direction) {
			case FORWARD:
				horizontal += step.units;
				if(aim != 0)
					depth += (aim * step.units);
				break;
			case DOWN:
				aim += step.units;
				break;
			case UP:
				aim -= step.units;
				break;
			default:
				break;
			
			}
		}
		
		System.out.println("Horizontal movement: " + horizontal);
		System.out.println("Depth movement: " + depth);
		System.out.println("Result: " + (horizontal * depth));
	}
}
