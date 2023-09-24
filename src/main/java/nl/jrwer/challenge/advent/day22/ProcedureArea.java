package nl.jrwer.challenge.advent.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcedureArea extends Cuboid {
	final boolean[][][] cubes;
	final List<RebootStep> steps;
	
	int cubesOnRebootRegion = 0;
	long cubesOn = 0L;
	
	public ProcedureArea(List<RebootStep> steps, Coord c1, Coord c2) {
		super(c1, c2);

		this.steps = steps;
		this.cubes = new boolean[width][height][depth];
	}
	
	public int executeReboot() {
		for(RebootStep step : steps)
			executeReboot(step);
		
		return cubesOnRebootRegion;
	}

	public void executeReboot(RebootStep step) {
		for(int x=a.x; x<=b.x; x++)
			for(int y=a.y; y<=b.y; y++)
				for(int z=a.z; z<=b.z; z++)
					set(x, y, z, step);
	}
	
	private void set(int x, int y, int z, RebootStep step) { 
		boolean on = step.on;
		
		if(inArea(x, y, z, step)) {
			boolean current = cubes[x - a.x][y - a.y][z - a.z]; 
			cubes[x - a.x][y - a.y][z - a.z] = on;
			
			if(current != on)
				cubesOnRebootRegion += (on ? 1 : -1);
		}
	}

	private boolean inArea(int x, int y, int z, RebootStep step) {
		Cuboid c = step.cuboid;
		
		return x >= c.a.x && x <= c.b.x 
				&& y >= c.a.y && y <= c.b.y 
				&& z >= c.a.z && z <= c.b.z;
	}
	
	Set<Cuboid> overlapOn = new HashSet<Cuboid>();
	Set<Cuboid> overlapOff = new HashSet<Cuboid>();
	
	public long executeCompleteReboot() {
		for(RebootStep step : steps) {
			if(step.on)
				addOn(step);
			
			break;
		}
		
		return cubesOn;
	}
	
	private void addOn(RebootStep step) {
		for(RebootStep other : steps) {
			Cuboid overlap = step.cuboid.overlapArea(other.cuboid);
			
			if(overlap != null)
				overlapOn.add(overlap);
		}
	}
}
