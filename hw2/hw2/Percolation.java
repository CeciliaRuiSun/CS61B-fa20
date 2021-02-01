package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private WeightedQuickUnionUF unionSites;
    private int[][] percoSites;
    private Boolean[][] openSites;

    private int nums; //number of components
    private int numOfRow;  //number of rows/columns
    private int numOfOpenSites = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        nums = N * N;
        numOfRow = N;
        // unionSites:
        // 0,   1,   2,    3,...,n-1
        // n, n+1, n+2,  n+3,...,2n-1
        // 2n,2n+1,2n+2,2n+3,...,3n-1
        //...
        // n(n-1),   ...        ,n*n-1
        unionSites = new WeightedQuickUnionUF(nums + 2);
        percoSites = new int[numOfRow][numOfRow];
        openSites = new Boolean[numOfRow][numOfRow];

        for(int i = 0;i < numOfRow;i ++){
            for(int j = 0;j < numOfRow;j ++){
                openSites[i][j] = false;
            }
        }

        //connect top sites to virtual top site nums
        for(int i = 0; i < numOfRow; i ++){
            unionSites.union(nums,i);
        }

        //connect bottom sites to virtual bottom site nums + 1
        for(int i = numOfRow * (numOfRow - 1); i < nums; i ++){
            unionSites.union(nums + 1,i);
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row < 0 || row > numOfRow){
            throw new IllegalArgumentException("Row number invalid");
        }

        if(col < 0 || col > numOfRow){
            throw new IllegalArgumentException("Collum number invalid");
        }

        if(openSites[row - 1][col - 1]){
            //do nothing
        } else{
            openSites[row - 1][col - 1] = true;  //open
            numOfOpenSites ++;

            //merge with other sets
            if(row - 2 >= 0 && openSites[row - 2][col - 1]){
                //above site
                unionSites.union(xyTo1D(row - 1,col - 1),xyTo1D(row - 2,col - 1));
            }
            if(row < numOfRow && openSites[row][col - 1]){
                //below site
                unionSites.union(xyTo1D(row - 1,col - 1),xyTo1D(row,col - 1));
            }
            if(col - 2 >= 0 && openSites[row - 1][col - 2]){
                //left site
                unionSites.union(xyTo1D(row - 1,col - 1),xyTo1D(row - 1,col - 2));
            }
            if(col < numOfRow && openSites[row - 1][col]){
                //left site
                unionSites.union(xyTo1D(row - 1,col - 1),xyTo1D(row - 1,col));
            }

        }
    }

    public int xyTo1D (int r, int c){
        return r * numOfRow + c;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return openSites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        //System.out.println(xyTo1D(row,col));
        return unionSites.find(xyTo1D(row - 1,col - 1)) == unionSites.find(nums);
    }

    //number of open sites
    public int numberOfOpenSites(){
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return unionSites.find(nums) == unionSites.find(nums + 1);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){
        Percolation test = new Percolation(5);
        test.open(1,2);
        System.out.println("If (1,2) is full " + test.isFull(1,2));   //true
        test.open(2,4);
        System.out.println("If (2,4) is full " + test.isFull(2,4));   //false
        test.open(2,3);
        test.open(2,2);
        System.out.println("If (2,4) is full " + test.isFull(2,4));  //true
        System.out.println("If (2,3) is full " + test.isFull(2,3));  //true
        test.open(3,3);
        System.out.println("If (3,3) is full " + test.isFull(3,3));  //true
        test.open(5,3);
        System.out.println("If (5,3) is full " + test.isFull(5,3));  //false
        test.open(4,3);
        System.out.println("If (4,3) is full " + test.isFull(4,3));  //true
        System.out.println("If (5,3) is full " + test.isFull(5,3));  //true
        System.out.println("If percolated? " + test.percolates());  //true

        System.out.println("Number of open sites " + test.numberOfOpenSites()); //7
    }


}
