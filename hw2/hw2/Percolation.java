package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.Arrays;

public class Percolation {
    private WeightedQuickUnionUF unionSites;     //[0,1,2,3,... n*n-1]
    private int[][] percoSites;                  //n * n matrix
    private Boolean[][] openSites;              //true if open, false if not open
    private boolean ispercolate = false;
    private int numOfBotSite = 0;               //number of open bottom sites
    private ArrayList<Integer> botOpenSites;    //set of open bottom sites

    private int nums;                           //total number of sites
    private int numOfRow;                       //number of rows/columns
    private int numOfOpenSites = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException("N should be more than 0");
        }

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

        botOpenSites = new ArrayList<Integer>();
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row < 0 || row >= numOfRow){
            throw new IllegalArgumentException("Row number invalid");
        }

        if(col < 0 || col >= numOfRow){
            throw new IllegalArgumentException("Collum number invalid");
        }

        if(openSites[row][col]){
            //do nothing
        } else{
            openSites[row][col] = true;  //open
            numOfOpenSites ++;

            if(row == 0){
                //top row site --> full, connect to virtual top site
                unionSites.union(xyTo1D(row,col),nums);
            }

            if(row == numOfRow - 1){
                numOfBotSite ++;
                botOpenSites.add(xyTo1D(row,col));
            }

            //merge with other sets
            if(row - 1 >= 0 && openSites[row - 1][col]){
                //above site
                unionSites.union(xyTo1D(row,col),xyTo1D(row - 1,col));
            }
            if(row + 1 < numOfRow && openSites[row + 1][col]){
                //below site
                unionSites.union(xyTo1D(row,col),xyTo1D(row + 1,col));
            }
            if(col - 1 >= 0 && openSites[row][col - 1]){
                //left site
                unionSites.union(xyTo1D(row,col),xyTo1D(row,col - 1));
            }
            if(col + 1 < numOfRow && openSites[row][col + 1]){
                //left site
                unionSites.union(xyTo1D(row,col),xyTo1D(row,col + 1));
            }

        }
    }

    public int xyTo1D (int r, int c){
        return r * numOfRow + c;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return openSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return unionSites.find(xyTo1D(row ,col)) == unionSites.find(nums);
    }

    //number of open sites
    public int numberOfOpenSites(){
        return numOfOpenSites;
    }

    public void helperPercolates(){
        if(numOfBotSite > 0) {
            for (int i = 0; i < botOpenSites.size(); i++) {
               if(unionSites.find(botOpenSites.get(i)) == unionSites.find(nums)){
                   ispercolate = true;
               }
            }
        }
    }
    // does the system percolate?
    public boolean percolates(){
        if(ispercolate) {
        } else{
            helperPercolates();
        }
        return ispercolate;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){
        Percolation test = new Percolation(5);
        test.open(0,1);
        System.out.println("If (0,1) is full " + test.isFull(0,1));   //true
        test.open(1,3);
        System.out.println("If (1,3) is full " + test.isFull(1,3));   //false
        test.open(1,2);
        test.open(1,1);
        System.out.println("If (1,3) is full " + test.isFull(1,3));  //true
        System.out.println("If (1,2) is full " + test.isFull(1,2));  //true
        test.open(2,2);
        System.out.println("If (2,2) is full " + test.isFull(2,2));  //true
        test.open(4,2);
        System.out.println("If (4,2) is full " + test.isFull(4,2));  //false
        test.open(3,2);
        System.out.println("If (3,2) is full " + test.isFull(3,2));  //true
        System.out.println("If (4,2) is full " + test.isFull(4,2));  //true
        System.out.println("If percolated? " + test.percolates());  //true

        System.out.println("Number of open sites " + test.numberOfOpenSites()); //7


    }



}
