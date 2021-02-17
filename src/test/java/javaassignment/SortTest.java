package javaassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTest {
    Sort obj;
    @BeforeEach
    public void setup(){
        obj  = new Sort();
    }
    @Test
    public void mergeSortTest(){
        String[] v= new String[3];
        v[0]="akash";
        v[1]="baba";
        v[2]="aaka";
        String[] ans= new String[3];
        ans[0]="baba";
        ans[1]="akash";
        ans[2]="aaka";
        assertArrayEquals(ans,obj.mergeSort(v,0,2));
    }

}
