package bearmaps;
import java.util.List;

public class KdTree implements PointSet{
    private static final boolean horizontal = false;
    private static final boolean vertical = true;
    private Node root;

    private class Node{
        public Point p;
        public boolean orientation;
        public Node leftchild;   // also refers to the down child
        public Node rightchild;   // also refers to the up child

        public Node(Point givenP, boolean po){
            p = givenP;
            orientation = po;
        }
    }

    public KdTree(List<Point> points){
        for(Point p: points){
             root = add(p,root,horizontal);
        }
    }

    private Node add(Point p, Node N, boolean orientation){
        if(N == null){
            return new Node(p, orientation);
        }
        int comparison = comparePoints(N.p,p,orientation);
        if(comparison < 0){
            //N.p < p
            N.rightchild = add(p, N.rightchild, !orientation);
        } else if (comparison > 0){
            //N.p > p
            N.leftchild= add(p, N.leftchild, !orientation);
        } else{
            if(comparePoints(N.p,p,!orientation) != 0){
                //one axis same, the other differ
                N.rightchild = add(p, N.rightchild, !orientation);
            } else{
                //two identical points
                //do nothing
            }
        }

        return N;
    }

    private int comparePoints(Point a, Point b, boolean orientation){
        if(orientation == horizontal){
            return Double.compare(a.getX(),b.getX());
        } else{
            return Double.compare(a.getY(),b.getY());
        }

    }

    private double axisDistance(Point a, Point b, boolean orientation){
        if(orientation == horizontal){
            return Math.abs(a.getX() - b.getX());
        } else{
            return Math.abs(a.getY() - b.getY());
        }

    }


    @Override
    public Point nearest(double x, double y){
        //always searches the left subtree, then the right, and doesn’t do any pruning
        Point goal = new Point(x,y);
        Node result = nearest(root,goal,root);
        if (result == null) {
            return null;
        }
        return result.p;
    }


    public Node nearest(Node N,Point goal,Node best){
        if(N == null){
            return best;
        }
        //System.out.println("Entering "+ N.p.toString() + " Current best: "+best.p.toString());

        Node goodSide = N.rightchild;
        Node badSide = N.leftchild;

        if(Point.distance(N.p,goal) < Point.distance(best.p,goal)) {
            best = N;
        }
        int comparison = comparePoints(goal,best.p,N.orientation);

        if(comparison >= 0){
            //p >= root.p, go to right side
        } else{
            goodSide = N.leftchild;
            badSide = N.rightchild;
        }
        best = nearest(goodSide,goal,best);
        //System.out.println("Returned best is "+ best);

        /*
            Pruning rule, bad side could still have something useful
         */
        if (axisDistance(goal,N.p,N.orientation) < Point.distance(best.p,goal)){
            best = nearest(badSide, goal, best);
        }

        //System.out.println("Leaving "+ N.toString() + " Current best: "+best.p.toString());
        return best;
    }


    public static void main(String args[]){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KdTree kd = new KdTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = kd.nearest(0,7);
        System.out.println(ret.toString());  //1,5
    }

}
