package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd(){
        ArrayHeapMinPQ example = new ArrayHeapMinPQ(10);
        example.add("a",1);
        example.add("j",2);

        assertEquals(2, example.size());
        assertEquals("a", example.getSmallest());
        assertEquals(true, example.contains("a"));
        assertEquals(true, example.contains("j"));

        example.add("c",3);
        example.add("d",4);
        example.add("e",5);
        example.add("f",6);
        example.add("g",7);
        example.add("h",8);
        assertEquals(8, example.size());

        example.add("z",0);
        assertEquals("z", example.getSmallest());

    }

    @Test
    public void testMassAdd(){
        ArrayHeapMinPQ b = new ArrayHeapMinPQ(10);

       long before = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            b.add("hi" + i, i);
            assertEquals(i+1,b.size());
            assertTrue(b.contains("hi" + i));
        }

        long after = System.currentTimeMillis();
        System.out.println("time used is: " + (after - before));
    }


    @Test
    public void testSize(){
        ArrayHeapMinPQ example = new ArrayHeapMinPQ(10);
        assertEquals(0, example.size());
        example.add("a",1);
        assertEquals(1, example.size());
        example.add("j",2);
        assertEquals(2, example.size());
        example.add("b",2);
        assertEquals(3, example.size());
        example.add("h",3);
        assertEquals(4, example.size());
        example.removeSmallest();
        assertEquals(3, example.size());
        example.removeSmallest();
        assertEquals(2, example.size());
        example.removeSmallest();
        assertEquals(1, example.size());
        example.removeSmallest();
        assertEquals(0, example.size());
    }

    @Test
    public void testChangePriority(){
        ArrayHeapMinPQ example = new ArrayHeapMinPQ(10);
        example.add("a",1);
        example.add("j",2);
        assertEquals(true, example.contains("a"));
        example.add("c",3);
        example.add("d",4);
        example.add("e",5);
        example.add("f",6);
        example.add("g",7);
        example.add("h",8);

        example.changePriority("h", 0);
        example.changePriority("h", 8);
        example.print();
        //assertEquals("a",example.getSmallest());
        example.changePriority("a", 3);
        example.print();
        assertEquals("j",example.getSmallest());
    }
}
