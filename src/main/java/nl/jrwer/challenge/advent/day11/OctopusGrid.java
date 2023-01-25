package nl.jrwer.challenge.advent.day11;

public class OctopusGrid {
	private int flashes = 0; 
	private final Octopus[][] grid;
	private final int width, height;
	
	public OctopusGrid(Octopus[][] grid) {
		this.grid = grid;
		this.height = grid.length;
		this.width = grid[0].length;
	}
	
	public int simulateSteps(int amount) {
		for(int i=0; i<amount; i++)
			simulateStep();
		
		return flashes;
	}
	
	private void simulateStep() {
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
				energyUp(x, y);
		
		nextStep();
	}
	
	private void energyUp(int x, int y) {
		if(x< 0 || x >= width || y < 0 || y >= height)
			return;
		
		if(get(x, y).energyUp()) {
			flashes++;
			onFlash(x, y);
		}
	}
	
	private void onFlash(int x, int y) {
		energyUp(x + 1, y + 1);
		energyUp(x + 1 , y);
		energyUp(x, y + 1);
		energyUp(x - 1, y - 1);
		energyUp(x - 1, y);
		energyUp(x, y - 1);
		energyUp(x + 1, y - 1);
		energyUp(x - 1, y + 1);
	}
	
	private void nextStep() {
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
				get(x, y).reset();
	}
	
	private Octopus get(int x, int y) {
		return grid[y][x];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int y=0; y<grid.length; y++) {
			for(int x=0; x<grid[y].length; x++)
				sb.append(grid[y][x]);
			
			sb.append('\n');
		}
		
		return sb.toString();
	}
}
