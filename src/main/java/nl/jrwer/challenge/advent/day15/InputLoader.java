package nl.jrwer.challenge.advent.day15;

import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoader extends AllLinesInputLoader<Cavern>{
    
    public InputLoader(String file) {
    	super(file);
    }

	@Override
	protected Cavern handleLines(List<String> lines) {
		int[][] grid = new int[lines.size()][lines.get(0).length()];

		for(int i=0; i<lines.size(); i++) {
			String line = lines.get(i);

			for(int j=0; j<line.length(); j++)
				grid[i][j] = line.charAt(j) - '0';
		}
		
		return new Cavern(grid);
	}
}