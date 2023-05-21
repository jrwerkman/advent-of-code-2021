package nl.jrwer.test.playground;

import java.lang.reflect.Array;

public class RotationCube<T> {
	// https://euclideanspace.com/maths/discrete/groups/categorise/finite/cube/index.htm
	static final char[][] ORIENTATIONS = new char[][] { { 'x' }, { 'y' }, { 'x', 'x' }, { 'x', 'y' }, { 'y', 'x' },
		{ 'y', 'y' }, { 'x', 'x', 'x' }, { 'x', 'x', 'y' }, { 'x', 'y', 'x' }, { 'x', 'y', 'y' }, { 'y', 'x', 'x' },
		{ 'y', 'y', 'x' }, { 'y', 'y', 'y' }, { 'x', 'x', 'x', 'y' }, { 'x', 'x', 'y', 'x' },
		{ 'x', 'x', 'y', 'y' }, { 'x', 'y', 'x', 'x' }, { 'x', 'y', 'y', 'y' }, { 'y', 'x', 'x', 'x' },
		{ 'y', 'y', 'y', 'x' }, { 'x', 'x', 'x', 'y', 'x' }, { 'x', 'y', 'x', 'x', 'x' },
		{ 'x', 'y', 'y', 'y', 'x' }, };
		
		
	final Class<T> clazz;
	
	public RotationCube(Class<T> clazz) {
		this.clazz = clazz; 
		
	}
	
	public T[][][] rotate(T[][][] map, char[] rotations) {
		for(char r : rotations) {
			map = rotate(map, r);
		}
		
		return map;
	}
	
	private T[][][] rotate(T[][][] map, char r) {
		if(r == 'x')
			return rotateX(map);
		else if(r == 'y')
			return rotateY(map);
		else if((r == 'z'))
			return rotateZ(map);

		throw new RuntimeException("Unsupported character: " + r);
	}

    @SuppressWarnings("unchecked")
	private T[][][] rotateX(T[][][] arr) {
        int same = arr.length;
        int n = arr[0][0].length;
        int m = arr[0].length;
        
		Object[][][] temp = (T[][][])Array.newInstance(clazz, same, n, m);
        
        for (int x = 0; x < same; x++)
        	for (int y = 0; y < m; y++)
            	for (int z = 0; z < n; z++)
                    temp[x][m - 1 - z][y] = arr[x][y][z];
        
        return (T[][][]) temp;
    }
    
    @SuppressWarnings("unchecked")
    private T[][][] rotateY(T[][][] arr) {
        int m = arr.length;
        int same = arr[0].length;
        int n = arr[0][0].length;

        T[][][] newArr = (T[][][])Array.newInstance(clazz, n, same, m);

        for (int x = 0; x < m; x++)
            for (int y = 0; y < same; y++)
                for (int z = 0; z < n; z++)
                	newArr[m - 1 - z][y][x] = arr[x][y][z];

        return newArr;
    }
    
    @SuppressWarnings("unchecked")
	private T[][][] rotateZ(T[][][] arr) {
    	int n = arr[0].length;
        int m = arr.length;
        int same = arr[0][0].length;

        T[][][] newArr = (T[][][])Array.newInstance(clazz, 
				n, m, same);

        for (int x = 0; x < m; x++)
            for (int y = 0; y < n; y++)
                for (int z = 0; z < same; z++)
                	newArr[y][m - 1 - x][z] = arr[x][y][z];

        return newArr;
    }  
}
