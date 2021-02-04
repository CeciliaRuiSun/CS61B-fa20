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
            N.rightchild = add(p, N.rightchild, !orientation);
        } else if (comparison > 0){
            N.leftchild= add(p, N.leftchild, !orientation);
        } else{
            if(comparePoints(N.p,p,!orientation) != 0){
                N.rightchild = add(p, N.rightchild, !orientation);
            } else{
                //do nothing
            }
        }

        return N;
    }

    private int comparePoints(Point a, Point b, boolean orientation){
        if(orientation == horizontal){
            return Double.compare(a.getX(),b.getX());   //?
        } else{
            return Double.compare(a.getY(),b.getY());    //?
        }

    }

    @Override
    public Point nearest(double x, double y){

        return null;
    }

    public static void main(String args[]){
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KdTree kd = new KdTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }

}
