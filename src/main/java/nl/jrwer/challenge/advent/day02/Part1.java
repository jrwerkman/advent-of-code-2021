package nl.jrwer.challenge.advent.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		List<Data> course = getInput();
		
		int horizontal = 0;
		int depth = 0;
		
		for(Data step : course) {
			switch(step.direction) {
			case FORWARD:
				horizontal += step.units;
				break;
			case DOWN:
				depth += step.units;
				break;
			case UP:
				depth -= step.units;
				break;
			default:
				break;
			
			}
		}
		
		System.out.println("Horizontal movement: " + horizontal);
		System.out.println("Depth movement: " + depth);
		System.out.println("Result: " + (horizontal * depth));
	}
	
	public List<Data> getInput() {
		List<Data> list = new ArrayList<>();
		
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-02.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
            	String[] split = line.split(" ");
            	
                list.add(new Data(
                			Direction.fromString(split[0]),
                			Integer.parseInt(split[1])
                		));
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
}
