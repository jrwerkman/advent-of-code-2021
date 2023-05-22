package nl.jrwer.challenge.advent.day20;

import java.util.Arrays;

public class Image {
	private final static int[][] SQUARE = new int[][] {
		{-1,-1}, {-1, 0}, {-1, 1},
		{ 0,-1}, { 0, 0}, { 0, 1},
		{ 1,-1}, { 1, 0}, { 1, 1}
	};
	
	private final char[] enhancementAlgorithm;
	private char[][] image;
	
	private int height, width;
	
	public Image(char[] ea, char[][] image) {
		this.enhancementAlgorithm = ea;
		this.image = image;
		
		this.height = image[0].length;
		this.width = image.length;
	}
	
	public int litPixels() {
		int lit = 0;
		
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
				if(get(x, y) == '#')
					lit++;

		return lit;
	}
	
	public void enhance(int times) {
		enlargeImageGrid(times);
		
		for(int i=0; i<times; i++)
			enhance();
	}
	
	public void enhance() {
		char[][] enhancedImage = new char[height][width];
		
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
				enhancedImage[y][x] = getEnhancedPixel(x, y);
		
		image = enhancedImage;
	}
	
	private char getEnhancedPixel(int x, int y) {
		return enhancementAlgorithm[getPixelValue(x, y)];
	}
	
	private int getPixelValue(int x, int y) {
		StringBuilder sb = new StringBuilder();
		
		for(int[] c : SQUARE) {
			char pixel = get(x+c[1], y+c[0]); 
			sb.append(pixel == '#' ? 1 : 0);
		}
		
		return Integer.parseInt(sb.toString(), 2);
	}
	
	private char get(int x, int y) {
		// loop to other side of the image, because the char not in the image array
		// should also be changed and that should be the same as the pixel on the other
		// infinite side
		if(x<0) x=width-1;
		if(x>=width) x=0;
		if(y<0) y=height-1;
		if(y>=height) y=0;
		
		return image[y][x];
	}
	
	private void enlargeImageGrid(int f) {
		int enlarged = f * 2;
		char[][] enlargedIamge = new char[height + enlarged][width + enlarged];
		
		for(int i=0; i<height + enlarged; i++)
			Arrays.fill(enlargedIamge[i], '.');
		
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
				enlargedIamge[y+f][x+f] = image[y][x];

		height += enlarged;
		width += enlarged;
		image = enlargedIamge;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<image.length; i++) {
			for(int j=0; j<image[i].length; j++)
				sb.append(image[i][j]);
		
			sb.append('\n');
		}
		
		return sb.toString();
	}
}
