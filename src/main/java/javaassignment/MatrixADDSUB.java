package javaassignment;

import java.util.Scanner;
import java.util.Vector;

/**
 * <h1>This class is used to add and subtract two 2D matrices</h1>
 * It takes two matrices as a user input and print their addition and subtraction.
 *
 * @author Akash Gupta
 */
public class MatrixADDSUB {
    /**
     * This add method prints the addition of two 2D matrices.
     *
     * @param m1 A two dimensional matrix
     * @param m2 A two dimensional matrix
     * @param r  number of rows in matrix
     * @param c  number of columns in matrix
     * @return It returns nothing
     */
    public static Vector<Integer> add(int[][] m1, int[][] m2, int r, int c) {
        Vector<Integer>v = new Vector<Integer>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print((m1[i][j] + m2[i][j]) + "\t");
                v.add(m1[i][j] + m2[i][j]);
            }
            System.out.println();
        }
        return v;

    }

    /**
     * This subtract method prints the difference of two 2D dimensional matrices
     *
     * @param m1 A two dimensional matrix
     * @param m2 A two dimensional matrix
     * @param r  number of rows in matrix
     * @param c  number of columns in matrix
     * @return It returns nothing
     */
    public static Vector<Integer> subtract(int[][] m1, int[][] m2, int r, int c) {
        Vector<Integer>v = new Vector<Integer>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print((m1[i][j] - m2[i][j]) + "\t");
                v.add(m1[i][j] - m2[i][j]);
            }
            System.out.println();
        }
       return v;
    }

    /**
     * This main method is used to take two 2D dimensional as a user input
     *
     * @param args Unused
     * @return It returns nothing
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no. of rows1: ");
        Integer rows1 = sc.nextInt();

        System.out.println("Enter no. of columns1: ");
        Integer cols1 = sc.nextInt();

        int[][] matrix1 = new int[rows1][cols1];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter no. of rows2: ");
        Integer rows2 = sc.nextInt();

        System.out.println("Enter no. of columns2: ");
        Integer cols2 = sc.nextInt();

        int[][] matrix2 = new int[rows2][cols2];
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }

        add(matrix1, matrix2, rows1, cols1);
        subtract(matrix1, matrix2, rows1, cols1);

    }

}
