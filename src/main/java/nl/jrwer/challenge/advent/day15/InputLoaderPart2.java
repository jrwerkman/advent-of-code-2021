package nl.jrwer.challenge.advent.day15;

import java.util.List;

import nl.jrwer.challenge.advent.input.AllLinesInputLoader;

class InputLoaderPart2 extends AllLinesInputLoader<Cavern>{
    private int width, height;
	
    public InputLoaderPart2(String file) {
    	super(file);
    }

	@Override
	protected Cavern handleLines(List<String> lines) {
		int[][] initialGrid = getInitialGrid(lines);
		width = initialGrid.length;
		height = initialGrid[0].length;
		
		int[][] superGrid = new int[width * 5][height * 5]; 
		
		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
				addSubGrid(superGrid, initialGrid, x, y);
		
		return new Cavern(superGrid);
	}
	
	private void addSubGrid(int[][] superGrid, int[][] initialGrid, int rX, int rY) {
		for(int y=0; y<width; y++)
			for(int x=0; x<height; x++)
				superGrid[(rY * width)+y][(rX * height)+x] = 
						getValue(initialGrid[y][x], rX, rY);
	}
	
	private int getValue(int base, int rX, int rY) {
		int newValue = base + rX + rY;
		
		return newValue > 9 ? newValue - 9 : newValue;
	}
	
	private int[][] getInitialGrid(List<String> lines) {
		int[][] grid = new int[lines.size()][lines.get(0).length()];

		for(int i=0; i<lines.size(); i++) {
			String line = lines.get(i);

			for(int j=0; j<line.length(); j++)
				grid[i][j] = line.charAt(j) - '0';
		}
		
		return grid;
	}
}