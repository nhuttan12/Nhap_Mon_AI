package k21;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class UniformCostSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node>frontier=new PriorityQueue<>(new NodeComparator());
		List<Node>explored=new ArrayList<>();
		Node current=frontier.poll();
		if(current.getLabel().equals(goal)) return current;
		else {
			explored.add(current);
			List<Edge>children=current.getChildren();
			for (Edge child: children) {
				Node n=child.getEnd();
				if(!explored.contains(n)&&!frontier.contains(n)) {
					frontier.add(n);
					n.setParent(current);
					n.setPathCost(current.getPathCost()+child.getWeight());
				}else if(n.getPathCost()>current.getPathCost()+child.getWeight()){
					n.setPathCost(current.getPathCost()+child.getWeight());
					n.setParent(current);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node>frontier=new PriorityQueue<>(new NodeComparator());
		List<Node>explored=new ArrayList<>();
		frontier.add(root);
		boolean seen=false;
		while(!frontier.isEmpty()) {
			Node current=frontier.remove();
			explored.add(current);
			if(current.getLabel().equals(start)) {
				seen=true;
				frontier.clear();
			}
			if(current.getLabel().equals(goal)&&seen) {
				return current;
			}else if(current.getLabel().equals(goal)&&seen==false) {
				continue;
			}else {
				current=frontier.poll();
				List<Edge>children=current.getChildren();
				for (Edge child : children) {
					double newPathCost=current.getPathCost()+child.getWeight();
					Node end=child.getEnd();
					if(!frontier.contains(end)&&!explored.contains(end)) {
						frontier.add(end);
						end.setPathCost(newPathCost);
						end.setParent(current);
					}else if(frontier.contains(end)&&end.getPathCost()>newPathCost) {
						end.setPathCost(newPathCost);
						end.setParent(current); 
					}
				}
			}
		}
		return null;
	}
	public Node execute(Node root, String goal, int limitedDepth) {
		Stack<Node>frontier=new Stack<>();
		frontier.add(root);
		List<Node>explored=new ArrayList<>();
		while(!frontier.isEmpty()) {
			Node current=frontier.pop();
			if(current.getLabel().equals(goal)) return current;
			else {
				if(current.getDepth()<limitedDepth) {
					List<Node> children=current.getChildrenNodes();
					for (Node child : children) {
						if(!explored.contains(child)||!frontier.contains(child)) {
							frontier.add(child);
							child.setParent(current);
							child.setDepth(current.getDepth()+1);
						}
					}
				}
			}
		}
		return null;
	}
	
}
