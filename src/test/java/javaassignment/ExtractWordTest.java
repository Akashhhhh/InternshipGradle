package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtractWordTest {
    ExtractWord obj;
    @BeforeEach
    public void setup(){
        obj  = new ExtractWord ();
    }
    @Test
    public void extractWordTest(){
        Vector<String> v = new Vector<String>();
        v.add("akash");
        v.add("gupta");
        assertEquals(v,(obj.extractWord("akash gupta")));
    }
}
