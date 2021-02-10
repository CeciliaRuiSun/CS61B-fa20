package bearmaps;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private PriorityNode[] Item;
    private int size = 0;
    private int multiFactor = 2;

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
        /* resize array when reaching maximum number */
        if(size == Item.length){
            PriorityNode[] newItem = new PriorityNode[size * multiFactor];
            System.arraycopy(Item,0,newItem,0,Item.length);
            Item = newItem;
        }

        Item[size] = new PriorityNode(item, priority);
        if(size > 0) {
            swim(size);
        }
        size ++;
    }

    public void swim(int k){
        if(k == 0){
            return;
        }

        if (Item[parent(k)].compareTo(Item[k]) > 0) {
            swap(k, parent(k));
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

    }

    @Override
    public T getSmallest(){

    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void changePriority(T item, double priority){

    }

    @Override
    public T removeSmallest(){

    }
}
