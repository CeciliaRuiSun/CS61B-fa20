package hw2;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Simulation {
    private static int nums = 20;          //nums * nums matrix
    private static int T = 20;            //number of trials
    private static double p = 0.55;         //each site has a probability of p open
    private static int res = 0;   //1 if percolates, 0 if not percolate

    public static void oneTimeRun(Percolation pc, int n){
        PercolationVisualizer vis = new PercolationVisualizer();
        //vis.draw(pc, n);
        for(int i = 0;i < n;i ++){
            for(int j = 0;j < n;j ++){
                double randNum = StdRandom.uniform();
                if(randNum < p){
                    pc.open(i,j);
                }
            }
        }
        //vis.draw(pc, n);
        if(pc.percolates()){
            res ++ ;
        }

        //StdDraw.text(.75 * n, -n * .025, "p = "+ p + ", res = " + res);
    }

    public static void runTTime(){
        int numOfRun = T;
        while(numOfRun-- > 0){
            Percolation perc = new Percolation(nums);
            oneTimeRun(perc, nums);
        }
    }

    public static void main(String args[]){
        runTTime();
        double ProOfPercolation = (double) res/T;
        System.out.println("when p is "+ p + ", chance of percolation is " + ProOfPercolation);
    }
}
