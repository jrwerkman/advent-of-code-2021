package nl.jrwer.challenge.advent.day22;

import java.util.List;

public class ProcedureArea extends Cuboid {
	final boolean[][][] cubes;
	final int width, height, depth;
	
	int cubesOn = 0;
	
	public ProcedureArea(Coord c1, Coord c2) {
		super(c1, c2);

		this.width = (c2.x - c1.x) + 1;
		this.height = (c2.y - c1.y) + 1;
		this.depth = (c2.z - c1.z) + 1;
		
		System.out.println(width + ", " + height + ", " + depth);
		
		this.cubes = new boolean[width][height][depth];
	}
	
	public int executeReboot(List<RebootStep> steps) {
		for(RebootStep step : steps)
			executeReboot(step);
		
		return cubesOn;
	}

	public void executeReboot(RebootStep step) {
		for(int x=c1.x; x<=c2.x; x++)
			for(int y=c1.y; y<=c2.y; y++)
				for(int z=c1.z; z<=c2.z; z++)
					set(x, y, z, step);
	}
	
	private void set(int x, int y, int z, RebootStep step) { 
		boolean on = step.on;
		
		if(inArea(x, y, z, step)) {
			boolean current = cubes[x - c1.x][y - c1.y][z - c1.z]; 
			cubes[x - c1.x][y - c1.y][z - c1.z] = on;
			
			if(current != on)
				cubesOn += (on ? 1 : -1);
		}
	}

	private boolean inArea(int x, int y, int z, RebootStep step) {
		Cuboid c = step.cuboid;
		
		return x >= c.c1.x && x <= c.c2.x 
				&& y >= c.c1.y && y <= c.c2.y 
				&& z >= c.c1.z && z <= c.c2.z;
	}	
}
