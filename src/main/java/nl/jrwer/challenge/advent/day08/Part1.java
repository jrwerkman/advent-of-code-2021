package nl.jrwer.challenge.advent.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 1 = 2 *
	 * 2 = 5
	 * 3 = 5
	 * 4 = 4 *
	 * 5 = 5
 	 * 6 = 6
	 * 7 = 3 *
	 * 8 = 7 *
	 * 9 = 6
	 * 0 = 6
	 * 
	 */
	
	public void start() {
		List<String> input = getInput();
//		List<String> input = getExampleInput();
		
		int recognizedDigits = 0;
		DataEntry entry;
		
		for(String inputEntry : input) {
			entry = new DataEntry(inputEntry);
			
			for(String digit : entry.digitOutputValue) 
				switch(digit.length()) {
				case 2:
				case 4:
				case 3:
				case 7:
					recognizedDigits++;
					break;
				default:
				}
		}
		
		System.out.println("Found digits 1,4,7,8: " + recognizedDigits);
	}

	public List<String> getInput() {
		List<String> list = new ArrayList<>();
		
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input-day-08.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            while ((line = reader.readLine()) != null) 
            	list.add(line);
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
	
	public List<String> getExampleInput() {
		List<String> list = new ArrayList<>();

		list.add("be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe");
		list.add("edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc");
		list.add("fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg");
		list.add("fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb");
		list.add("aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea");
		list.add("fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb");
		list.add("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe");
		list.add("bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef");
		list.add("egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb");
		list.add("gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce");

		return list;
	}
}
