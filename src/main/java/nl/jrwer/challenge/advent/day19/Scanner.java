package nl.jrwer.challenge.advent.day19;

import java.util.HashSet;
import java.util.Set;

public class Scanner extends Coord {
	final int id;
	final Set<Beacon> beacons = new HashSet<>();
	
	public Scanner(int id) {
		super(0,0,0);
		this.id = id;
	}
	
	public void addBeacon(Beacon beacon) {
		beacons.add(beacon);
	}
	
	public BeaconMap getMap() {
		return new BeaconMap(this);
	}
	
	public void set(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int manhattanDistance(Scanner other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("--- scanner ").append(id).append(" ---").append('\n');
		
		for(Beacon beacon : beacons)
			sb.append(beacon).append('\n');
		
		return sb.toString();
	}
}
