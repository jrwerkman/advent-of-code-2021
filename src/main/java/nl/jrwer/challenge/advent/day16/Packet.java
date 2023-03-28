package nl.jrwer.challenge.advent.day16;

public abstract class Packet {
	final String binarySequence; 
	
	final int version;
	final int typeId;
	
	final int index;
	int length;
	
	protected Packet(String binarySequence, int index, int version, int typeId) {
		this.binarySequence = binarySequence;
		this.index = index;
		this.version = version;
		this.typeId = typeId;
		
//		System.out.println("-----------------");
//		System.out.println(this.getClass().getSimpleName() + " - version: " + version);
//		System.out.println(this.getClass().getSimpleName() + " - typeid : " + typeId);
//		System.out.println(this.getClass().getSimpleName() + " - index  : " + index);
	}
	
	public abstract void decode();
	public abstract int getLength();
	
	protected static int parse(String str, int index, int length) {
		return Integer.parseInt(sub(str, index, length), 2);
	}
	
	protected static String sub(String str, int index, int length) {
		return str.substring(index, index + length);
	}

	public static Packet createPacket(String binarySequence, int index) {
//		System.out.println(index);
		String versionStr = sub(binarySequence, index, 3);
		String typeIdStr = sub(binarySequence, index + 3, 3);
		
		return createPacket(versionStr, index, typeIdStr, binarySequence);
	}
	
	public static Packet createPacket(String versionStr, int index, String typeIdStr, String binarySequence) {
		int version = Integer.parseInt(versionStr, 2);
		int typeId = Integer.parseInt(typeIdStr, 2);

		Packet p = typeId == 4 ? new LiteralPacket(binarySequence, index, version, typeId) :
				new OperatorPacket(binarySequence, index, version, typeId);
		
		p.decode();
		
		return p;
	}
}
