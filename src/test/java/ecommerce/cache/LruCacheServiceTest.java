package ecommerce.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class LruCacheServiceTest<key,value> {
     LruCacheService lruCacheService;

    @BeforeEach
    public   void setup() {
        lruCacheService= new LruCacheService();

    }

    @Test
    public void testPutWithValidCapacity(){

        assertTrue(lruCacheService.put("akashgupta@gmail.com", UUID.randomUUID()));
    }
    @Test
    public void testPutWithInValidCapacity(){

        assertTrue(lruCacheService.put("akash@gmail.com",UUID.randomUUID()));
        assertTrue(lruCacheService.put("akas@gmail.com", UUID.randomUUID()));
        assertTrue(lruCacheService.put("akah@gmail.com", UUID.randomUUID()));
    }
    @Test
    public void testGet() {

      lruCacheService.put("akashgupta@gmail.com", UUID.randomUUID());
      UUID uuid =lruCacheService.get("akashgupta@gmail.com");
      assertEquals(uuid,lruCacheService.get("akashgupta@gmail.com"));
    }
    @Test
    public  void testFindIfExit(){
        lruCacheService.put("akashgupta@gmail.com", UUID.randomUUID());
        assertTrue(lruCacheService.find("akashgupta@gmail.com"));
    }
    @Test
    public  void testFindIfNotExit(){

        assertFalse(lruCacheService.find("akashgupta@gmail.com"));
    }

    @Test
    public  void testDelete(){
        lruCacheService.put("akashgupta@gmail.com", UUID.randomUUID());
        assertTrue(lruCacheService.delete("akashgupta@gmail.com"));
    }

    @Test
    public  void testDisplay(){
        lruCacheService.put("akashgupta@gmail.com", UUID.randomUUID());
        Vector<String >v = new Vector<>();
        v.add("akashgupta@gmail.com");
        assertEquals(v,lruCacheService.display());

    }
}
