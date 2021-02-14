package bearmaps.proj2c;
import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome ret;
    private ArrayList<Vertex> ls = new ArrayList<>();
    private double totalWeight;

    /**finds the solution, computing everything necessary for all other methods to
     * return their results in constant time.
     * timeout passed in is in seconds.
     *
     * @param input
     * @param start
     * @param end
     * @param timeout
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){

    }

    /**
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * @return
     */
    public SolverOutcome outcome(){

    }

    /**
     * A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    public List<Vertex> solution(){
        if(ret == SolverOutcome.TIMEOUT || ret == SolverOutcome.UNSOLVABLE){
            return null;
        } else{
            return ls;
        }
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     * @return
     */
    public double solutionWeight(){
        if(ret == SolverOutcome.TIMEOUT || ret == SolverOutcome.UNSOLVABLE){
            return 0;
        } else{
            return totalWeight;
        }
    }

    /**
     * The total number of priority queue dequeue operations.
     * @return
     */
    public int numStatesExplored(){

    }

    /**
     * The total time spent in seconds by the constructor.
     */
    public double explorationTime(){

    }
}