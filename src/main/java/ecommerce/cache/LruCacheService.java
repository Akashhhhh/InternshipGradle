package ecommerce.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * This is a specific lru cache for customer
 * @param <key> key
 * @param <value> value
 * @author  Akash Gupta
 */
public class LruCacheService<key, value> {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LruCacheService.class.getName());
    Set<key> cache = null;
    Map<key, value> m = new HashMap<key, value>();
    int capacity;

    /**
     * Constructor of LruCacheService
     */
    public LruCacheService() {
        this.cache = ((LruCache) new LruCache()).cache;
        this.m = ((LruCache) new LruCache()).m;
        this.capacity = ((LruCache) new LruCache()).capacity;
    }

    /**
     * This methodis for fetching
     * @param k key
     * @return it returns the uuid
     */
    public UUID get(String k) {

        return (UUID) m.get(k);
    }

    /**
     * Tis method is used for deleting
     * @param k key
     */
    public void delete(String k) {
        cache.remove(k);

    }

    /**
     * This is used to check whether key exist or not
     * @param k key
     * @return it return true or false
     */
    public boolean find(String k) {
        if (cache.contains(k)) {
            return true;
        }
        return false;
    }
//    public void display() {
//        for (Map.Entry<String,Object> entry : m.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
//    }

    /**
     * This method is used for inserting into the cache
     * @param k key
     * @param v value
     */
    public void put(key k, value v) {
        if (cache.size() == capacity) {
            key firstKey = cache.iterator().next();
            cache.remove(firstKey);
            m.remove(firstKey);
        }

        cache.add(k);
        m.put(k, v);
    }

}
