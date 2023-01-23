package nl.jrwer.challenge.advent.day06;

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

	
	public Part1() {
		getInput();
	}
	
	LanternFishSchool school = new LanternFishSchool();
	
	public void start() {
		int size = school.breed(80);
		System.out.println("Size of school: " + size);
	}

	public void getInput() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-06.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) 
            	school.addFish(line);
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
