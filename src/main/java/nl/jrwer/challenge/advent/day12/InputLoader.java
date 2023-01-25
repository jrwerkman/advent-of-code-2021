package nl.jrwer.challenge.advent.day12;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<CaveSystem>{
    private final boolean part2;
    
    public InputLoader(String file) {
        this(file, false);
    }

    public InputLoader(String file, boolean part2) {
        super(file);
        
        this.part2 = part2;
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
		
		return new CaveSystem(paths, part2);
	}




}