package javaassignment;

import java.util.Scanner;

/**
 * <h1>It print the sum of digit of number</h1>

 * @author Akash Gupta
 */
public class DigitOfNumber {
    /**
     * This ADD method is used to print the sum of two numbers represented in strings
     * @param n first number
     *
     * @return void It returns nothing.
     */
    public static int add(int n){
        int sum=0;
        while(n!=0){
            sum=sum+n%10;
            n=n/10;
        }
        System.out.println(sum);
        return sum;
    }

    /**
     * This method is used to take user input.
     * @param args Unused
     * @return Nothing
     */
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        add(num1);
    }
}

