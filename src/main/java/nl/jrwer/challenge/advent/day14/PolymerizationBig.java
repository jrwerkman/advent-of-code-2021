package nl.jrwer.challenge.advent.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolymerizationBig {
	final List<PairInsertionRule> rules;
	List<Pair> pairs = new ArrayList<>();
	Pair lastPair;
	final PolymerTemplate template;
	
	public PolymerizationBig(List<PairInsertionRule> rules, PolymerTemplate template) {
		this.rules = rules;
		this.template = template;
	}
	
	public void process(int times) {
		firstProcess();
		
		for(int i=0; i<times; i++) 
			processBig();
	}
	
	private void firstProcess() {
		Pair pair = null;
	
		for(int i=0; i<template.size() - 1; i++) {
			char first = template.get(i);
			char second = template.get(i+1);
			
			pair = new Pair(first, second);
			addPair(pairs, pair);
		}
		
		lastPair = pair;
	}
	
	private void addPair(List<Pair> pairs, Pair p) {
		int index = pairs.indexOf(p);
		
		if(index > 0)
			pairs.get(index).increment(p.times);
		else
			pairs.add(p);
	}
	
	private void processBig() {
		List<Pair> nextPairs = new ArrayList<>();
		
		for(Pair pair : pairs) {
			if(pair.times > 0) {
				for(PairInsertionRule rule : rules) {
					if(pair.match(rule.first, rule.second)) {
						Pair first = new Pair(rule.first, rule.between, pair.times);
						Pair second = new Pair(rule.between, rule.second, pair.times);
						
						addPair(nextPairs, first);
						addPair(nextPairs, second);
						
						if(lastPair.equals(pair))
							lastPair = second;
					}
				}
			}
		}
		
		pairs = nextPairs;
	}
	
	public long result() {
		Map<Character, Long> quantities = getQuantities();
		
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
	
	private Map<Character, Long> getQuantities() {
		Map<Character, Long> quantities = new HashMap<>();
		
		for(Pair pair : pairs) {
			addCharacter(quantities, pair.first, pair.times);
			
			if(pair.equals(lastPair))
				addCharacter(quantities, pair.second, 1L);
		}
		
		return quantities;
	}
	
	private void addCharacter(Map<Character, Long> quantities, Character c, Long times) {
		if(quantities.containsKey(c))
			quantities.put(c, quantities.get(c) + times);
		else
			quantities.put(c, times);
	}
}
