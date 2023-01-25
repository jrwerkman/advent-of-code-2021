package nl.jrwer.challenge.advent.day12;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<CaveSystem>{

	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected CaveSystem handleLines(List<String> lines) {
		List<Tunnel> paths = new ArrayList<>();

		for(String l : lines) {
		    String[] split = l.split("-");
		    
		    paths.add(new Tunnel(
		            new Cave(split[0]), 
		            new Cave(split[1])));
		}
		
		return new CaveSystem(paths);
	}




}