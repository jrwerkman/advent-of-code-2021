package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day02Dive {

	/*
	 * https://adventofcode.com/2021/day/2

		--- Day 2: Dive! ---
		
		Now, you need to figure out how to pilot this thing.
		
		It seems like the submarine can take a series of commands like forward 1, down 2, or up 3:
		
		    forward X increases the horizontal position by X units.
		    down X increases the depth by X units.
		    up X decreases the depth by X units.
		
		Note that since you're on a submarine, down and up affect your depth, and so they have the opposite result of what you might expect.
		
		The submarine seems to already have a planned course (your puzzle input). You should probably figure out where it's going. For example:
		
		forward 5
		down 5
		forward 8
		up 3
		down 8
		forward 2
		
		Your horizontal position and depth both start at 0. The steps above would then modify them as follows:
		
		    forward 5 adds 5 to your horizontal position, a total of 5.
		    down 5 adds 5 to your depth, resulting in a value of 5.
		    forward 8 adds 8 to your horizontal position, a total of 13.
		    up 3 decreases your depth by 3, resulting in a value of 2.
		    down 8 adds 8 to your depth, resulting in a value of 10.
		    forward 2 adds 2 to your horizontal position, a total of 15.
		
		After following these instructions, you would have a horizontal position of 15 and a depth of 10. (Multiplying these together produces 150.)
		
		Calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth?
	 * 
	 */
	
	public static void main(String[] args) {
		try {
			Day02Dive day = new Day02Dive();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Day02Dive() {
		
	}
	
	public void start() {
		List<Data> course = getInput();
		
		int horizontal = 0;
		int depth = 0;
		
		for(Data step : course) {
			switch(step.direction) {
			case FORWARD:
				horizontal += step.units;
				break;
			case DOWN:
				depth += step.units;
				break;
			case UP:
				depth -= step.units;
				break;
			default:
				break;
			
			}
		}
		
		System.out.println("Horizontal movement: " + horizontal);
		System.out.println("Depth movement: " + depth);
		System.out.println("Result: " + (horizontal * depth));
	}
	
	public List<Data> getInput() {
		List<Data> list = new ArrayList<>();
		
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-02.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
            	String[] split = line.split(" ");
            	
                list.add(new Data(
                			Direction.fromString(split[0]),
                			Integer.parseInt(split[1])
                		));
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
	
	public enum Direction {
		FORWARD,
		UP,
		DOWN;
		
		public static Direction fromString(String direction) {
			direction = direction.toLowerCase();
			
			switch(direction) {
			case "forward":
				return FORWARD;
			case "up":
				return UP;
			case "down":
				return DOWN;
			default:
				return null;
			}
			
		}
	}
	
	public class Data {
		public Data(Direction direction, Integer units) {
			this.direction = direction;
			this.units = units;
		}
		
		Direction direction;
		Integer units;
	}
}
