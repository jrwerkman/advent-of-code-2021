package nl.jrwer.challenge.advent.day07;

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

	
	public Part2() {
		getInput();
//		crabs.loadCrabs("16,1,2,0,4,2,7,1,2,14");
	}
	
	SecondCrabs crabs = new SecondCrabs();
	
	public void start() {
		int[] result = crabs.calculateCheapestPosition();
		System.out.println("Cheapest position: " + result[0]);
		System.out.println("Fuel usage: " + result[1]);
	}

	public void getInput() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-07.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) 
            	crabs.loadCrabs(line);
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
