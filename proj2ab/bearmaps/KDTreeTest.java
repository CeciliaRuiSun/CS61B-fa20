package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class KDTreeTest {

    private static Random r = new Random(500);

    //simple test from lecture example
    @Test
    public void testSimpleTree() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KdTree kd = new KdTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = kd.nearest(0,7);
        assertEquals(p6,ret);
    }

    /* Test nearest by a list a randomized points
    *  Author: Josh Hug
    * */
    private Point RandomPoint(){
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x,y);
    }

    private List<Point> randomPoints(int N){
        List<Point> points = new ArrayList<Point>();
        for(int i = 0;i < N;i ++){
            points.add(RandomPoint());
        }
        return points;
    }

    @Test
    public void testNearest(){
        List<Point> points1000 = randomPoints(1000);
        NaivePointSet nps = new NaivePointSet(points1000);
        KdTree kd = new KdTree(points1000);

        List<Point> queryPoints = randomPoints(200);
        for(Point p: queryPoints){
            Point expected = nps.nearest(p.getX(),p.getY());
            Point actual = kd.nearest(p.getX(),p.getY());
            assertEquals(expected,actual);
        }
    }
}
