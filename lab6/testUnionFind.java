import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {
    @Test
    public void testConnect(){
        //  -1,-1,-1,-1,-1,-1,-1,-1
        // {0,  1, 2, 3, 4, 5, 6, 7}
        UnionFind disjoinSet = new UnionFind(8);
        //  -1,-1, 3,-2,-1,-1,-1,-1
        // {0,  1, 2, 3, 4, 5, 6, 7}
        disjoinSet.connect(2,3);
        assertEquals(3, disjoinSet.parent(2));
        assertEquals(-2, disjoinSet.parent(3));
        //  -1,-1, 3,-3,-1, 3,-1,-1
        // {0,  1, 2, 3, 4, 5, 6, 7}

        disjoinSet.connect(2,5);
        assertEquals(3,disjoinSet.find(2));

        assertEquals(3, disjoinSet.parent(5));
        assertEquals(-3, disjoinSet.parent(3));
    }

}
