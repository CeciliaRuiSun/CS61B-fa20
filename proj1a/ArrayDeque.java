public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst = 3; //0 to 7
    private int nextLast = 4;  //0 to 7

    public ArrayDeque(){
        items = (Item []) new Object[8];
        size = 0;
    }

    private void resize(int capacity){
        int factor = 4;
        Item[] a = (Item []) new Object[capacity * factor];

        //copy old array to new array BY ORDER
        //[hdcabegf] -- > [gfhdcabe]
        int index = 0;
        for(int i = nextFirst + 1;i < size;i++){
            a[index] = items[i];
            index ++;
        }
        for(int i = 0; i <= nextFirst;i ++){
            a[index] = items[i];
            index ++;
        }
        nextLast = size;
        nextFirst = size * factor - 1;
        items = a;
    }

    public void addFirst(Item x){
        if(size == items.length){
            resize(size);
        }
        items[nextFirst] = x;
        if(nextFirst > 0) {
            nextFirst -= 1;
        } else {
            nextFirst = items.length - 1;
        }
        size += 1;
    }

    public void addLast(Item x){
        if(size == items.length){
            resize(size);
        }
        items[nextLast] = x;
        if(nextLast < items.length - 1) {
            nextLast += 1;
        } else {
            nextLast = 0;
        }
        size += 1;
    }

    public Item get(int i){
        //[---abc--]   get(2) -- > c
        //[---dabc--]   get(2) -- > b
        //[abc----e]   get(2) -- > b
        if(nextFirst + i + 1 < items.length) {
            return items[nextFirst + i + 1];
        } else{
            return items[nextFirst + i + 1 - items.length];
        }
    }

    public void removeLast(){
        if(nextLast > 0) {
            nextLast--;
        } else {
            nextLast = size - 1;
        }
        items[nextLast] = null;
        size -= 1;
    }

    public static void main(String[] args) {
        ArrayDeque<String> arr = new ArrayDeque<String>();
        arr.addFirst("a");
        arr.addLast("b");
        arr.addFirst("c");
        arr.addFirst("d");
        arr.addFirst("h");
        arr.addLast("e");
        arr.addFirst("f");
        arr.addFirst("g");
        arr.addFirst("m");
        arr.addLast("n");
        System.out.println(arr.get(2));
    }
}
