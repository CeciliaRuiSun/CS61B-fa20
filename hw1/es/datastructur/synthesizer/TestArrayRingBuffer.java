package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {


    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
    }

    @Test
    public void testOtherFunctions(){
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        arb.enqueue(4);
        arb.enqueue(3);
        assertEquals(4,arb.dequeue());
        assertEquals(3,arb.peek());
        arb.enqueue(2);
        assertEquals(2,arb.fillCount());

    }
}
