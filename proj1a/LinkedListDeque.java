public class LinkedListDeque <Item>{

    private class DoubleNode{
        public DoubleNode pre;
        public Item item;
        public DoubleNode next;

        public DoubleNode(DoubleNode a, Item b, DoubleNode c){
            pre = a;
            item = b;
            next = c;
        }
    }

    private DoubleNode sentinelHead;
    private DoubleNode sentinelTail;
    private int size;

    //create an empty linkedlist
    public LinkedListDeque(){
        sentinelHead = new DoubleNode(null,null,null);
        sentinelTail = new DoubleNode(null,null,null);
        sentinelHead.next = sentinelTail;
        sentinelHead.pre = sentinelTail;
        sentinelTail.next = sentinelHead;
        sentinelTail.pre = sentinelHead;
        size = 0;
    }

    public LinkedListDeque(Item item){
        sentinelHead = new DoubleNode(null,null,null);
        sentinelHead.next = new DoubleNode(sentinelHead,item,null);
        sentinelTail = new DoubleNode(null,null,null);
        sentinelHead.next.next = sentinelTail;
        sentinelTail.pre = sentinelHead.next;
        sentinelTail.next = sentinelHead;
        sentinelHead.pre = sentinelTail;
        size = 1;
    }

    public Boolean isEmpty(){
        if(size == 0) {
            return true;
        }
        return false;

    }

    public void addFirst(Item item){
        sentinelHead.next = new DoubleNode(sentinelHead,item,sentinelHead.next);
        sentinelHead.next.next.pre = sentinelHead.next;
        size += 1;
    }

    public void addLast(Item item){
        sentinelTail.pre = new DoubleNode(sentinelTail.pre,item,sentinelTail);
        sentinelTail.pre.pre.next = sentinelTail.pre;
        size += 1;
    }

    public Item removeFirst(){
        Item res;
        if(sentinelHead.next != sentinelTail){
            res = sentinelHead.next.item;
        } else{
            res = null;
        }
        sentinelHead.next.next.pre = sentinelHead;
        sentinelHead.next = sentinelHead.next.next;
        size -= 1;

        return res;
    }

    public Item removeLast(){
        Item res;
        if(sentinelTail.pre != sentinelHead){
            res =  sentinelTail.pre.item;
        } else{
            res =  null;
        }
        sentinelTail.pre.pre.next = sentinelTail;
        sentinelTail.pre = sentinelTail.pre.pre;
        size -= 1;

        return res;
    }

    public int size(){
        return size;
    }

    public Item get(int index){
        if(index < size) {
            DoubleNode P;
            P = sentinelHead;
            while (index-- > 0) {
                P = P.next;
            }
            return P.next.item;
        }
        return null;
    }

    public Item getRecursive(int index){
        if(index < size) {
            return getHelper(index).item;
        }
        return null;
    }

    public DoubleNode getHelper(int n){
        //recursion method to get node
        if (n == 0){
            return sentinelHead.next;
        }
        return getHelper(n - 1).next;
    }

    public void printDeque(){
        DoubleNode P;
        P = sentinelHead;
        while(P.next != sentinelTail){
            System.out.println(P.next.item);
            P = P.next;
        }
    }

}