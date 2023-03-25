package nl.jrwer.challenge.advent.day16;

public class Decoder {
	final String hexSequence;
	final String binarySequence;
	
	int version;
	int typeId;
	int lengthTypeId = -1;
	int subpacketsInfo = -1;
	String[] subpackets = new String[0];

	// TODO sub sub sub , etc...
	public Decoder(String hexSequence) {
		this.hexSequence = hexSequence;
		this.binarySequence = convert();
	}
	
	public void decode() {
		this.version = parse(binarySequence, 0, 3);
		this.typeId = parse(binarySequence, 3, 3);
		System.out.println("Version: " + version);
		System.out.println("TypeID: " + typeId);
		
		if(typeId == 4) {
			decodeLiteral();
			return;
		}
		
		this.lengthTypeId = parse(binarySequence, 6, 1);
		System.out.println("Length TypeID: " + lengthTypeId);

		if(lengthTypeId == 0)
			decodeLength();
		else
			decodeNumber();

	}
	
	private void decodeLiteral() {
		StringBuilder sb = new StringBuilder();
		subpackets = new String[1];
		int i = 0;
		
		while(true) {
			String group = sub(binarySequence, (i*5) + 6, 5);
			sb.append(group.substring(1, 5));
			
			
			if(group.startsWith("0"))
				break;

			i++;
		}
		
		subpackets[0] = sb.toString();
	}
	
	private void decodeLength() {
		this.subpacketsInfo = parse(binarySequence, 7, 15);
		System.out.println("Length Subpackets: " + subpacketsInfo);
		int restLength = subpacketsInfo % 11 + 11;
		
		subpackets = new String[subpacketsInfo / 11];
		
		for(int i=0; i<subpackets.length; i++) {
			if(i != subpackets.length - 1)
				subpackets[i] = sub(binarySequence, (i*11) + 22, 11);
			else
				subpackets[i] = sub(binarySequence, (i*11) + 22, restLength);
		}
	}
	
	private void decodeNumber() {
		this.subpacketsInfo = parse(binarySequence, 7, 11);
		System.out.println("Number Subpackets: " + subpacketsInfo);
		
		subpackets = new String[subpacketsInfo];
		
		for(int i=0; i<subpackets.length; i++)
			subpackets[i] = sub(binarySequence, (i*11) + 22, 11);
	}

	private int parse(String str, int index, int length) {
		return Integer.parseInt(sub(str, index, length), 2);
	}
	
	private String sub(String str, int index, int length) {
		System.out.println(str.substring(index, index + length));
		return str.substring(index, index + length);
	}
	
	private String convert() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<hexSequence.length(); i++) {
			int digit = Integer.parseInt(hexSequence.substring(i, i+1), 16);
			String binary = Integer.toBinaryString(digit);
			
			sb.append(padLeftZeros(binary, 4));
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append('0');
	    }
	    sb.append(inputString);

	    return sb.toString();
	}
	
	public int parseSubpackets() {
		int totalVersion = 0;
		
		for(String packet : subpackets) {
			int version = parse(packet, 0, 3);
			System.out.println("version: " + version);
			totalVersion += version;
			
		}
		
		return totalVersion;
	}
}
