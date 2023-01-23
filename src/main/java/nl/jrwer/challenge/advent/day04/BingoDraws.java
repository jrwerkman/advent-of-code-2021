package nl.jrwer.challenge.advent.day04;

class BingoDraws {
	int index = 0;
	private int[] numbers;
	
	public BingoDraws(String numbersLine) {
		String[] strNumbers = numbersLine.split(",");
		
		numbers = new int[strNumbers.length];
		
		for(int i=0; i<strNumbers.length; i++)
			numbers[i] = Integer.parseInt(strNumbers[i]);
	}
	
	public boolean hasNextDraw() {
		return index < numbers.length;
	}
	
	public Integer nexDraw() {
		try {
			return numbers[index];
		} finally {
			index++;
		}
	}
}