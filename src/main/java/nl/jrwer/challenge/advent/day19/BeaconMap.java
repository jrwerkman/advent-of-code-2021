package nl.jrwer.challenge.advent.day19;

import java.util.List;

public class BeaconMap {
	// https://euclideanspace.com/maths/discrete/groups/categorise/finite/cube/index.htm
	static final char[][] ORIENTATIONS = new char[][] { { 'x' }, { 'y' }, { 'x', 'x' }, { 'x', 'y' }, { 'y', 'x' },
			{ 'y', 'y' }, { 'x', 'x', 'x' }, { 'x', 'x', 'y' }, { 'x', 'y', 'x' }, { 'x', 'y', 'y' }, { 'y', 'x', 'x' },
			{ 'y', 'y', 'x' }, { 'y', 'y', 'y' }, { 'x', 'x', 'x', 'y' }, { 'x', 'x', 'y', 'x' },
			{ 'x', 'x', 'y', 'y' }, { 'x', 'y', 'x', 'x' }, { 'x', 'y', 'y', 'y' }, { 'y', 'x', 'x', 'x' },
			{ 'y', 'y', 'y', 'x' }, { 'x', 'x', 'x', 'y', 'x' }, { 'x', 'y', 'x', 'x', 'x' },
			{ 'x', 'y', 'y', 'y', 'x' }, };

	static final int SIZE = 2000;
	final boolean[][][] map = new boolean[SIZE][SIZE][SIZE];
	final List<Beacon> beacons;

	public BeaconMap(List<Beacon> beacons) {
		this.beacons = beacons;
		this.createMap();
	}

	private void createMap() {
		for (Beacon b : beacons)
			map[b.x][b.y][b.z] = true;
	}

	public void combine(BeaconMap other) {
		compare(other);
	}
	
	private void compare(BeaconMap other) {
		for(char[] orientation : ORIENTATIONS) {
			boolean[][][] rotatedMap = rotate(map, orientation);
			
			if(overlay(other, rotatedMap)) {
				// TODO adjust full map
			}
			
		}
	}
	
	private boolean overlay(BeaconMap other, final boolean[][][] rotatedMap) {
		// TODO compare maps
		
		return false;
	}
	
	private boolean[][][] rotate(boolean[][][] map, char[] rotations) {
		for(char r : rotations)
			map = rotate(map, r);
		
		return map;
	}
	
	private boolean[][][] rotate(boolean[][][] map, char r) {
		if(r == 'x')
			return rotateX(map);
		else if(r == 'y')
			return rotateY(map);
		else if((r == 'z'))
			return rotateZ(map);

		throw new RuntimeException("Unsupported character: " + r);
	}

	
	private boolean[][][] rotateX(boolean[][][] orgMap) {
        boolean[][][] rotatedMap = new boolean[SIZE][SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                for (int k = 0; k < SIZE; k++)
                    rotatedMap[i][j][k] = orgMap[i][k][j];
        
        return rotatedMap;
    }
    
    private boolean[][][] rotateY(boolean[][][] orgMap) {
        boolean[][][] rotatedMap = new boolean[SIZE][SIZE][SIZE];

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                for (int k = 0; k < SIZE; k++)
                	rotatedMap[i][j][k] = orgMap[j][i][k];

        return rotatedMap;
    }
    
    private boolean[][][] rotateZ(boolean[][][] orgMap) {
        boolean[][][] rotatedMap = new boolean[SIZE][SIZE][SIZE];

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                for (int k = 0; k < SIZE; k++)
                	rotatedMap[k][j][i] = orgMap[j][k][i];

        return rotatedMap;
    }    
}
