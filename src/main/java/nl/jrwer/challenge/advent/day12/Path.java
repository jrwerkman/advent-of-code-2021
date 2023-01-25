package nl.jrwer.challenge.advent.day12;

import java.util.ArrayList;
import java.util.List;

public class Path {
    final Cave currentCave;
    final List<Cave> visistedCaves = new ArrayList<>();
    boolean visitedACaveTwice;

    public Path(Cave currentCave) {
        this.currentCave = currentCave;
        this.visistedCaves.add(currentCave);
        this.visitedACaveTwice = false;
    }
    
    public Path(Cave currentCave, Path path) {
        this.currentCave = currentCave;
        this.visitedACaveTwice = path.visitedACaveTwice;
        
        this.visistedCaves.addAll(path.visistedCaves);
        this.visistedCaves.add(currentCave);
    }
    
    public Path(Cave currentCave, Path path, boolean visitedACaveTwice) {
        this.currentCave = currentCave;
        this.visitedACaveTwice = visitedACaveTwice;
        
        this.visistedCaves.addAll(path.visistedCaves);
        this.visistedCaves.add(currentCave);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for(Cave c : visistedCaves)
            sb.append(c).append(',');
        
        return sb.toString();
    }
    
    
}
