package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import bearmaps.proj2c.streetmap.Node;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
        private List<Node> nodes;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        nodes = this.getNodes();
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        List<Point> allPointsList = new ArrayList<>();
        Map<Point, Long> hm = new HashMap<>();

        /*
        double dis = 99999;
        long id = 0;

        for(Node n: nodes){
            double newDis = cal_distance(lon, n.lon(), lat,  n.lat());
            if(newDis < dis && neighbors(n.id()).size() != 0){
                dis = newDis;
                id = n.id();
            }
            if (n.id() == 760706748) {
                System.out.println("n.id: "+n.id() +", dis:"+newDis+", minDis:"+dis);
            }
        }

        */

        for(Node n: nodes){
            allPointsList.add(new Point(n.lon(),n.lat()));
            hm.put(allPointsList.get(allPointsList.size() - 1),n.id());
        }

       WeirdPointSet ptSet = new WeirdPointSet(allPointsList);
        Point nearestPoint = ptSet.nearest(lon,lat);
        Long id = hm.get(nearestPoint);
        //System.out.println("id: " + id);
        while(neighbors(id).size() == 0){
            allPointsList.remove(nearestPoint);
            ptSet = new WeirdPointSet(allPointsList);
            nearestPoint = ptSet.nearest(lon,lat);
            id = hm.get(nearestPoint);
            //System.out.println("id in while loop: " + id);
        }


        return id;
    }

    private double cal_distance(double lon, double pX, double lat, double pY){
        return super.distance(lon, pX, lat, pY);
    }

    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        return new LinkedList<>();
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        return new LinkedList<>();
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
