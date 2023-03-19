package nl.jrwer.challenge.advent.day15;

import java.util.Objects;

public class State {
	final int risk, x, y;
	
	public State() {
		this.risk = Integer.MAX_VALUE;
		this.x = 0;
		this.y = 0;
	}

	public State(int x, int y, int risk) {
		this.risk = risk;
		this.x = x;
		this.y = y;
	}
	
	public boolean isCoord(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof State) {
			State s = (State) obj;
			
			return s.risk == risk && s.x == x && s.y == y;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, risk);
	}
}
