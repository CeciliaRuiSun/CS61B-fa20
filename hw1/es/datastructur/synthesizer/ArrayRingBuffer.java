package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* capacity */
    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int cap) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        capacity = cap;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[cap];
    }

    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        rb[last] = x;
        fillCount ++;
        if (last == capacity - 1){
            last = 0;
        } else {
            last++;
        }
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        T item = rb[first];
        rb[first] = null;
        if(first == capacity - 1){
            first = 0;
        } else{
            first ++;
        }
        fillCount --;
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        return rb[first];
    }

/*    public void bufferPrint(){
        System.out.print(rb[first + 1]);
    }*/

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
