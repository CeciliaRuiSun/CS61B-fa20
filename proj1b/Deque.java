public interface Deque<Item>{
    public void addFirst(Item item);
    public void addLast(Item item);
    public int size();
    public void printDeque();
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);

    default public Boolean isEmpty(){
        if(size() == 0){
            return true;
        }
        return false;
    }
}