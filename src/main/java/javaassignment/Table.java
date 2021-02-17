package javaassignment;
import java.util.Scanner;
import java.util.Vector;

/**
 * <h1>This class print the table of a number</h1>
 * It takes a number and print its table upto the multiple of 10
 *
 * @author Akash Gupta
 */
public class Table {
    /**
     *
     * @param n it is integer type number
     * @return It return nothing. It just print the table of a number upto the multiple of 10
     */
    public static Vector<Integer>table(int n){
        Vector<Integer>v = new Vector<Integer>();
        for(int i=1; i <= 10; i++) {
            System.out.println(n+" * "+i+" = "+n*i);
            v.add(n*i);
        }
        return v;
    }

    /**
     * This method is used to take user input
     * @param args Unused
     * @retrun It returns nothing
     */
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any number: ");
        int n  = sc.nextInt();
        table(n);
    }
}
