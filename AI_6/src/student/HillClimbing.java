package student;

public class HillClimbing {
    public Node execute(Node start){
        Node current=new Node(start);
        Node next=new Node();
        while(true){
            next=current.getBestCadidates();
            if(next.getH()<current.getH()){
                current=next;
            }else {
                return current;
            }
        }
    }
    public Node executeHillClimbingWithRandomRestart(Node start){
        Node state=execute(start);
        while (state.getH()!=0){
            state=new Node();
            state.generateBoard();
            state=execute(start);
        }
        return state;
    }
}
