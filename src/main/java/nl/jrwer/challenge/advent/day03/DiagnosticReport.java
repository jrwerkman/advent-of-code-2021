package nl.jrwer.challenge.advent.day03;
	class DiagnosticReport {
		int totalLines = 0;
		int[] totalOnes;
		
		public DiagnosticReport(int positions) {
			totalOnes = new int[positions];
			
//			for(int i=0; i<totalOnes.length; i++)
//				totalOnes[i] = 0;
		}
		
		public void add(Data data) {
			totalLines++;
			
			for(int i=0; i<data.data.length; i++) 
				if(data.data[i])
					totalOnes[i]++;
		}
		
		/**
		 * most common bit
		 * 
		 * @return
		 */
		public int getGammaRate() {
			StringBuilder sb = new StringBuilder();
			
			for(int totalOne : totalOnes) {
				if(totalLines / totalOne < 2)
					sb.append('0');
				else
					sb.append('1');
			}
			
			return Integer.parseInt(sb.toString(), 2);
		}

		/**
		 * least common bits
		 * 
		 * @return
		 */
		public int getEpsilonRate() {
			StringBuilder sb = new StringBuilder();
			
			for(int totalOne : totalOnes) {
				if(totalLines / totalOne < 2)
					sb.append('1');
				else
					sb.append('0');
			}
			
			return Integer.parseInt(sb.toString(), 2);
		}
	}