package nl.jrwer.challenge.advent.day21;

public class Dice {
	private final int positions;
	private int currentNumber = 0;
	int rolled = 0;
	
	public Dice() {
		this(100);
	}
	
	public Dice(int positions) {
		this.positions = positions;
	}
	
	public int roll(int times) {
		int value = 0;
		
		for(int i=0; i<times; i++)
			value += roll();
		
		return value;
	}	
	
	public int roll() {
		rolled++;
		currentNumber++;
		
		if(currentNumber > positions)
			currentNumber = 1;
		
		return currentNumber;
	}
}
