package javaassignment;

import java.util.Scanner;
import java.util.Vector;

/**
 * <h1>This class prints the first n terms of fibonacci series</h1>
 * It takes number of terms to be printed as user input.
 *
 * @authos Akash Gupta
 */
public class Fibonacci {
    /**
     * This methods prints the first n terms of fibonacci series.
     * @param n Number of terms to be printed as given by user
     * @return void It returns nothing
     */
    public static Vector<Integer> fibonacci(int n){
        Vector<Integer> v = new Vector<Integer>();
        int num1 = 0;
        int num2 = 1;
        for(int i=0;i<n;i++)
        {

            System.out.print(num1 + " ");
            v.add(num1);
            int num3 = num2 + num1;
            num1 = num2;
            num2 = num3;

        }
        return v;
    }

    /**
     *
     * @param args Unused
     * @retrurn nothing
     */
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of trems: ");
        int n = sc.nextInt();

        fibonacci(n);
    }
}

