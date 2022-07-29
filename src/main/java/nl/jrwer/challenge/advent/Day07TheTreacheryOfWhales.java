package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day07TheTreacheryOfWhales {

	/*

		--- Day 7: The Treachery of Whales ---
		
		A giant whale has decided your submarine is its next meal, and it's much faster than you are. There's nowhere to run!
		
		Suddenly, a swarm of crabs (each in its own tiny submarine - it's too deep for them otherwise) zooms in to rescue you! They seem to be preparing to blast a hole in the ocean floor; sensors indicate a massive underground cave system just beyond where they're aiming!
		
		The crab submarines all need to be aligned before they'll have enough power to blast a large enough hole for your submarine to get through. However, it doesn't look like they'll be aligned before the whale catches you! Maybe you can help?
		
		There's one major catch - crab submarines can only move horizontally.
		
		You quickly make a list of the horizontal position of each crab (your puzzle input). Crab submarines have limited fuel, so you need to find a way to make all of their horizontal positions match while requiring them to spend as little fuel as possible.
		
		For example, consider the following horizontal positions:
		
		16,1,2,0,4,2,7,1,2,14
		
		This means there's a crab with horizontal position 16, a crab with horizontal position 1, and so on.
		
		Each change of 1 step in horizontal position of a single crab costs 1 fuel. You could choose any horizontal position to align them all on, but the one that costs the least fuel is horizontal position 2:
		
		    Move from 16 to 2: 14 fuel
		    Move from 1 to 2: 1 fuel
		    Move from 2 to 2: 0 fuel
		    Move from 0 to 2: 2 fuel
		    Move from 4 to 2: 2 fuel
		    Move from 2 to 2: 0 fuel
		    Move from 7 to 2: 5 fuel
		    Move from 1 to 2: 1 fuel
		    Move from 2 to 2: 0 fuel
		    Move from 14 to 2: 12 fuel
		
		This costs a total of 37 fuel. This is the cheapest possible outcome; more expensive outcomes include aligning at position 1 (41 fuel), position 3 (39 fuel), or position 10 (71 fuel).
		
		Determine the horizontal position that the crabs can align to using the least fuel possible. How much fuel must they spend to align to that position?


	 */
	
	public static void main(String[] args) {
		try {
			Day07TheTreacheryOfWhales day = new Day07TheTreacheryOfWhales();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Day07TheTreacheryOfWhales() {
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
				// set highest abd lowest
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
			
//			System.out.println("Cheapest position: " + cheapestPosition);
//			System.out.println("Fuel usage: " + fuel);
			
			return new int[] {cheapestPosition, cheapestFuel};
		}
		
		public int fuelUsageOnPosition(int targetPosition) {
			int fuel = 0;
			for(int crabPosition : crabPositions) {
				if(targetPosition < crabPosition)
					fuel += (crabPosition - targetPosition);
				else if(targetPosition > crabPosition)
					fuel += (targetPosition - crabPosition);
			}
			
			return fuel;
		}
	}
}
