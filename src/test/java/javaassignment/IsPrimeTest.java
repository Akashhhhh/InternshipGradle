package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsPrimeTest {
    IsPrime obj;
    @BeforeEach
    public void setup(){
        obj  = new IsPrime();
    }
    @Test
    public void isPrimeTest1(){
        assertEquals(true,obj.isPrime(23));

    }
    @Test
    public void isPrimeTest2(){
        assertEquals(false,obj.isPrime(20));
    }


}
