package bearmaps;

import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T>{
    private PriorityNode[] Item;
    private int size = 0;
    private int index;
    private int multiFactor = 2;
    private MyHashMap<T,Integer> hm;

    public ArrayHeapMinPQ(int n){
        Item = new PriorityNode[n];
    }

    /**  Node of heap
     * @author Matt Owen @since 03-11-19
     * */
    private class PriorityNode<T> implements Comparable<PriorityNode>{
        public T item;
        public double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }
        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

    }

    @Override
    public void add(T item, double priority){
        if(contains(item)){
            throw new IllegalArgumentException("Item already exists");
        }

        /* resize array when reaching maximum number */
        if(size >= 0.75 * Item.length){
            PriorityNode[] newItem = new PriorityNode[size * multiFactor];
            System.arraycopy(Item,0,newItem,0,Item.length);
            Item = newItem;
        }

        Item[size] = new PriorityNode(item, priority);
        index = size;
        if(index > 0) {
            swim(index);
        }
        hm.put(item,index);
        size ++;
    }

    public void swim(int k){
        if(Item[parent(k)].compareTo(Item[k]) <= 0){
            return;
        } else {
            swap(k, parent(k));
            index = parent(k);
            swim(parent(k));
        }
    }

    public void swap(int k, int parentK){
        PriorityNode temp = Item[k];
        Item[k] = Item[parentK];
        Item[parentK] = temp;
    }

    public int parent(int k) {
        return (k - 1) / 2;
    }


    @Override
    public boolean contains(T item){
        return hm.containsKey(item);
    }

    @Override
    public T getSmallest(){
        return (T) Item[0].getItem();
    }

    @Override
    public int size(){
        return size;
    }


    @Override
    public void changePriority(T item, double priority){
        if(!contains(item)){
            throw new NoSuchElementException("Item doesn't exist");
        }

        int getIndex = hm.get(item);
        double oldPriority = Item[getIndex].priority;
        Item[getIndex].priority= priority;

        /* swim up if get higher priority */
        if(priority > oldPriority){
            swim(getIndex);
        }

        /* swim down if get higher priority */

    }

    @Override
    public T removeSmallest(){

    }
}
