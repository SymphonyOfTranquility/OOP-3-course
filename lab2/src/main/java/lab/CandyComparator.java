package lab;

import java.util.Comparator;

public class CandyComparator implements Comparator<Candy> {
    public int compare(Candy a, Candy b){
        return a.name.compareTo(b.name);
    }
}
