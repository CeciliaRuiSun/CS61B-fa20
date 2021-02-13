package bearmaps;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T>{
    /**
     * Array <-- Node <-- item, priority
     * Hashmap <-- key: item, value: item's index in array
     *
     * an easier way: let both array and hashmap point to nodes, i.e. hashmap store node's address
     */

    private PriorityNode[] Item;
    private int size = 0;
    private int index;
    private int multiFactor = 2;
    private MyHashMap<T,Integer> hm;    //store item as key, item's index in array as value

    public ArrayHeapMinPQ(int n){
        Item = new PriorityNode[n];
        hm = new MyHashMap<>();
    }

    /**  Node of heap
     * @author Matt Owen @since 03-11-19
     * */
    private class PriorityNode<T> implements Comparable<PriorityNode>{
        public T item;
        public double priority;

        public String toString() {
            return "" + item + ":" + priority;
        }
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

    private void swimup(int k){
        if(Item[parent(k)].compareTo(Item[k]) <= 0){
            return;
        } else {
            if(hm.containsKey((T) Item[k].item)) {
                System.out.println("k is " + k);
                System.out.println("parent(k) is " + parent(k));
                hmReplaceNode((T) Item[k].item, parent(k));
            } else{
                index = parent(k);
            }
            hmReplaceNode((T)Item[parent(k)].item,k);
            swapNode(k, parent(k));
            swimup(parent(k));
        }
    }

    private void swimdown(int k){
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
                hmReplaceNode((T) Item[k].item, minchild);
                hmReplaceNode((T) Item[minchild].item, k);
                swapNode(k, minchild);
                swimdown(minchild);
            }
        }
    }

    private void hmReplaceNode(T key, int newValue){
        hm.remove(key);
        hm.put(key,newValue);
    }

    private void swapNode(int k, int parentK){
        PriorityNode temp = Item[k];
        Item[k] = Item[parentK];
        Item[parentK] = temp;
    }

    private int parent(int k) {
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
        Item[0] = Item[size - 1];
        Item[size - 1] = null;
        size --;
        swimdown(0);

        if(size > 0) {
            return (T) Item[0].item;
        } else {
            return null;
        }
    }

    public void print(){
        System.out.println("Print, size:"+size);
        ArrayList<Object> ls = new ArrayList<>();
        ls.add("");
        for(int i = 0;i < size;i++){
            //System.out.println("  Add: "+Item[i].toString());
            ls.add(Item[i].toString());
        }
        PrintHeapDemo printtest = new PrintHeapDemo();
        printtest.printFancyHeapDrawing(ls.toArray());
    }


}
