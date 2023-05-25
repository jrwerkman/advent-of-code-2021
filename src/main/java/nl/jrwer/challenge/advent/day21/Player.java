package nl.jrwer.challenge.advent.day21;

public class Player {
	final int id;
	final int startingPostion;
	
	int score = 0;
	int currentPosition;
	long wins = 0;
	
	public Player(int id, int startingPosition) {
		this.id = id;
		this.startingPostion = startingPosition;
		this.currentPosition = startingPosition;
	}
}
