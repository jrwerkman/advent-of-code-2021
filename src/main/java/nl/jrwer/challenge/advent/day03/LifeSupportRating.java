package nl.jrwer.challenge.advent.day03;

import java.util.ArrayList;
import java.util.List;

class LifeSupportRating {
		private static final short BITS = 12;
		List<Data> dataList;
		
		public LifeSupportRating(List<Data> dataList) {
			this.dataList = dataList;
		}
		
		public Data getOxygenGeneratorRating() {
			return getList(true);
		}
		
		public Data getCO2ScrubberRating() {
			return getList(false);
		}
		
		private Data getList(boolean common) {
			List<Data> result = dataList;

			for(int i=0; i<BITS; i++) {
				result = getSubsetBits(result, i, common);
				
				if(result.size() == 1)
					break;
			}
			
			if(result.size() != 1)
				throw new RuntimeException("Expected 1 result");
			
			return result.get(0);
		}
		
		private List<Data> getSubsetBits(List<Data> data, int position, boolean common) {
			List<Data> zero = new ArrayList<>();
			List<Data> one = new ArrayList<>();
			
			for(Data item : data) {
				if(item.data[position])
					one.add(item);
				else
					zero.add(item);
			}

//			System.out.println("Subset list items ("+position+"), (0) size: " + zero.size());
//			System.out.println("Subset list items ("+position+"), (1) size: " + one.size());

			if((zero.size() > one.size() && common) 
					|| (zero.size() <= one.size() && !common))
				return zero;
			else
				return one;
		}
	}