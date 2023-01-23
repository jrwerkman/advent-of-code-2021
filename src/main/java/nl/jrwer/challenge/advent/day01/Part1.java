package nl.jrwer.challenge.advent.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		List<Integer> coords = getInput();
		
		System.out.println("\nStart measurement");
		int total = 0;
		
		for(int i=1; i<coords.size(); i++) 
			if(coords.get(i-1) < coords.get(i)) 
				total++;
		
		System.out.println("Measured [" + total + "] larger than previous");
	}
	
	private Random r = new Random();

	public List<Integer> getRandomList(int size) {
		return getRandomList(200, 300, size);
	}
	
	public List<Integer> getRandomList(int min, int max, int size) {
		List<Integer> list = new ArrayList<>();
		
		int range = max - min;
		
		for(int i=0; i<size; i++)
			list.add(r.nextInt(range) + min);
		
		return list;
	}
	
	public List<Integer> getInput() {
		List<Integer> list = new ArrayList<>();
		
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-01.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null)
                list.add(Integer.parseInt(line));
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
}
