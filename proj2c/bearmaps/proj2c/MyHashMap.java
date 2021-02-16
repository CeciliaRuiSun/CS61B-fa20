package bearmaps.proj2c;
import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int size = 16;            // number of buckets
    private int num;                  // number of elements
    private double factor = 0.75;        // num/size
    private int resizeFactor = 2;

    private List<Node>[] buckets = (List<Node>[])new ArrayList[size];

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(){
        }

        public Node(K k, V v){
            key = k;
            value = v;
        }
    }

    public MyHashMap(){

    }

    public MyHashMap(int initialSize){
        size = initialSize;
    }

    public MyHashMap(int initialSize, double loadFactor){
        size = initialSize;
        factor = loadFactor;
    }

    @Override
    public void clear(){
        for(int i = 0;i < size;i ++){
            buckets[i] = null;
        }
        num = 0;
    }

    public int getLoc(K key){
        /* get index of which bucket key should be in */
        return Math.floorMod(key.hashCode(),size);
    }

    @Override
    public boolean containsKey(K key){
        List<Node> ls = buckets[getLoc(key)];
        if(ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                if (ls.get(i).key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key){
        List<Node> ls = buckets[getLoc(key)];
        if(ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                if (ls.get(i).key.equals(key)){
                    return ls.get(i).value;
                }
            }
            return null;
        } else{
            return null;
        }
    }

    @Override
    public int size(){
        return num;
    }

    @Override
    public void put(K key, V value){
        if(num/size >= factor){
            buckets = resize(buckets);
        }

        Node N = new Node(key,value);
        helperPut(buckets,N);
        num ++;
    }

    public void helperPut(List<Node>[] ls, Node N){
        int loc = getLoc(N.key);
        boolean sameKeyNode = false;
        if(ls[loc] != null) {
            for (int i = 0; i < ls[loc].size(); i++) {
                if (ls[loc].get(i).key.equals(N.key)) {
                    ls[loc].get(i).value = N.value;
                    sameKeyNode = true;
                    break;
                }
            }
            if (sameKeyNode) {
                //do nothing
            } else {
                ls[loc].add(N);
            }
        } else {
            /* if bucket is null, initiate and assign a list to it */
            ls[loc] = new ArrayList<Node>();
            ls[loc].add(N);
        }
    }

    public List<Node>[] resize(List<Node>[] ls){
        int preSize = size;
        size = size * resizeFactor;
        List<Node>[] newBuckets= (List<Node>[])new ArrayList[size];
        for(int i = 0;i < preSize;i ++){
            if(buckets[i] != null){
                for(int j = 0;j < buckets[i].size();j++){
                    Node N = buckets[i].get(j);
                    helperPut(newBuckets,N);
                }
            }
        }
        return newBuckets;
    }

    @Override
    public Set<K> keySet(){
        Set<K> keys = new HashSet<>();
        for(int i = 0;i < size;i ++){
            if(buckets[i] != null){
                for(int j = 0;j < buckets[i].size();j ++){
                    keys.add(buckets[i].get(j).key);
                }
            }
        }
        return keys;
    }

    @Override
    public V remove(K key){
        if(containsKey(key)) {
            int index = 0;
            List<Node> ls = buckets[getLoc(key)];
            for (int i = 0; i < ls.size(); i++) {
                if (ls.get(i).key.equals(key)) {
                    index = i;
                    break;
                }
            }

            Node deleteNode = ls.get(index);
            V value = deleteNode.value;
            ls.remove(deleteNode);
            return value;
        }

        return null;
    }

    @Override
    public V remove(K key, V value){

        return null;
    }

    public Iterator<K> iterator(){
        return null;
    }

    public static void main(String[] args){
        MyHashMap<String, String> a = new MyHashMap<String, String>();
        a.put("a","apple");
        a.put("c","car");
        a.put("b","beach");
        a.put("g","goat");
        a.put("d","deer");
        a.put("f","flower");
        a.remove("d");
        //a.put("hi","flower");
        System.out.println(a.get("d"));
        //System.out.println(a.containsKey("hi"));
        System.out.println(a.keySet());
       //a.clear();
        //System.out.println("After clear----");
        //System.out.println(a.get("c"));

    }
}
