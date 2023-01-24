package nl.jrwer.challenge.advent.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.day04.Part1;

public abstract class MultipleLinesInputLoader<T> {
	
	private final String file;
	private final int amountLines;
	private List<T> list = new ArrayList<>();
	
	public MultipleLinesInputLoader(String file, int amountLines) {
		this.file = file;
		this.amountLines = amountLines;
	}
	
	public List<T> getInput() {
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(file);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	int i = 0;
        	String[] lines = new String[this.amountLines];
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	lines[i] = line;
            	i++;
            	
            	if(i == amountLines) {
            		i=0;
	            	T obj = handleLines(lines);
	            	
	            	if(obj != null)
	            		list.add(obj);
            	}
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        postProcess(list);
        
        return list;
	}
	
	protected abstract void postProcess(List<T> objects);
	protected abstract T handleLines(String[] lines);
}
