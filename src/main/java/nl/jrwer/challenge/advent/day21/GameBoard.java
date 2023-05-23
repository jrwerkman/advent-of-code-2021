package nl.jrwer.challenge.advent.day21;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	final int spaces;
	
	final Dice dice;
	final List<Player> players = new ArrayList<>();
	
	Player winningPlayer = null;
	Player losingPlayer = null;
	
	public GameBoard() {
		this(10, 100);
	}

	public GameBoard(int spaces, int diceSides) {
		this.spaces = spaces;
		this.dice = new Dice(diceSides);
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public int practiceGame() {
		boolean gameEnd = false;
		
		while(losingPlayer == null) {
			for(Player p : players) {
				if(gameEnd) {
					losingPlayer = p;
					System.out.println("Losing player: " + losingPlayer.id);
					break;
				}
				
				int value = dice.roll(3);
				int position = p.currentPosition + value;
				int rest = position % spaces;
				int space = rest == 0 ? 10 : rest; 
				
				p.currentPosition = space;
				p.score += space;
				
				if(p.score >= 1000) {
					p.wins = true;
					gameEnd = true;
					winningPlayer = p;
					System.out.println("Winning player: " + winningPlayer.id);
				}
			}
		}
		
		return losingPlayer.score * dice.rolled;
	}
}
