package nl.jrwer.challenge.advent.day18;

public class SnailFishNumber {
	public static boolean log = false; 
	// for parsing
	private int i = 0;
	
	
	private Object left, right;
	private SnailFishNumber parent = null;
	
	public SnailFishNumber(String in) {
		this(in.toCharArray(), 0, null);
	}
	
	private SnailFishNumber(char[] in, int index, SnailFishNumber parent) {
		this.parent = parent;
		this.i = index;
		load(in, index, this);
	}
	
	private SnailFishNumber(SnailFishNumber left, SnailFishNumber right) {
		left.parent = this;
		right.parent = this;
		
		this.left = left;
		this.right = right;
	}

	private SnailFishNumber(Object left, Object right, SnailFishNumber parent) {
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	/**
	 * If any pair is nested inside four pairs, the leftmost such pair explodes.
	 * If any regular number is 10 or greater, the leftmost such regular number splits.
	 * 
	 * @return false when ready
	 */
	public void reduce() {
		boolean hasExploded = true;
		boolean nextCycle = true;

		while(nextCycle) {
			nextCycle = false;
			
			// keep exploding till there is nothing to explode
			do {
				hasExploded = explode(0);
				
				if(hasExploded) {
					if(log)
						System.out.println("after explode:  " + this);
					nextCycle = true;
				}
			} while(hasExploded);
			
			if(checkSplit(0)) {
				if(log)
					System.out.println("after split:    " + this);
				nextCycle = true;
			}
		}
	}
	
	
	private boolean explode(int nested) {
		if(nested >= 4) {
			explode();
			return true;
		} else if(left instanceof SnailFishNumber && ((SnailFishNumber) left).explode(nested + 1)) {
			return true;
		} else if(right instanceof SnailFishNumber && ((SnailFishNumber) right).explode(nested + 1)) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkSplit(int nested) {
		// First check left side
		if(left instanceof SnailFishNumber && ((SnailFishNumber) left).checkSplit(nested + 1))
				return true;
		
		// And first split left side
		if(left instanceof Integer && ((Integer) left) > 9) {
			left = doSplit((Integer) left);
			return true;
		}
		
		if(right instanceof SnailFishNumber && ((SnailFishNumber) right).checkSplit(nested + 1))
				return true;

		if (right instanceof Integer && ((Integer) right) > 9) {
			right = doSplit((Integer) right);
			return true;
		}
		
		return false;
	}
	
	private void explode() {
		if(left instanceof SnailFishNumber)
			((SnailFishNumber) left).explode();
		else if(right instanceof SnailFishNumber)
			((SnailFishNumber) right).explode();
		else if(this == parent.left)
			doExplode(true);
		else if(this == parent.right)
			doExplode(false);
	}
	
	private void doExplode(boolean l) {
		// if other side is SnailFishNumber add exploding to that opposite side SnailFishNumber
		if(parent.get(!l) instanceof SnailFishNumber) {
			SnailFishNumber sfn = (SnailFishNumber) parent.get(!l);
			sfn.set(l, (((Integer)sfn.get(l)) + ((Integer)this.get(!l))));
		// else set Integer of opposite side
		} else {
			parent.set(!l, (((Integer)parent.get(!l)) + ((Integer)this.get(!l))));
		}	
		
		// Travel back to Integer of the same side and add value
		SnailFishNumber currentParent = parent;
		SnailFishNumber parentParent = currentParent.parent;
		
		while(parentParent != null) {
			// Go down a parent
			if(currentParent == parentParent.get(l)) {
				currentParent = parentParent;
				parentParent = currentParent.parent;
			} else {
				// if side is a snailfishnumber number, travel upwards to a integer to set it.
				if(parentParent.get(l) instanceof SnailFishNumber) {
					SnailFishNumber parent = parentParent;
					Object child = parentParent.get(l);

					// check from left up
					while(!(child instanceof Integer)) {
						parent = ((SnailFishNumber) child);
						child = parent.get(!l);
					}
					
					parent.set(!l,((Integer) child) + ((Integer)this.get(l)));  
				} else {
					parentParent.set(l, ((Integer)parentParent.get(l)) + ((Integer)this.get(l)));
				}
				break;
			}
		}
		
		// set exploding side to 0
		parent.set(l, (Integer) 0);		
	}
	
	protected Object get(boolean l) {
		return l ? left : right;
	}
	
	protected void set(boolean l, Object value) {
		if(l)
			this.left = value;
		else
			this.right = value;
			
	}

	private SnailFishNumber doSplit(int number) {
		int half = number / 2;
		int rest = number % 2;
		
		return new SnailFishNumber(half, half + rest, this); 
	}
	
	public int sum() {
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(left).append(',').append(right).append(']');
		
		return sb.toString();
	}
	
	private void load(char[] list, int index, SnailFishNumber parent) {
		for(i=index; i<list.length; i++) {
			char c = list[i];
			
			if(c == '[')
				left = loadSnailFishNumber(list, parent);
			else if(c == ',')
				right = loadSnailFishNumber(list, parent);
			else if(c == ']')
				return;
		}
	}
	
	private Object loadSnailFishNumber(char[] list, SnailFishNumber parent) {
		i++;
		
		if(list[i] == '[') {
			SnailFishNumber snf = new SnailFishNumber(list, i, parent);
			i = snf.i;
			return snf;
		} else {
			return loadDigit(list);
		}
	}
	
	private int loadDigit(char[] list) {
		String digit = String.valueOf(list[i]);
		
		while(Character.isDigit(list[i+1])) {
			i++;
			digit += list[i];
		}
		
		return Integer.parseInt(digit);
	}
	
	public int magnitude() {
		int lm = 3 * (left instanceof Integer ? (Integer) left :
			((SnailFishNumber) left).magnitude());
	
		int rm = 2 * (right instanceof Integer ? (Integer) right :
			((SnailFishNumber) right).magnitude());

		return lm + rm;
	}

	public static SnailFishNumber addition(SnailFishNumber a, String in) {
		return addition(a, new SnailFishNumber(in));
	}
	
	public static SnailFishNumber addition(SnailFishNumber a, SnailFishNumber b) {
		SnailFishNumber sfn = new SnailFishNumber(a, b);
		
		if(log)
			System.out.println("after addition: " + sfn);
		return sfn;
	}
}
