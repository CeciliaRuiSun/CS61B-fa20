public class LinkedListDeque<T> {
    private int size = 0;
    private Node Sentinal;

    private class Node{
        Node pre;
        T item;
        Node next;

        public Node(T t, Node p, Node n){
            item = t;
            pre = p;
            next = n;
        }

    }

    public LinkedListDeque(){
        Sentinal = new Node(null,null,null);
        Sentinal.next = Sentinal;
        Sentinal.pre = Sentinal;
    }

    public void addFirst(T item){
        Sentinal = new Node(null,null,null);
        Sentinal.next = new Node(item,Sentinal,Sentinal);
        Sentinal.pre = Sentinal.next;
        size += 1;
    }

    public void addLast(T item){
        Sentinal.pre.next = new Node(item,Sentinal.pre,Sentinal);
        Sentinal.pre = Sentinal.pre.next;
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        } else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int count = size;
        Node p = Sentinal.next;
        while(count != 0){
            System.out.println(p.item);
            p = p.next;
            count --;
        }
    }

    public T removeFirst(){
        T item = Sentinal.next.item;
        Sentinal.next.next = Sentinal;
        Sentinal.next = Sentinal.next.next;
        size -= 1;
        return item;
    }

    public T removeLast(){
        T item = Sentinal.pre.item;
        Sentinal.pre.pre.next = Sentinal;
        Sentinal.pre = Sentinal.pre.pre;
        size -= 1;
        return item;
    }

    public T get(int index){
        Node n = Sentinal.next;
        if(index >= size){
            return null;
        }
        while(index != 0){
            n = n.next;
            index --;
        }

        return n.item;
    }

    public T getRecursive(int index){
        if(index >= size || index <= 0){
            return null;
        }
        Node n = Sentinal.next;
        return helper(index,n);
    }

    private T helper(int index, Node n){
        if(index == 0){
            return n.item;
        }

        return helper(index --, n.next);
    }
}
