import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size = 16;            // num of buckets
    private int num;                  // num of items
    private double factor = 0.75;        // num/size
    private int resizeFactor = 2;

    private List<Node>[] buckets = (List<Node>[]) new Object[size];

    private class Node{
        public K key;
        public V value;
        public Node next;

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

    @Override
    public void put(K key, V value){
        if(num/size >= factor){
            buckets = resize(buckets);
        }

        Node N = new Node(key,value);
        helperPut(buckets,N);
    }

    public void helperPut(List<Node>[] ls, Node N){
        int loc = Math.floorMod(N.key.hashCode(),size);
        boolean identicalNode = false;
        for(int i = 0;i < ls[loc].size();i ++){
            if(ls[loc].get(i).value == N.value){
                identicalNode = true;
                break;
            }
        }
        if(identicalNode){

        } else {
            ls[loc].add(N);
        }
    }

    public List<Node>[] resize(List<Node>[] ls){
        int preSize = size;
        size = size * resizeFactor;
        List<Node>[] newBuckets= (List<Node>[]) new Object[size];
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
//    public List<V> helperPut(List<V> ls, V value){
//        ls.add(value);
//    }

    @Override
    public Set<K> keySet(){

    }

    @Override
    public V remove(K key){

    }

    @Override
    public V remove(K key, V value){

    }

    public Iterator<K> iterator(){
        return null;
    }
}
