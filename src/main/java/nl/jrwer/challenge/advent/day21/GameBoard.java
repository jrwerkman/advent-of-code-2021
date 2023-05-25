package nl.jrwer.challenge.advent.day21;

public class GameBoard {
	private static final long[][] OUTCOMES = new long[][] {
			{1, 3}, // 1x 3 --> dices 1, 1, 1
			{3, 4}, // 3x 4 --> dices 2,1 1 or 1,2,1 or 1,1,2
			{6, 5}, // 6x 5 --> ect..
			{7, 6}, // 7x 6 
			{6, 7}, // 6x 7
			{3, 8}, // 3x 8
			{1, 9}  // 1x 9
	};
		
	private static final int UNIVERSES = 0;
	private static final int SCORE = 1;
	private static final int POS = 0;
	private static final int BOARD_SPACES = 10;
	private static final int WINNING_SCORE = 21;
	
	
	final Player a;
	final Player b;
	
	public GameBoard(Player playerA, Player playerB) {
		this.a = playerA;
		this.b = playerB;
	}

	public long play() {
		play(a.startingPostion, b.startingPostion, 0, 0, true, 1);
		
		System.out.println("Player 1:  " + a.wins);
		System.out.println("Player 2:  " + b.wins);

		return a.wins > b.wins ? a.wins : b.wins;
	}
	
	public void play(int posA, int posB, int scoreA, int scoreB, boolean aTurn, long universes) {
		for(long[] rolled : OUTCOMES) {
			long nextUniverses = universes * rolled[UNIVERSES];
			
			if(aTurn) {
				int[] next = nextRoll(posA, scoreA, (int)rolled[SCORE]);
				
				if(next[SCORE] >= WINNING_SCORE)
					a.wins += nextUniverses;
				else
					play(next[POS], posB, next[SCORE], scoreB, false, nextUniverses);
			} else {
				int[] next = nextRoll(posB, scoreB, (int)rolled[SCORE]);

				if(next[SCORE] >= WINNING_SCORE)
					b.wins += nextUniverses;
				else
					play(posA, next[POS], scoreA, next[SCORE], true, nextUniverses);
			}
		}
	}
	
	public int[] nextRoll(int pos, int score, int rolled) {
		int nextPos = nextPosition(pos, rolled);
		int nextScore = nextScore(nextPos, score);
		
		return new int[] {nextPos, nextScore};
	}
	
	public int nextScore (int nextPos, int score) {
		return score + nextPos;
	}
	
	public int nextPosition(int pos, int rolled) {
		int position = pos + rolled;

		if(position <= 10)
			return position;
		
		int rest = position % BOARD_SPACES;
		return rest == 0 ? 10 : rest;
	}
}
