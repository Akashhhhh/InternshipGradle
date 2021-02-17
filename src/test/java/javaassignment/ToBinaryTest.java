package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class ToBinaryTest {
    ToBinary obj;
    @BeforeEach
    public void setup(){
        obj  = new ToBinary();
    }
    @Test
    public void toBinaryTest(){
        int [] v = new int[1000];
        v[0]=1;
        v[1]=1;

        assertArrayEquals(v,obj.toBinary(3));

    }
}
