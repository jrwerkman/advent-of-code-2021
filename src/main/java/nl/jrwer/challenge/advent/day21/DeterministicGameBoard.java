package nl.jrwer.challenge.advent.day21;

import java.util.ArrayList;
import java.util.List;

public class DeterministicGameBoard {
	final int spaces;
	final int winningScore;
	
	final IDice dice;
	final List<Player> players = new ArrayList<>();
	
	Player winningPlayer = null;
	Player losingPlayer = null;
	
	public DeterministicGameBoard(int spaces, int winningScore, IDice dice) {
		this.spaces = spaces;
		this.dice = dice;
		this.winningScore = winningScore;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public long practiceGame() {
		boolean gameEnd = false;
		
		while(losingPlayer == null) {
			for(Player p : players) {
				if(gameEnd) {
					losingPlayer = p;
					System.out.println("Losing player: " + losingPlayer.id);
					break;
				}
				
				int[] values = dice.roll();
				
				int value = 0;
				for(int v : values)
					value+=v;
				
				int position = p.currentPosition + value;
				int rest = position % spaces;
				int space = rest == 0 ? 10 : rest; 
				
				p.currentPosition = space;
				p.score += space;
				
				if(p.score >= winningScore) {
					p.wins++;
					gameEnd = true;
					winningPlayer = p;
					System.out.println("Winning player: " + winningPlayer.id);
				}
			}
		}
		
		return losingPlayer.score * dice.rolled();
	}
}
