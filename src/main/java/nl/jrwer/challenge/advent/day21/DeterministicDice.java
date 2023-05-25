package nl.jrwer.challenge.advent.day21;

public class DeterministicDice implements IDice {
	private final int sides;
	private int currentNumber = 0;
	long rolled = 0;
	
	public DeterministicDice() {
		this(100);
	}
	
	public DeterministicDice(int sides) {
		this.sides = sides;
	}
	
	@Override
	public int[] roll() {
		int[] results = new int[3];
		
		for(int i=0; i<3; i++)
			results[i] = oneRoll();
		
		return results;
	}	
	
	public int oneRoll() {
		rolled++;
		currentNumber++;
		
		if(currentNumber > sides)
			currentNumber = 1;
		
		return currentNumber;
	}

	@Override
	public long rolled() {
		return rolled;
	}

	@Override
	public int sides() {
		return sides;
	}
}
