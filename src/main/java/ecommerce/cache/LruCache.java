package ecommerce.cache;

import java.util.*;

public class LruCache {
    Set<String> cache;
    Map<String, UUID> m= new HashMap<String,UUID>();
    int capacity;

    public LruCache() {
        this.cache = new LinkedHashSet<String>(10);
        this.capacity = 10;
    }

    public UUID get( String key) {

        return m.get(key);
    }
    public void delete(String key){
        cache.remove(key);

    }
    public boolean find(String key){
        if(cache.contains(key)){
            return true;
        }
        return false;
    }
//    public void display() {
//        for (Map.Entry<String,Object> entry : m.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
//    }

    public void put(String key,UUID obj) {
        if (cache.size() == capacity) {
            String firstKey = cache.iterator().next();
            cache.remove(firstKey);
            m.remove(firstKey);
        }

        cache.add(key);
        m.put(key,obj);
    }


}

