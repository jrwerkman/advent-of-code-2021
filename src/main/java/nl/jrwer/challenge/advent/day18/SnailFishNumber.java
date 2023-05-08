package nl.jrwer.challenge.advent.day18;

public class SnailFishNumber {
	// for parsing
	private int i = 0;
	
	
	private Object left, right;
	private SnailFishNumber parent = null;
	
	public SnailFishNumber(String in) {
		this(in.toCharArray(), 0, null);
		System.out.println("Constructor in    --> " + in);
		System.out.println("Constructor parse --> " + this);
	}
	
	private SnailFishNumber(char[] in, int index, SnailFishNumber parent) {
		this.parent = parent;
		load(in, index, this);
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
		while(reduce(0)) {
			System.out.println("after reduce once --> " + this);
			//break;
		}
	}
	
	private boolean reduce(int nested) {
		if(nested >= 4) {
			explode();
			return true;
		}
		
		if(left instanceof Integer && ((Integer) left) > 9) {
			split(true);
			return true;
		}
		if (right instanceof Integer && ((Integer) right) > 9) {
			split(false);
			return true;
		}
		
		if(left instanceof SnailFishNumber)
			if(((SnailFishNumber) left).reduce(nested + 1))
				return true;

		if(right instanceof SnailFishNumber)
			if(((SnailFishNumber) right).reduce(nested + 1))
				return true;
		
		return false;
	}
	
	private void explode() {
		if(this == parent.left) {
			parent.right = ((Integer)parent.right) 
					+ ((Integer)this.right);
			
			SnailFishNumber currentParent = parent;
			SnailFishNumber parentParent = currentParent.parent;
			
			while(parentParent != null) {
				if(currentParent == parentParent.left ) {
					currentParent = parentParent;
					parentParent = currentParent.parent;
				} else {
					if(parentParent.left instanceof SnailFishNumber) {
						SnailFishNumber parent = parentParent;
						Object child = parentParent.left;

						// check from left up
						while(!(child instanceof Integer)) {
							parent = ((SnailFishNumber) child);
							child = parent.left;
						}
						
						parent.right = ((Integer) child) + ((Integer)this.left);  
					} else {
						parentParent.left = ((Integer)parentParent.left) + ((Integer)this.left);
					}
					break;
				}
			}
			
			parent.left = (Integer) 0;
		} else { // parent.right
			parent.left = ((Integer)parent.left) + ((Integer)this.left);
			
			SnailFishNumber currentParent = parent;
			SnailFishNumber parentParent = currentParent.parent;
			
			while(parentParent != null) {
				if(currentParent == parentParent.right ) {
					currentParent = parentParent;
					parentParent = currentParent.parent;
				} else {
					if(parentParent.right instanceof SnailFishNumber) {
						SnailFishNumber parent = parentParent;
						Object child = parentParent.right;

						// check from left up
						while(!(child instanceof Integer)) {
							parent = ((SnailFishNumber) child);
							child = parent.right;
						}
						
						parent.left = ((Integer) child) + ((Integer)this.right);  
					} else {
						parentParent.right = 
								((Integer)parentParent.right) 
								+ ((Integer)this.right);
					}
					break;
				}
			}
			
			parent.right = (Integer) 0;
		}
	}
	
	private void split(boolean isLeft) {
		int number = isLeft ? (Integer) left : (Integer) right;
		int half = number / 2;
		int rest = number % 2;
		
		SnailFishNumber newSfn = new SnailFishNumber(half, half + rest, this); 
		
		if(isLeft)
			left = newSfn; 
		else
			right = newSfn;
		
	}
	
	public void add(String in) {
		add(new SnailFishNumber(in));
	}
	
	public void add(SnailFishNumber add) {
		this.left = new SnailFishNumber(this.left, this.right, this);
		this.right = add;
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
			
			if(c == '[') {
				i++;
				if(list[i] == '[') {
					left = new SnailFishNumber(list, i, parent);
					i = ((SnailFishNumber) left).i;
				} else {
					left = Integer.parseInt("" + list[i]);
				}
			} else if(c == ',') {
				i++;
				if(list[i] == '[') {
					right = new SnailFishNumber(list, i, parent);
					i = ((SnailFishNumber) right).i;
				} else {
					right = Integer.parseInt("" + list[i]);
				}
			} else if(c == ']') {
				return;
			} 
		}
	}
}
