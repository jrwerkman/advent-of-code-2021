package nl.jrwer.challenge.advent.day22;

import java.util.Objects;

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
	
	public Cuboid intersect(Cuboid other) {
        var minX = Math.max(a.x, other.a.x);
        var maxX = Math.min(b.x, other.b.x);
        var minY = Math.max(a.y, other.a.y);
        var maxY = Math.min(b.y, other.b.y);
        var minZ = Math.max(a.z, other.a.z);
        var maxZ = Math.min(b.z, other.b.z);
        
        if (minX > maxX || minY > maxY || minZ > maxZ)
            return null;
        
        return new Cuboid(new Coord(minX, minY, minZ), new Coord(maxX, maxY, maxZ));
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
