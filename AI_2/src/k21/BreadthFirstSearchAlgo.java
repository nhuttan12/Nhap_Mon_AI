package k21;

import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo{
	//for graph
//	@Override
//	public Node execute(Node root, String goal) {
//		// TODO Auto-generated method stub
//		Queue<Node> frontier= new LinkedList<>();
//		frontier.add(root);
//		//graph
//		List<Node> explored=new LinkedList<>();
//		while(!frontier.isEmpty()) {
//			Node current=frontier.poll();
//			if(current.getLabel().equals(goal)) return current;
//			//graph
//			else explored.add(current);
//			//
//			List<Node> children= current.getChildrenNodes();
//			for (Node child: children) {
//				//graph
//				if(!frontier.contains(child)&&!explored.contains(child)) {
//				//
//					frontier.add(child);
//					child.setParent(current);
//				}
//			}
//		}
//		return null;
//	}
	//for tree
	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier= new LinkedList<>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current=frontier.poll();
			if(current.getLabel().equals(goal)) return current;
			List<Node> children= current.getChildrenNodes();
			for (Node child: children) {
					frontier.add(child);
					child.setParent(current);
			}
		}
		return null;
	}
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started=false;
		Queue<Node> frontier = new LinkedList<>();
		List<Node> explored =new ArrayList<>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current=frontier.poll();
			if(current.getLabel().equals(goal)) return current;
			else explored.add(current);
			List<Node>children=current.getChildrenNodes();
			for (Node child : children) {
				if(!frontier.contains(child)&&!explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
			if(current.getLabel().equals(start)&&started) return current;
			else {
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
		}
		return null;
	}
	
}
