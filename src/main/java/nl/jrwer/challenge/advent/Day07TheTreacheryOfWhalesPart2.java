package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day07TheTreacheryOfWhalesPart2 {

	/*

		--- Part Two ---
		
		The crabs don't seem interested in your proposed solution. Perhaps you misunderstand crab engineering?
		
		As it turns out, crab submarine engines don't burn fuel at a constant rate. Instead, each change of 1 step in horizontal position costs 1 more unit of fuel than the last: the first step costs 1, the second step costs 2, the third step costs 3, and so on.
		
		As each crab moves, moving further becomes more expensive. This changes the best horizontal position to align them all on; in the example above, this becomes 5:
		
		    Move from 16 to 5: 66 fuel
		    Move from 1 to 5: 10 fuel
		    Move from 2 to 5: 6 fuel
		    Move from 0 to 5: 15 fuel
		    Move from 4 to 5: 1 fuel
		    Move from 2 to 5: 6 fuel
		    Move from 7 to 5: 3 fuel
		    Move from 1 to 5: 10 fuel
		    Move from 2 to 5: 6 fuel
		    Move from 14 to 5: 45 fuel
		
		This costs a total of 168 fuel. This is the new cheapest possible outcome; the old alignment position (2) now costs 206 fuel instead.
		
		Determine the horizontal position that the crabs can align to using the least fuel possible so they can make you an escape route! How much fuel must they spend to align to that position?

	 */
	
	public static void main(String[] args) {
		try {
			Day07TheTreacheryOfWhalesPart2 day = new Day07TheTreacheryOfWhalesPart2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Day07TheTreacheryOfWhalesPart2() {
		getInput();
//		crabs.loadCrabs("16,1,2,0,4,2,7,1,2,14");
	}
	
	Crabs crabs = new Crabs();
	
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
	
	class Crabs {
		int[] crabPositions;
		int lowestPosition;
		int highestPosition;
		
		public void loadCrabs(String input) {
			String[] split = input.split(",");
			
			crabPositions = new int[split.length];
			
			for(int i=0; i<split.length; i++) {
				int crabPosition = Integer.parseInt(split[i]);
				crabPositions[i] = crabPosition;
				
				if(i == 0) {
					lowestPosition = crabPosition;
					highestPosition = crabPosition;
					
					continue;
				}
				
				if(crabPosition < lowestPosition)
					lowestPosition = crabPosition;
				else if(crabPosition > highestPosition)
					highestPosition = crabPosition;
			}
		}
		
		public int[] calculateCheapestPosition() {
			int cheapestPosition = 0;
			int cheapestFuel = Integer.MAX_VALUE;
			
			for(int i=lowestPosition; i< highestPosition; i++) {
				int newFuel = fuelUsageOnPosition(i);
				
				if(newFuel < cheapestFuel) {
					cheapestFuel = newFuel;
					cheapestPosition = i;
				}
				
//				System.out.println("position: " + i + " - fuel: " + newFuel);
			}
			
			return new int[] {cheapestPosition, cheapestFuel};
		}
		
		public int fuelUsageOnPosition(int targetPosition) {
			int fuel = 0;
			for(int crabPosition : crabPositions) {
				int steps = 0;
				
				if(targetPosition < crabPosition)
					steps = crabPosition - targetPosition;
				else if(targetPosition > crabPosition)
					steps = targetPosition - crabPosition;
				
				fuel += getFuelConsumption(steps);
			}
			
			return fuel;
		}
		
		private int getFuelConsumption(float steps) {
			return (int) (steps * (0.5 + (steps * 0.5)));
		}
	}
}
