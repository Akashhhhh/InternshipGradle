package ecommerce.cache;

import java.util.*;

/**
 * This class is used for creating generic cache
 * @param <key> any type of key
 * @param <value> any type of value'
 * @author Akash Gupta
 */
public class LruCache <key,value>{
    Set<key> cache;
    Map<key, value> m= new HashMap<key,value>();
    int capacity;

    /**
     * This is the constructor of LruCache
     */
    public LruCache() {
        this.cache = new LinkedHashSet<key>(10);
        this.m = new HashMap<key,value>();
        this.capacity = 10;
    }

    /**
     * This method is used for getting the value from cache
     * @param k key
     * @return value in the cache
     */
    public value get( key k) {

        return m.get(k);
    }

    /**
     * This method is used for deleting value from cache
     * @param k key
     */
    public void delete(key k){
        cache.remove(k);

    }

    /**
     * This method is used for checking whether key exist in the cache or not
     * @param k key
     * @return returns true or false
     */
    public boolean find(key k){
        if(cache.contains(k)){
            return true;
        }
        return false;
    }

    /**
     * This method is used for displaying the content of cache
     */
    public void display() {
        for (Map.Entry<key,value> entry : m.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
    }

    /**
     * This method is used for putting value in the cache
     * @param k key
     * @param v value
     */
    public void put(key k,value v) {
        if (cache.size() == capacity) {
            key firstKey = cache.iterator().next();
            cache.remove(firstKey);
            m.remove(firstKey);
        }

        cache.add(k);
        m.put(k,v);
    }


}

