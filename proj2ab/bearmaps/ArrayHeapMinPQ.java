package bearmaps;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private PriorityNode[] Item;

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

    }

    @Override
    public boolean contains(T item){

    }

    @Override
    public T getSmallest(){

    }

    @Override
    public int size(){
        return Item.length;
    }

    @Override
    public void changePriority(T item, double priority){

    }

    @Override
    public T removeSmallest(){

    }
}
