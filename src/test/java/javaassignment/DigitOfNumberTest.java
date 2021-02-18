package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DigitOfNumberTest {
    DigitOfNumber obj;
    @BeforeEach
    public void setup(){
        obj  = new DigitOfNumber ();
    }
    @Test
    public void addTest(){
        assertEquals(6,obj.add(123));

    }

}
