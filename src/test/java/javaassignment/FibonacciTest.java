package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {
    Fibonacci obj;
    @BeforeEach
    public void setup(){
        obj  = new Fibonacci ();
    }
    @Test
    public void fibonacciTest(){
        Vector<Integer>v = new Vector<Integer>();
        v.add(0);
        v.add(1);
        v.add(1);
        v.add(2);
        assertEquals(v,obj.fibonacci(4));
    }

}
