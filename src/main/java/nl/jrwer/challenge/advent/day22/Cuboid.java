package nl.jrwer.challenge.advent.day22;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cuboid {
	final Coord a, b;

	final int width, height, depth;
	final long size;

	
	public Cuboid(Coord a, Coord b) {
		this.a = a;
		this.b = b;
		
		this.width = (b.x - a.x) + 1;
		this.height = (b.y - a.y) + 1;
		this.depth = (b.z - a.z) + 1;
		this.size = (long)this.width * (long)this.height * (long)this.depth;

//		System.out.println(width + ", " + height + ", " + depth);
//		System.out.println(size);
	}
	
	/**
	 * on overlap return 15, 11, 7 or 2
	 * @param c
	 * @return
	 */
	public Set<Cuboid> splitOverlap(Cuboid c) {
		Cuboid o = overlapArea(c);
		
		if(o != null) {
			Set<Cuboid> set = new HashSet<>();
			
			
		}
		
		
		// no overlap return 2
		return Set.of(this, c);
	}
	
	// TODO
	public Set<Cuboid> split(Coord coord) {
		return Set.of(
				new Cuboid(new Coord(a), coord),
				new Cuboid(new Coord(a), coord),
				new Cuboid(coord, coord),
				new Cuboid(coord, coord),
				new Cuboid(coord, coord),
				new Cuboid(coord, coord),
				new Cuboid(coord, coord),
				new Cuboid(coord, coord));
	}
	
	
	public Cuboid overlapArea(Cuboid c) {
		if(overlap(c) > 0L) {
			int[] x = overlapCoords(a.x, b.x, c.a.x, c.b.x); 
			int[] y = overlapCoords(a.y, b.y, c.a.y, c.b.y); 
			int[] z = overlapCoords(a.z, b.z, c.a.z, c.b.z); 
			
			return new Cuboid(
					new Coord(x[0], y[0], z[0]), 
					new Coord(x[1], y[1], z[1]));
		}
		
		return null;
	}
	
	public long overlap(Cuboid c) {
		long overlap = (long)overlap(a.x, b.x, c.a.x, c.b.x) * 
				(long)overlap(a.y, b.y, c.a.y, c.b.y) *
				(long)overlap(a.z, b.z, c.a.z, c.b.z);
		
		return overlap; 
	}
	
	private int[] overlapCoords(int firstA, int firstB, int secondA, int secondB) {
		if(firstB > secondA && firstB < secondB)
			return new int[] {secondA, firstB};
		
		return new int[] {secondB, firstA};
	}
	
	private int overlap(int firstA, int firstB, int secondA, int secondB) {
		if(firstA > secondB || secondA > firstB)
			return 0;
		
		if(firstB > secondA && firstB < secondB)
			return Math.abs(firstB - secondA);
		
		return Math.abs(secondB - firstA);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(a.hashCode(), b.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cuboid) {
			Cuboid c = (Cuboid) obj;
			
			return (c.a.equals(a) && c.b.equals(b)) || (c.a.equals(b) && c.b.equals(a));
		}
		return false;
	}	
}
