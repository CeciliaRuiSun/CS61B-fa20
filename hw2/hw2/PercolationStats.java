package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fraction;
    private int numOfExp;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N <= 0){
            throw new IllegalArgumentException("N should be more than 0");
        }
        if(T <= 0){
            throw new IllegalArgumentException("T should be more than 0");
        }

        fraction = new double[T];
        numOfExp = T;

        for(int i = 0;i < T;i ++) {
            //initialize all sites to be blocked
            Percolation pc = pf.make(N);

            while (!pc.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                pc.open(row, col);
            }

            fraction[i] = (double) pc.numberOfOpenSites()/(N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fraction);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - 1.96 * stddev() / Math.sqrt(numOfExp);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean() + 1.96 * stddev() / Math.sqrt(numOfExp);
    }

}
