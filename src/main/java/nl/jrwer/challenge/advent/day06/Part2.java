package nl.jrwer.challenge.advent.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	MegaSchool school = new MegaSchool();
	
	public Part2() {
		getInput();
	}

	public void start() {
		long size = school.breed(256);
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
