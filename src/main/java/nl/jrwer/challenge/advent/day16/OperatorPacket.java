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
		if (lengthTypeId == 0)
			decodeByLength();
		else
			decodeByNumber();
	}

	private void decodeByLength() {
		this.length = 22;
		this.subpacketsInfo = parse(binarySequence, index + 7, 15);

		while (this.getLength() - 22 < subpacketsInfo)
			this.createSubpacket();
	}

	private void decodeByNumber() {
		this.length = 18;
		this.subpacketsInfo = parse(binarySequence, index + 7, 11);

		for (int i = 0; i < subpacketsInfo; i++)
			this.createSubpacket();
	}

	private void createSubpacket() {
		Packet p = Packet.createPacket(this.binarySequence, this.index + this.getLength());
		subpackets.add(p);
	}

	@Override
	public int getLength() {
		int l = this.length;

		for (Packet p : subpackets)
			l += p.getLength();

		return l;
	}

	@Override
	public long calculate() {
		switch (typeId) {
		case 0:
			return sum();
		case 1:
			return product();
		case 2:
			return min();
		case 3:
			return max();
		case 5:
			return greaterThan();
		case 6:
			return lessThan();
		case 7:
			return equal();
		default:
			throw new RuntimeException();
		}
	}

	/**
	 * Packets with type ID 0 are sum packets - their value is the sum of the values
	 * of their sub-packets. If they only have a single sub-packet, their value is
	 * the value of the sub-packet.
	 * 
	 * @return
	 */
	private long sum() {
		long sum = 0;
		
		for(Packet p : subpackets)
			sum += p.calculate();
		
		return sum;
	}

	/**
	 * Packets with type ID 1 are product packets - their value is the result of
	 * multiplying together the values of their sub-packets. If they only have a
	 * single sub-packet, their value is the value of the sub-packet.
	 * 
	 * @return
	 */
	private long product() {
		long prod = 1;
		
		for(Packet p : subpackets)
			prod *= p.calculate();
		
		return prod;
	}

	/**
	 * Packets with type ID 2 are minimum packets - their value is the minimum of
	 * the values of their sub-packets.
	 * 
	 * @return
	 */
	private long min() {
		long min = Long.MAX_VALUE;
		
		for(Packet p : subpackets)
			min = Math.min(min, p.calculate());
		
		return min;
	}

	/**
	 * Packets with type ID 3 are maximum packets - their value is the maximum of
	 * the values of their sub-packets.
	 * 
	 * @return
	 */
	private long max() {
		long max = Long.MIN_VALUE;
		
		for(Packet p : subpackets)
			max = Math.max(max, p.calculate());
		
		return max;
	}

	/**
	 * Packets with type ID 5 are greater than packets - their value is 1 if the
	 * value of the first sub-packet is greater than the value of the second
	 * sub-packet; otherwise, their value is 0. These packets always have exactly
	 * two sub-packets.
	 * 
	 * @return
	 */
	private long greaterThan() {
		if(subpackets.size() != 2)
			throw new RuntimeException();

		return subpackets.get(0).calculate() > subpackets.get(1).calculate() ? 1 : 0;
	}

	/**
	 * Packets with type ID 6 are less than packets - their value is 1 if the value
	 * of the first sub-packet is less than the value of the second sub-packet;
	 * otherwise, their value is 0. These packets always have exactly two
	 * sub-packets.
	 * 
	 * @return
	 */
	private long lessThan() {
		if(subpackets.size() != 2)
			throw new RuntimeException();

		return subpackets.get(0).calculate() < subpackets.get(1).calculate() ? 1 : 0;
	}

	/**
	 * Packets with type ID 7 are equal to packets - their value is 1 if the value
	 * of the first sub-packet is equal to the value of the second sub-packet;
	 * otherwise, their value is 0. These packets always have exactly two
	 * sub-packets.
	 * 
	 * @return
	 */
	private long equal() {
		if(subpackets.size() != 2)
			throw new RuntimeException();

		return subpackets.get(0).calculate() == subpackets.get(1).calculate() ? 1 : 0;
	}

}
