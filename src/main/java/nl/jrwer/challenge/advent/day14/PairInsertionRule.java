package nl.jrwer.challenge.advent.day14;

public class PairInsertionRule {
	final char first, second, between;
	long times = 0;
	long tempTimes = 0;
	
	public PairInsertionRule(char first, char second, char between) {
		this.first = first;
		this.second = second;
		this.between = between;
	}
	
	public boolean match(char first, char second) {
		return first == this.first && second == this.second;
	}
	
	public char between() {
		return between;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(first)
				.append(between)
				.append(second)
				.append(" - ")
				.append(times);
		
		return sb.toString();
	}
}
