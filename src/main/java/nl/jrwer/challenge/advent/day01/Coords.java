package nl.jrwer.challenge.advent.day01;

import java.util.ArrayList;
import java.util.List;

class Coords {
	Integer position = 0;
	List<Integer> coords = new ArrayList<>();
	
	public Coords(List<Integer> coords) {
		this.coords = coords;
	}
	
	public Integer nextCoords() {
		Integer measurement = coords.get(position) 
				+ coords.get(position + 1) 
				+ coords.get(position + 2);
		
		position++;
		
		return measurement;
	}
	
	public boolean hasNext() {
		return position <= coords.size() - 3;
	}
}