package lab;

import java.util.Comparator;

public class PaperComparator implements Comparator<Paper> {
    public int compare(Paper a, Paper b){
        return a.title.compareTo(b.title);
    }
}
