package student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements  IInformedSearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node>frontier=new PriorityQueue<>(new NodeComparatorByGn());
        ArrayList<Node>explored=new ArrayList<>();
        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current=frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            else{
                explored.add(current);
                List<Edge>children=current.getChildren();
                for (Edge edge : children) {
                    Node child = edge.getEnd();
                    if(!explored.contains(child)&&!frontier.contains(child)){
                        frontier.add(child);
                        child.setParent(current);
                        child.setG(current.getG()+edge.getWeight());
                    }else if (frontier.contains(child)&&(child.getG()>current.getG()+edge.getWeight())){
                        frontier.remove(child);
                        child.setG(current.getG()+edge.getWeight());
                        frontier.add(child);
                        child.setParent(current);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        boolean started=false;
        PriorityQueue<Node>frontier=new PriorityQueue<>(new NodeComparatorByGn());
        ArrayList<Node>explored=new ArrayList<>();
        frontier.add(root);
        while ((!frontier.isEmpty())){
            Node current=frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            else {
                explored.add(current);
                List<Edge>children=current.getChildren();
                for (Edge edge : children) {
                    Node child = edge.getEnd();
                    if(!frontier.contains(child)&&!explored.contains(child)){
                        frontier.add(child);
                        child.setParent(current);
                        child.setG(current.getG()+edge.getWeight());
                    }else if (frontier.contains(child)&&current.getG()>current.getG()+edge.getWeight()){
                        frontier.remove(child);
                        child.setG(current.getG()+edge.getWeight());
                        child.setParent(current);
                        frontier.add(child);
                    }
                }
                if(current.getLabel().equals(start)&&started) return current;
                else {
                    frontier.clear();
                    explored.clear();
                    current.setParent(null);
                }
            }
        }
        return null;
    }
}
