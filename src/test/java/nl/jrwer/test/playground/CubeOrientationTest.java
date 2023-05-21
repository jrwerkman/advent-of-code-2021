package nl.jrwer.test.playground;

import java.util.Objects;

import nl.jrwer.challenge.advent.day19.Beacon;
import nl.jrwer.challenge.advent.day19.Rotation;

public class CubeOrientationTest {
	public static void main(String[] args) {
//		rot2dTest();
//		rot3dTest1();
//		rot3dTest2();
		
		rotTest();
		
	}
	
	public static void rotTest() {
		Beacon b = new Beacon(1, 2, 3);
		
		for(Rotation r : Rotation.ROTATIONS) {
			System.out.println(r.rotate(b));
		}
	}
  
	public static void rot2dTest() {
		int dim = 5;
		Boolean[][] arr = new Boolean[dim-1][dim];
		arr[3][4] = true;
		print(arr);

		System.out.println();
		print(rotateCW(arr));
	}
	
	public static Boolean[][] rotateCW(Boolean[][] arr) {
		int m = arr.length;
		int n = arr[0].length;
		
		Boolean[][] temp = new Boolean[n][m];
		
		for(int x=0; x<m; x++)
			for(int y=0; y<n; y++)
				temp[y][m - 1 - x] = arr[x][y];
		
		return temp;
	}
	
	public static Boolean[][] rotateCCW(Boolean[][] arr) {
		int m = arr.length;
		int n = arr[0].length;

		Boolean[][] temp = new Boolean[n][m];
		
		for(int x=0; x<m; x++)
			for(int y=0; y<n; y++)
				temp[m - 1 - y][x] = arr[x][y];
		
		return temp;
	}
	
	public static void print(Boolean[][] arr) {
		for(int x=0; x<arr.length; x++) {
			for(int y=0; y<arr[x].length; y++) {
				if(arr[x][y] != null && arr[x][y])
					System.out.print("X ");
				else 
					System.out.print("O ");
			}
			System.out.println();
		}
	}
	
	public static void rot3dTest1() {
		Boolean[][][] b = new Boolean[3][4][5];
		b[2][3][4] = true;
		RotationCube<Boolean> rot = new RotationCube<Boolean>(Boolean.class);
		print(b);
		Boolean[][][] rotated = rot.rotate(b, new char[] {'x','y','z'});
		print(rotated);
		

	}
	
	public static void print(Boolean[][][] arr) {
		for(int x = 0; x<arr.length; x++) {
			for(int y=0; y<arr[0].length; y++) {
				for(int z=0; z<arr[0][0].length; z++) {
					if(arr[x][y][z] != null && arr[x][y][z])
						System.out.print("X ");
					else 
						System.out.print("O ");
				}
				System.out.println();
			}
			System.out.println("----------");
		}
	}
	
	public static void rot3dTest2() {
		int size = 11;
		int half = size / 2;
		
		RotationCube<Boolean> rot = new RotationCube<Boolean>(Boolean.class);
		Coord c = new Coord(1, 2, 3);
		System.out.println(c);
		System.out.println();
		Boolean[][][] map = new Boolean[size][size][size];
		map[c.x+half][c.y+half][c.z+half] = true;
		
		for(char[] o : RotationCube.ORIENTATIONS) {
			Boolean[][][] rotMap = rot.rotate(map, o);
			
			for(int x = 0; x<size; x++)
				for(int y = 0; y<size; y++)
					for(int z = 0; z<size; z++)
						if(rotMap[x][y][z] != null)
							System.out.println((x-half)+","+(y-half)+","+(z-half));
		}		
	}
	
	
	
	static class Coord {
		int x, y, z;
		
		public Coord(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y, x);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Coord) {
				Coord c = (Coord) obj;
				
				return c.x == x && c.y == y && c.z == z;
			}
			return false;
		}
		
		@Override
		public String toString() {
			return x+","+y+","+z;
		}
	}
}
