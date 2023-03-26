package nl.jrwer.challenge.advent.day16;

public class LiteralPacket  extends Packet {

	String binaryValue;
	int value;
	
	protected LiteralPacket(String binarySequence, int index, int version, int typeId) {
		super(binarySequence, index, version, typeId);
		this.length = 6;
	}
	
	@Override
	public void decode() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		
		while(true) {
			String group = sub(binarySequence, index + 6 + (i*5), 5);
			sb.append(group.substring(1, 5));
			this.length += 5;
			
			if(group.startsWith("0"))
				break;

			i++;
		}
		
		binaryValue = sb.toString();
		value = Integer.parseInt(binaryValue, 2);
	}

	@Override
	public int getLength() {
		return length;
	}
}
