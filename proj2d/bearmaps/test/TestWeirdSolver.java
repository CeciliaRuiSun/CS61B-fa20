package bearmaps.test;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2d.AugmentedStreetMapGraph;
import org.junit.Before;
import org.junit.Test;
import bearmaps.proj2d.Router;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestWeirdSolver {

    @Test
    public void testWeirdSolver(){
        List<Point> allPoints = new ArrayList<>();
        allPoints.add(new Point(37.8754169, -122.2386624));
        allPoints.add(new Point(37.8638211000, -122.2467303000));

        WeirdPointSet ptSet = new WeirdPointSet(allPoints);
        Point nearestPoint = ptSet.nearest(37.873839,-122.2335);

        System.out.println(nearestPoint);

    }
}
