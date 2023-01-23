package nl.jrwer.challenge.advent.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Diagram hydrothermalMap = new Diagram();
	
	public void start() {
		getInput();
		
		System.out.println("Start test");
		hydrothermalMap.createDiagram();
		System.out.println("Created map");
		
		System.out.println("number of points where at least two lines overlap: " + hydrothermalMap.overlaps());
	}

	public void getInput() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-05.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) 
            	hydrothermalMap.addLineSegment(line);
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
