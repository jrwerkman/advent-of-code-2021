package nl.jrwer.challenge.advent.day22;

import java.util.Objects;

public class RebootStep {
	final boolean on;
	final Cuboid cuboid;
	
	public RebootStep(boolean on, Cuboid cuboid) {
		this.on = on;
		this.cuboid = cuboid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cuboid.hashCode(), on);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RebootStep) {
			RebootStep rs = (RebootStep) obj;
			
			return rs.cuboid.equals(cuboid) && rs.on == on;
		}
		return false;
	}		
}
