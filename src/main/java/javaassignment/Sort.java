package javaassignment;

import java.util.Scanner;

/**
 * <h1>sort N names (strings) in ascending order</h1>
 * This class takes length of string and string as input and then sort them and print them out in ascending order.
 *
 * This class implements the sortString method.
 *
 * @author Akash Gupta
 */
public class Sort {
    /**
     * This method basically merges two sorted array.
     * @param a It is the array of strings
     * @param from Lower end of string array
     * @param mid  mid point of string array
     * @param to Upper end of string array
     * @return It return nothing
     */
    public static void merge(String[] a, int from, int mid, int to)
    {
        int n = to - from + 1;
        String[]b = new String[n];
        int i1 = from;
        int i2 = mid + 1;
        int j = 0;

        while (i1 <= mid && i2 <= to)
        {
            if (a[i1]!=null&&a[i1].compareTo(a[i2]) > 0)
            {
                b[j] = a[i1];
                i1++;
            }
            else
            {
                b[j] = a[i2];
                i2++;
            }
            j++;
        }
        while (i1 <= mid)
        {
            b[j] = a[i1];
            i1++;
            j++;
        }


        while (i2 <= to)
        {
            b[j] = a[i2];
            i2++;
            j++;
        }


        for (j = 0; j < n; j++)
            a[from + j] = b[j];
    }

    /**
     * This method recursively divide the array into two parts and the merge it
     * @param a It is the array of strings
     * @param from Lower end of string array
     * @param to Upper end of string array
     */
    public static String[] mergeSort(String[] a, int from, int to)
    {
        if (from == to)
            return a;
        int mid = (from + to) / 2;

        mergeSort(a, from, mid);
        mergeSort(a, mid + 1, to);
        merge(a, from, mid, to);
        return a;
    }
    /**
     * This is the main method which takes length and string as input and makes use of sortString methods.
     * @param args Ununsed
     * @return Nothing
     */
    public static void main(String[] args)
    {
        System.out.println("Enter length of string: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Scanner st = new Scanner(System.in);
        String[] names= new String[n];

        System.out.println("Enter names: ");
        for(int i=0; i<n; i++)
        {
            System.out.print("Enter name [ " + (i+1) +" ]: ");
            names[i] = st.nextLine();
        }

        mergeSort(names,0,n-1);
        for(int i=n-1;i>=0;i--)
            System.out.println(names[i]);
    }
}
