package nl.jrwer.challenge.advent.day11;

public class Octopus {
	private int energyLevel;
	private boolean flashed = false;
	
	public Octopus(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
	public boolean energyUp() {
		if(flashed)
			return false;
		
		energyLevel++;
		
		if(energyLevel > 9) {
			energyLevel = 0;
			flashed = true;
			return true;
		}
		
		return false;
	}
	
	public void reset() {
		flashed = false;
	}
	
	@Override
	public String toString() {
		return String.valueOf(energyLevel);
	}
}
