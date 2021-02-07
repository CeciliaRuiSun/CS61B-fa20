import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* only work with keys that can be compared*/
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;
    private int size = 0;

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K k, V v){
             key= k;
             value = v;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
    public BSTMap(){

    }


    @Override
    public void put(K key, V value){
        root = helperPut(root,key,value);
        size ++;
    }

    public Node helperPut(Node N, K key, V value){
        if(N == null){
            N = new Node(key,value);
            return N;
        }
        if(key.compareTo(N.key) < 0){
            N.left = helperPut(N.left, key, value);
        } else{
            N.right = helperPut(N.right, key, value);
        }

        return N;
    }

    @Override
    public void clear(){
        //root = null;   //if we just set root points to null, some nodes called in other functions
                        // could potentially be not cleared

        helperClear(root);
        root = null;
        size = 0;
    }

    public void helperClear(Node N){
        if (N == null) {
            return;
        }

        N.key = null;
        N.value = null;

        helperClear(N.left);
        N.left = null;

        helperClear(N.right);
        N.right = null;

        //although there is no "return" explicitly, there is one.
        //After execution of each layer, it returns to last layer.
    }

    @Override
    public boolean containsKey(K key){
        return helperContainsKey(root, key);
    }

    public boolean helperContainsKey(Node N, K key){
        if(N == null){
            return false;
        }
        if(N.key.compareTo(key) == 0){
            return true;
        } else if(N.key.compareTo(key) < 0){
            return helperContainsKey(N.right, key);
        } else {
            return helperContainsKey(N.left, key);
        }
    }

    @Override
    public V get(K key){
        return helperGet(root,key);
    }

    public V helperGet(Node N, K key){
        if(N == null){
            return null;
        }
        //System.out.println(N.toString());

        if(N.key.compareTo(key) == 0){
            return N.value;
        } else if(N.key.compareTo(key) < 0){
            return helperGet(N.right, key);
        } else {
            return helperGet(N.left, key);
        }

    }

    @Override
    public int size(){
        return size;
    }

    public void printInOrder(){
        helperPrint(root);
    }

    public void helperPrint(Node N){
        if(N == null){
            return;
        }
        helperPrint(N.left);
        System.out.println(N.value);
        helperPrint(N.right);
    }

    public Set<K> keySet(){
        return null;
    }

    public V remove(K key){
        return null;
    }

   public V remove(K key, V value){
        return null;
    }

    public Iterator<K> iterator(){
        return null;
    }

    public static void main(String[] args){
        BSTMap<String, String> a = new BSTMap<String, String>();
        a.put("a","apple");
        a.put("c","car");
        a.put("b","beach");
        a.put("g","goat");
        a.put("d","deer");
        a.put("f","flower");
        //System.out.println(a.get("c"));
        //a.printInOrder();
        a.clear();
        System.out.println("After clear----");
        System.out.println(a.get("c"));
    }
}
