package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

public class Day08SevenSegmentSearchPart02 extends Day08SevenSegmentSearch {

	/*

		--- Part Two ---
		
		Through a little deduction, you should now be able to determine the remaining digits. Consider again the first example above:
		
		acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
		cdfeb fcadb cdfeb cdbaf
		
		After some careful analysis, the mapping between signal wires and segments only make sense in the following configuration:
		
		 dddd
		e    a
		e    a
		 ffff
		g    b
		g    b
		 cccc
		
		So, the unique signal patterns would correspond to the following digits:
		
		    acedgfb: 8
		    cdfbe: 5
		    gcdfa: 2
		    fbcad: 3
		    dab: 7
		    cefabd: 9
		    cdfgeb: 6
		    eafb: 4
		    cagedb: 0
		    ab: 1
		
		Then, the four digits of the output value can be decoded:
		
		    cdfeb: 5
		    fcadb: 3
		    cdfeb: 5
		    cdbaf: 3
		
		Therefore, the output value for this entry is 5353.
		
		Following this same process for each entry in the second, larger example above, the output value of each entry can be determined:
		
		    fdgacbe cefdb cefbgd gcbe: 8394
		    fcgedb cgb dgebacf gc: 9781
		    cg cg fdcagb cbg: 1197
		    efabcd cedba gadfec cb: 9361
		    gecf egdcabf bgf bfgea: 4873
		    gebdcfa ecba ca fadegcb: 8418
		    cefg dcbef fcge gbcadfe: 4548
		    ed bcgafe cdgba cbgef: 1625
		    gbdfcae bgc cg cgb: 8717
		    fgae cfgab fg bagce: 4315
		
		Adding all of the output values in this larger example produces 61229.
		
		For each entry, determine all of the wire/segment connections and decode the four-digit output values. What do you get if you add up all of the output values?
		
	
	
	 */

	
	public static void main(String[] args) {
		try {
			Day08SevenSegmentSearchPart02 day = new Day08SevenSegmentSearchPart02();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Day08SevenSegmentSearchPart02() {
	}
	
	public void start() {
		List<String> input = getInput();
//		List<String> input = getExampleInput();

		long total = 0;
		
		for(String entry : input) {
			CompleteDataEntry dataEntry = new CompleteDataEntry(entry);
			
			total += dataEntry.getCode();
//			break;
		}
		
		System.out.println("Total is: " + total);
	}

	class CompleteDataEntry extends DataEntry {
		
		private Decoder decoder;
		
		public CompleteDataEntry(String line) {
			super(line);
			
			decoder = new Decoder(uniqueSignalPattern);
		}

		public int getCode() {
			return decoder.decode(digitOutputValue);
		}
		
		
	}
	
	class Decoder {
		String[] numbers = new String[10];
		int[] quick = new int[10];
		
		public Decoder(String[] uniqueSignalPattern) {
			this.setupDecoder(uniqueSignalPattern);
			
			for(int i=0; i<numbers.length; i++)
				quick[i] = toBinary(numbers[i]);
		}
		
		private int toBinary(String input) {
			int binaryNumber = 0;
			
			for(int i=0; i<input.length(); i++) {
				switch(input.charAt(i)) {
				case 'a':
					binaryNumber += 0b1;
					break;
				case 'b':
					binaryNumber += 0b10;
					break;
				case 'c':
					binaryNumber += 0b100;
					break;
				case 'd':
					binaryNumber += 0b1000;
					break;
				case 'e':
					binaryNumber += 0b10000;
					break;
				case 'f':
					binaryNumber += 0b100000;
					break;
				case 'g':
					binaryNumber += 0b1000000;
					break;
				}
			}
			
//			System.out.println(input + " to binary " + Integer.toString(binaryNumber, 2));
			return binaryNumber;
		}
		
		public int getResultingNumber(int binaryNumber) {
			for(int i=0; i<quick.length; i++)
				if(quick[i] == binaryNumber)
					return i;
			
			return -1;
		}
		
		private void setupDecoder(String[] uniqueSignalPattern) {
			List<String> rest = getEasyNumbers(uniqueSignalPattern);
			getRest(rest);
		}
		
		private List<String> getEasyNumbers(String[] uniqueSignalPattern) {
			List<String> rest = new ArrayList<>();
			
			for(String element : uniqueSignalPattern) {
				switch(element.length()) {
				case 2: // 1
					numbers[1] = element;
					break;
				case 3: // 7
					numbers[7] = element;
					break;
				case 4: // 4
					numbers[4] = element;
					break;
				case 7: // 8
					numbers[8] = element;
					break;
				case 5: // 2, 3, 5
				case 6: // 6, 9, 0
					rest.add(element);
				default:
				}
			}
			
			return rest;
		}
		
		private void getRest(List<String> rest) {
			for(String element : rest) {
				if(element.length() == 5) {
					// min 1, length == 3 --> 3
					if(lengthMin(element, numbers[1]) == 3)
						numbers[3] = element;
					else { 
						// min 4, length == 3 --> 2
						// min 4, length == 2 --> 5
						int result = lengthMin(element, numbers[4]);
						
						if(result == 2)
							numbers[5] = element;
						if(result == 3)
							numbers[2] = element;
					}	
				}
				
				if(element.length() == 6) {
					// min 1, length == 5 --> 6
					if(lengthMin(element, numbers[1]) == 5)
						numbers[6] = element;
					else { 
						// min 4, length == 3 --> 0
						// min 4, length == 2 --> 9
						int result = lengthMin(element, numbers[4]);
						
						if(result == 2)
							numbers[9] = element;
						if(result == 3)
							numbers[0] = element;
					}
				}
			}
		}
		
		private int lengthMin(String element, String elementToSubstract) {
			String result = element;
			
			for(int i=0; i<elementToSubstract.length(); i++)
				result = result.replace(String.valueOf(elementToSubstract.charAt(i)), "");
			
			return result.length();
		}
		
		public int decode(String[] input) {
			int result = 0;
			int strLength = input.length;
			
			for(int i=0; i<strLength; i++) {
				int binary = toBinary(input[i]);
				int digit = getResultingNumber(binary);
				
				digit = digit * (int) Math.pow(10, strLength - i - 1);

				result += digit;
			}
			
			return result;
		}
	}
}
