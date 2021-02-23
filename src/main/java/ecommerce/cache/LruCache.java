package ecommerce.cache;

import java.util.*;

public class LruCache <key,value>{
    Set<key> cache;
    Map<key, value> m= new HashMap<key,value>();
    int capacity;

    public LruCache() {
        this.cache = new LinkedHashSet<key>(10);
        this.m = new HashMap<key,value>();
        this.capacity = 10;
    }

    public value get( key k) {

        return m.get(k);
    }
    public void delete(key k){
        cache.remove(k);

    }
    public boolean find(key k){
        if(cache.contains(k)){
            return true;
        }
        return false;
    }
//    public void display() {
//        for (Map.Entry<String,Object> entry : m.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
//    }

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

