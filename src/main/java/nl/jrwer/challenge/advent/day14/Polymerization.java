package nl.jrwer.challenge.advent.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polymerization {
	final List<PairInsertionRule> rules;
	final PolymerTemplate template;
	
	public Polymerization(List<PairInsertionRule> rules, PolymerTemplate template) {
		this.rules = rules;
		this.template = template;
	}
	
	public void process(int times) {
		for(int i=0; i<times; i++)
			process();
	}
	
	public void process() {
		for(int i=0; i<template.size() - 1; i++)
			if(match(i))
				i++;
	}
	
	private boolean match(int index) {
		char first = template.get(index);
		char second = template.get(index+1);
		
		for(PairInsertionRule rule : rules)
			if(rule.match(first, second)) {
				template.add(index + 1, rule.between);
				
				return true;
			}
		
		return false;
	}
	
	public long result() {
		Map<Character, Long> quantities = new HashMap<>();
		
		for(int i=0; i<template.size(); i++) {
			Character c = template.get(i);
			
			if(quantities.containsKey(c))
				quantities.put(c, quantities.get(c) + 1);
			else
				quantities.put(c, 1L);
		}
		
		Long lowest = Long.MAX_VALUE;
		Long highest = Long.MIN_VALUE;
		
		for(Long v : quantities.values()) {
			if(v < lowest)
				lowest = v;
			
			if(v > highest)
				highest = v;
		}
		
		return highest - lowest;
	}
	
	@Override
	public String toString() {
		return template.toString();
	}
}
