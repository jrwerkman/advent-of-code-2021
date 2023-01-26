package nl.jrwer.challenge.advent.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<Manual>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected Manual handleLines(List<String> lines) {
		Set<Coord> coords = new HashSet<>();
		List<Fold> folds = new ArrayList<>();

		for(String l : lines) {
			if(l.contains("fold")) {
				String[] split = l.replace("fold along", "").trim().split("=");
				folds.add(new Fold(
						Axis.get(split[0].charAt(0)), 
						Integer.parseInt(split[1])));
			} else {
				String[] split = l.split(",");
				coords.add(new Coord(
						Integer.parseInt(split[0]), 
						Integer.parseInt(split[1])));
			}
		}
		
		return new Manual(coords, folds);
	}




}