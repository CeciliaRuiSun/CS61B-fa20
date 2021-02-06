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
    }
    public BSTMap(){

    }


    @Override
    public void put(K key, V value){
        helperPut(root,key,value);
        size ++;
    }

    public Node helperPut(Node root, K key, V value){
        if(root == null){
            return new Node(key,value);
        }
        if(key.compareTo(root.key) < 0){
            root.left = helperPut(root.left, key, value);
        } else{
            root.right = helperPut(root.right, key, value);
        }

        return root;
    }

    @Override
    public void clear(){
        //root = null;   //if we just set root points to null, some nodes called in other functions
                        // could potentially be not cleared

        helperClear(root);
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
        //After excution of each layer, it returns to last layer.
    }

    @Override
    public boolean containsKey(K key){

    }

    @Override
    public V get(K key){

    }

    @Override
    public int size(){
        return size;
    }

    public void printInOrder(){

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
}
