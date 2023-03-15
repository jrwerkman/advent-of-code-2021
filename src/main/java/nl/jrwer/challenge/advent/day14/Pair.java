package nl.jrwer.challenge.advent.day14;

import java.util.Objects;

public class Pair {
	final char first, second;
	long times = 0L;
	
	public Pair(char first, char second) {
		this(first, second, 1L);
	}
	
	public Pair(char first, char second, long times) {
		this.first = first;
		this.second = second;
		this.times = times;
	}
	
	public void increment() {
		times++;
	}
	
	public void increment(long amount) {
		times += amount;
	}
	
	public boolean match(char first, char second) {
		return first == this.first && second == this.second;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Pair) {
			Pair p = (Pair) o;
			return p.first == first && p.second == second;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(first)
				.append(second)
				.append(" - ")
				.append(times);
		
		return sb.toString();
	}
}
