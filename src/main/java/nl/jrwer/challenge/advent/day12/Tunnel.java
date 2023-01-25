package nl.jrwer.challenge.advent.day12;

import java.util.Objects;

public class Tunnel {
    final Cave a, b;
    
    public Tunnel(Cave a, Cave b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Tunnel) {
            Tunnel p = (Tunnel) o;
            
            return a.equals(p.a) && b.equals(p.b);
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return a + "-" + b;
    }
}
