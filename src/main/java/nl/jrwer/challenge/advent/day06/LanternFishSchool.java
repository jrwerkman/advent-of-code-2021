package nl.jrwer.challenge.advent.day06;

import java.util.ArrayList;
import java.util.List;

class LanternFishSchool {
	List<LanternFish> fish = new ArrayList<>();
	int daysPassed = 0;
	
	public void addFish(String input) {
		addFish(input.split(","));
	}

	public void addFish(String[] fishArray) {
		for(String fishDay : fishArray) 
			fish.add(new LanternFish(Integer.parseInt(fishDay)));
	}
	
	public int breed(int days) {
		for(int i=0; i<days; i++) {
			List<LanternFish> babyFish = new ArrayList<>();
			
			for(LanternFish singleFish : fish) {
				LanternFish newFish = singleFish.nextDay();
				
				if(newFish != null)
					babyFish.add(newFish);
			}
			
			fish.addAll(babyFish);
			daysPassed++;
			
			System.out.println("Day "+daysPassed+": " + fish.size());
		}
		
		return fish.size();
	}
}
