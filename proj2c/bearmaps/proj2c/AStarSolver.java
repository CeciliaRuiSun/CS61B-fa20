package bearmaps.proj2c;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome ret;
    private ArrayList<Vertex> solution = new ArrayList<>();
    private double totalWeight;
    private double timeSpent;
    private DoubleMapPQ<Vertex> PQ;
    private double minDistTo = 0;


    /**finds the solution, computing everything necessary for all other methods to
     * return their results in constant time.
     * timeout passed in seconds.
     *
     * @param input
     * @param start
     * @param end
     * @param timeout
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        timeSpent = sw.elapsedTime();

        /* to find the solution */
        PQ.add(start,input.estimatedDistanceToGoal(start,end));
        while(PQ.size() != 0 && PQ.getSmallest() != end && timeSpent < timeout) {
            Vertex p = PQ.removeSmallest();
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                /* relax edge */
                Vertex edgeFrom =(Vertex) e.from();
                Vertex edgeTo =(Vertex) e.to();
                double edgeWeight = e.weight();

                PQ.add(e.to(),input.estimatedDistanceToGoal(p,e.to())+
                        input.estimatedDistanceToGoal(e.to(),end));
            }
            timeSpent = sw.elapsedTime();
        }

        /* solution is unavailable if overtime or unsolvable */
        if(timeSpent > timeout) {       //???
            ret = SolverOutcome.UNSOLVABLE;
        }
    }


    private void relax(AStarGraph<Vertex> input, WeightedEdge e){
        Vertex p =(Vertex) e.from();
        Vertex q =(Vertex) e.to();
        double w = e.weight();

        if(input.estimatedDistanceToGoal(p,)
    }

    /* Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time. */
    public SolverOutcome outcome(){

    }

    /* A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE. */
    public List<Vertex> solution(){
        if(ret == SolverOutcome.TIMEOUT || ret == SolverOutcome.UNSOLVABLE){
            return null;
        } else{
            return solution;
        }
    }

    /*The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE. */
    public double solutionWeight(){
        if(ret == SolverOutcome.TIMEOUT || ret == SolverOutcome.UNSOLVABLE){
            return 0;
        } else{
            return totalWeight;
        }
    }

    /* The total number of priority queue dequeue operations. */
    public int numStatesExplored(){

    }

    /* The total time spent in seconds by the constructor. */
    public double explorationTime(){
        return timeSpent;
    }
}