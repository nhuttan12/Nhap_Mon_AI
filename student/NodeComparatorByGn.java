package student;

import java.util.Comparator;

public class NodeComparatorByGn implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        int re=Double.compare(o1.getH(),o2.getH());
        if(re==0) return o1.getLabel().compareTo(o2.getLabel());
        else return re;
    }
}
