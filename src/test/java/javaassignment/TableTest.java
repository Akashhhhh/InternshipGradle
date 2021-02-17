package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest {
    Table obj;
    @BeforeEach
    public void setup(){
        obj  = new Table();
    }
    @Test
    public void tableTest(){
        Vector<Integer>v = new Vector<Integer>();
        v.add(4);
        v.add(8);
        v.add(12);
        v.add(16);
        v.add(20);
        v.add(24);
        v.add(28);
        v.add(32);
        v.add(36);
        v.add(40);
        assertEquals(v,obj.table(4));
    }
}
