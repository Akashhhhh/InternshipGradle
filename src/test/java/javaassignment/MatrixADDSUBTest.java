package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MatrixADDSUBTest {
    MatrixADDSUB obj;
    @BeforeEach
    public void setup(){
        obj  = new MatrixADDSUB();
    }
    @Test
    public void addTest(){
      Vector<Integer>ans = new Vector<Integer>();
      ans.add(2);
      ans.add(3);
      ans.add(4);
      ans.add(5);
       int m1[][] = {{1,2},{3,4}};
       int m2[][] = {{1,1},{1,1}};
       assertEquals(ans,obj.add(m1,m2,2,2));
    }
    @Test
    public void subtractTest(){
        Vector<Integer>ans = new Vector<Integer>();
        ans.add(0);
        ans.add(1);
        ans.add(2);
        ans.add(3);
        int m1[][] = {{1,2},{3,4}};
        int m2[][] = {{1,1},{1,1}};
        assertEquals(ans,obj.subtract(m1,m2,2,2));
    }
}
