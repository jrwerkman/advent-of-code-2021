package nl.jrwer.challenge.advent.day16;

import java.util.ArrayList;
import java.util.List;

public class OperatorPacket extends Packet {

	final int lengthTypeId;
	int subpacketsInfo;
	List<Packet> subpackets = new ArrayList<>();

	protected OperatorPacket(String binarySequence, int index, int version, int typeId) {
		super(binarySequence, index, version, typeId);
		
		this.lengthTypeId = parse(binarySequence, index + 6, 1);
	}

	@Override
	public void decode() {
		if(lengthTypeId == 0)
			decodeByLength();
		else
			decodeByNumber();
		
		for(Packet p : subpackets)
			length += p.getLength();
	}
	
	private void decodeByLength() {
		this.length = 22;
		this.subpacketsInfo = parse(binarySequence, index + 7, 15);
//		System.out.println(this.getClass().getSimpleName() + "   - packet length: " + subpacketsInfo);

		while(this.getLength() - 22 < subpacketsInfo)
			this.createSubpacket();
	}
	
	private void decodeByNumber() {
		this.length = 18;
		this.subpacketsInfo = parse(binarySequence, index + 7, 11);
//		System.out.println(this.getClass().getSimpleName() + " - packet amount: " + subpacketsInfo);

		for(int i=0; i<subpacketsInfo; i++)
			this.createSubpacket();
	}
	
	private void createSubpacket() {
		Packet p = Packet.createPacket(this.binarySequence, this.index + this.getLength());
		subpackets.add(p);

//		System.out.println("  " + this.getClass().getSimpleName() +" ("+version+") - " + length + " " + p.getLength());
	}

	@Override
	public int getLength() {
		int l = this.length;
		
		for(Packet p : subpackets)
			l += p.length;
		
		return l;
	}

}
