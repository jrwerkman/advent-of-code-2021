package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day06LanternfishPart2 {

	/*

		--- Part Two ---
		
		Suppose the lanternfish live forever and have unlimited food and space. Would they take over the entire ocean?
		
		After 256 days in the example above, there would be a total of 26984457539 lanternfish!
		
		How many lanternfish would there be after 256 days?

	 */
	
	public static void main(String[] args) {
		try {
			Day06LanternfishPart2 day = new Day06LanternfishPart2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	MegaSchool school = new MegaSchool();
	
	public Day06LanternfishPart2() {
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
	
	class MegaSchool {
		long[] school = new long[9]; 
		
		public void addFish(String input) {
			System.out.println(input);
			String[] split = input.split(",");
			
			for(String strDay : split) {
				int day = Integer.parseInt(strDay);
				
				school[day]++;
			}
		}
		
		public long breed(int days) {
			for(int i=0; i<days; i++) {
				long nextFish = 0;
				long currentFish = school[school.length - 1];
				
				for(int j=(school.length - 1); j>=0; j--) {
					
					if(j == 0) {
						school[school.length - 1] = currentFish;
						school[school.length - 3] += currentFish; 
					} else { 
						nextFish = school[j - 1];
						school[j - 1] = currentFish;
						currentFish = nextFish;
					}
				}
				
//				System.out.println(getSchoolSize());
			}
			
			return getSchoolSize();
		}
		
		public long getSchoolSize() {
			long size = 0;
			
			for(long fish : school)
				size += fish;
			
			return size;
		}
	}
}
