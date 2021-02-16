package bearmaps.proj2c;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome ret;
    private int numOfDequeOperation = 0;
    private ArrayList<Vertex> solution = new ArrayList<>();
    private double totalWeight;
    private double timeSpent;
    private double timeOut;
    private Vertex source;
    private Vertex goal;
    private AStarGraph<Vertex> G;

    private DoubleMapPQ<Vertex> PQ = new DoubleMapPQ<>();
    private MyHashMap<Vertex,Double> distTo = new MyHashMap<>();
    private MyHashMap<Vertex,Vertex> edgeTo = new MyHashMap<>();

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
        source = start;
        goal = end;
        timeOut = timeout;
        G = input;

        Stopwatch sw = new Stopwatch();
        timeSpent = sw.elapsedTime();

        /* to find the solution */
        PQ.add(source,G.estimatedDistanceToGoal(source,goal));
        distTo.put(source,0.0);
        edgeTo.put(source,source);
        //System.out.println("PQ.getSmallest()" + PQ.getSmallest());
        while(PQ.size() != 0 && !PQ.getSmallest().equals(goal) && timeSpent < timeOut) {
            Vertex p = PQ.removeSmallest();
            //System.out.println(p);
            numOfDequeOperation ++;
            if(timeSpent >= timeOut){
                break;
            }
            List<WeightedEdge<Vertex>> neighborEdges = G.neighbors(p);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                if(distTo.containsKey(e.to())){
                    //do nothing
                } else {
                    distTo.put(e.to(), (double) Integer.MAX_VALUE);
                }

                relax(e);
            }
            timeSpent = sw.elapsedTime();
        }

        outcome();
        printPath();
    }


    private void relax(WeightedEdge e){
        Vertex p = (Vertex) e.from();
        Vertex q =(Vertex) e.to();
        double w = e.weight();

        if(distTo.get(p) + w < distTo.get(q)){
            /* update distTo and edgeTo */
            distTo.remove(q);
            double distToq = distTo.get(p) + w;
            distTo.put(q,distToq);
            if(edgeTo.containsKey(q)){
                edgeTo.remove(q);
            } else {
                // do nothing
            }
            edgeTo.put(q,p);

            /* update PQ */
            double totalDisqToGoal = distToq + G.estimatedDistanceToGoal(q,goal);
            if(PQ.contains(q)){
                PQ.changePriority(q,totalDisqToGoal);
            } else{
                PQ.add(q,totalDisqToGoal);
            }
        }
    }

    /* Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time. */
    public SolverOutcome outcome(){
        /* solution is Timeout if overtime  */
        if(timeSpent >= timeOut) {
            ret = SolverOutcome.TIMEOUT;
            System.out.println("TIMEOUT");
        } else if (PQ.size() == 0){          /* solution is unsolvable if overtime or unsolvable */
            ret = SolverOutcome.UNSOLVABLE;
            System.out.println("UNSOLVABLE");
        } else if (PQ.size() != 0 && PQ.getSmallest() == goal){
            ret = SolverOutcome.SOLVED;
            totalWeight = distTo.get(PQ.getSmallest());
            System.out.println("SOLVED");
            System.out.println("Total weight is: " + totalWeight);
        }

        return ret;
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
        return numOfDequeOperation;
    }

    /* The total time spent in seconds by the constructor. */
    public double explorationTime(){
        return timeSpent;
    }

    private void printPath(){
        printHelper(goal);
    }

    private void printHelper(Vertex v){
        if(v.equals(source)){
            System.out.println(v);
            return;
        }
        printHelper(edgeTo.get(v));
        System.out.println(v);
    }
}