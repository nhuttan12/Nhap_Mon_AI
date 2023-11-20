package bt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<>();
    Queen queen;
    Random rd = new Random();
    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }
    public Node execute() {
    // Enter your code here
        initPopulation();
        int k=0;
        while (k++<MAX_ITERATIONS){
            List<Node> new_population=new ArrayList<>();
            for (int i = 0; i < POP_SIZE; i++) {
                Node x=getParentByRandomSelection();
                Node y=getParentByRandomSelection();
                Node child=reproduce(x,y);
                if(rd.nextDouble()<MUTATION_RATE) mutate(child);
                if(child.getH()==0) return child;
            }
        }
        return null;
    }
    // Mutate an individual by selecting a random Queen and
    //move it to a random row.
    public void mutate(Node node) {
    //  Enter your code here
        node.setRow(node.getRow(rd.nextInt(Node.N)), rd.nextInt(8));
    }
    //Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
    // Enter your code here
        Node child=new Node();
        child.generateBoard();
        int c=rd.nextInt(Node.N);
        for (int i = 0; i < c; i++) {
            child.setRow(i, x.getRow(i));
        }
        for (int i = c+1; i < Node.N; i++) {
            child.setRow(i, y.getRow(i));
        }
        return child;
    }
    // Select K individuals from the population at random and
    //select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
    // Enter your code here
    	Random rand=new Random();
    	int k=50;
    	Node parent=new Node();
    	for (int i = 0; i < k; i++) {
			Node node=population.get(rand.nextInt(population.size()));
			if(node.getH()<parent.getH()) {
				parent=new Node(node);
			}
		}
        return parent;
    }
    //Select a random parent from the population
    public Node getParentByRandomSelection() {
    // Enter your code here
    	Random random=new Random();
    	Node parent=population.get(random.nextInt(population.size()));
        return parent;
    }
}

