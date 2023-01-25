package nl.jrwer.challenge.advent.day11;

import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<OctopusGrid>{

	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected OctopusGrid handleLines(List<String> lines) {
		Octopus[][] grid = new Octopus[lines.size()][lines.get(0).length()];
		
		for(int y=0; y<lines.size(); y++)
			for(int x=0; x<lines.get(y).length(); x++)
				grid[y][x] = new Octopus(lines.get(y).charAt(x) - '0');
		
		return new OctopusGrid(grid);
	}




}