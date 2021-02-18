package javaassignment;

import java.util.Scanner;

/**
 * <h1> This class converts decimal representation of a number to its binary representation</h1>
 *
 * @author  Akash Gupta
 */


public class ToBinary {
    /**
     * This class converts decimal representation to binary representation
     * @param n it is the decimal representation of a number
     * @return It returns nothing .It just print the binary representation of the number
     */
    static int[] toBinary(Integer n){
        int[] binaryNum = new int[1000];


        int i = 0;
        while (n > 0)
        {

            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }


        for (int j = i - 1; j >= 0; j--)
            System.out.print(binaryNum[j]);
        return binaryNum;
    }

    /**
     * This method is used to take user input
     * @param args Unused
     * @return Nothing
     */
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the decimal representation of Number: ");
        Integer number = sc.nextInt();
        System.out.println("Binary representation of Number:");
        toBinary(number);
    }
}

