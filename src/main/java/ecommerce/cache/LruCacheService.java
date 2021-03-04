package ecommerce.cache;

import java.util.*;

/**
 * This is a specific lru cache for customer
 * @param <key> key
 * @param <value> value
 * @author  Akash Gupta
 */
public class LruCacheService<key, value> {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LruCacheService.class.getName());
    Set<key> cache = null;
    Map<String, UUID> m = new HashMap<String, UUID>();

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
    public boolean delete(String k) {
        cache.remove(k);
        return true;
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
    public Vector<String> display() {
        Vector<String>v = new Vector<>();
        for (Map.Entry<String, UUID> entry : m.entrySet())
        {
            logger.info("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            v.add(entry.getKey());
        }
           return v;
    }

    /**
     * This method is used for inserting into the cache
     * @param k key
     * @param v value
     */
    public boolean put(String k, UUID v) {
        if (cache.size() == capacity) {
            String  firstKey = (String) cache.iterator().next();
            cache.remove(firstKey);
            m.remove(firstKey);
        }

        cache.add((key) k);
        m.put(k, v);
        return true;
    }

}
