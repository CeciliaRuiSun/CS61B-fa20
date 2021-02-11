package bearmaps;

import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T>{
    private PriorityNode[] Item;
    private int size = 0;
    private int index;
    private int multiFactor = 2;
    private MyHashMap<T,Integer> hm;    //store item as key, item's index in array as value

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
            swimup(index);
        }
        hm.put(item,index);
        size ++;
    }

    public void swimup(int k){
        if(Item[parent(k)].compareTo(Item[k]) <= 0){
            return;
        } else {
            swap(k, parent(k));
            if(hm.containsKey((T) Item[k].item)) {
                hmReplaceNode((T) Item[k].item, parent(k));
            } else{
                index = parent(k);
            }
            hmReplaceNode((T)Item[parent(k)].item,k);
            swimup(parent(k));
        }
    }

    public void swimdown(int k){
        int leftchild = 2 * k + 1;
        if(leftchild < size) {
            int rightchild = 2 * k + 2;
            int minchild = 0;
            if(rightchild < size) {
                minchild = Math.min(leftchild, rightchild);
            }else {
                minchild = leftchild;
            }

            if (Item[minchild].compareTo(Item[k]) > 0) {
                return;
            } else {
                swap(k, minchild);
                hmReplaceNode((T) Item[k].item, minchild);
                hmReplaceNode((T) Item[minchild].item, k);
                swimdown(minchild);
            }
        }
    }

    public void hmReplaceNode(T key, int newValue){
        hm.remove(key);
        hm.put(key,newValue);
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
        if(priority < oldPriority){
            swimup(getIndex);
        }

        /* swim down if get higher priority */
        if(priority > oldPriority){
            swimdown(getIndex);
        }

    }

    @Override
    public T removeSmallest(){
        return null;
    }



    public static void main(String[] args){

    }
}
