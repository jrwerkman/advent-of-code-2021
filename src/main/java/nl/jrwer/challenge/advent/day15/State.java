package nl.jrwer.challenge.advent.day15;

import java.util.Objects;

public class State {
	final int risk;
	final int steps;
	final Coord currentCoord;
	
	public State() {
		this.risk = 0;
		this.steps = 0;
		this.currentCoord = new Coord(0, 0);
	}

	public State(Coord currentCoord, int risk, int steps) {
		this.risk = risk;
		this.steps = steps;
		this.currentCoord = currentCoord;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof State) {
			State s = (State) obj;
			
			return s.risk == risk && s.steps == steps && s.currentCoord.equals(currentCoord);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(currentCoord, steps, risk);
	}
}
