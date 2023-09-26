package nl.jrwer.challenge.advent.day22;

import java.util.ArrayList;
import java.util.List;

public class ProcedureArea extends Cuboid {
	final boolean[][][] cubes;
	final List<RebootStep> steps;
	
	int cubesOnRebootRegion = 0;
	
	public ProcedureArea(List<RebootStep> steps, Coord c1, Coord c2) {
		super(c1, c2);

		this.steps = steps;
		this.cubes = new boolean[width][height][depth];
	}
	
	public ProcedureArea(List<RebootStep> steps) {
		this(steps, new Coord(0, 0, 0), new Coord(0, 0, 0));
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
	
	List<CuboidState> core = new ArrayList<>();
	
	public long executeCompleteReboot() {
		for(RebootStep step : steps) {
			Cuboid current = step.cuboid;
			
			// create a temp list, for update to add later
			List<CuboidState> processing = new ArrayList<>();

			// if the step turns on cubes, add it, if it turns cubes off, 
			// only the intersects will be added 
			if(step.on)
				processing.add(new CuboidState(current, true));
			
			// check intersect with processed cuboids 
			for(CuboidState entry : core) {
				Cuboid intersect = current.intersect(entry.cuboid);
				
				// if there is an intersect, add the intersect, but set the state
				// opposite of the current comparing processed entry from the core.
				if(intersect != null)
					processing.add(new CuboidState(intersect, !entry.state));
			}
			
			// Add the new processed entry to the core
			core.addAll(processing);
		} 
		
		// calculate the cubes that are turned on.
		long result = 0L;
		
		for(CuboidState state : core)
			result += state.getCubes();
		
		return result;
	}
	
	class CuboidState {
		Cuboid cuboid;
		Boolean state;
		
		public CuboidState(Cuboid cuboid, Boolean state) {
			this.cuboid = cuboid;
			this.state = state;
		}
		
		public long getCubes() {
			return cuboid.size * (state ? 1 : -1);
		}
		
	}
}
