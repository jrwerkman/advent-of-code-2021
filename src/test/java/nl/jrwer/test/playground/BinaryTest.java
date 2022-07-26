package nl.jrwer.test.playground;

public class BinaryTest {
	private static final int NEXT_VALUE = 65536;
	
	public static void main(String[] args) {
		
		
		int x = 603;
		int y = 748;
		
		System.out.println(Integer.toString(x, 2));
		System.out.println(Integer.toString(y, 2));
		
		// store value
		System.out.println(Integer.toString((x << 10) + y, 2));
		System.out.println(Integer.toString((x << 10) + y));

		/**
		 	1001011011
			1011101100
			1001011011
			1011101100
		 */
		
	}
}
