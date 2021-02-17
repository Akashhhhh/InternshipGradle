package javaassignment;

import java.util.Scanner;

/**
 * <h1>This class check whether a number is prime or not</h1>
 * This class takes a number as a user input
 *
 * @author Akash Gupta
 */
public class IsPrime {
    /**
     *
     * @param number It is number given by user which is tested for primality.
     * @return It returns nothing. It just print "Prime" if the given numnber is prime else "Not Prime"
     *
     */
    public static boolean isPrime(Integer number){
        boolean flag=false;
        for(int i=2;i*i<=number;i++)
        {
            if(number%i==0){
                flag=true;
                break;
            }
        }
        if(!flag)
        {
            System.out.println("Prime");
            return true;
        }

            System.out.println("Not Prime");
            return false;
    }

    /**
     *
     * @param args Unused
     * @return It returns nothing
     */
    public static void main(String []args){

        Scanner sc = new Scanner(System.in);
        Integer number = sc.nextInt();
        isPrime(number);
    }
}
