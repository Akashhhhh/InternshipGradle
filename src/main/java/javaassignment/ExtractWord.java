package javaassignment;

import java.util.Scanner;
import java.util.Vector;

/**
 * <h1>This class extract out words from a given sentence</h1>
 * This class takes a sentence as input from the user and prints every word
 * in a new line
 *
 * @author Akash Gupta
 */
public class ExtractWord {
    /**
     * This ExtractWord method uses the sentence given by user and print every word in a new line
     *
     * @param expression string Sentence whose words are extracted
     * @return void It returns nothing.It just print every word in new line
     *
     */
    public static Vector<String> extractWord(String expression){
        String word = "";
        Vector<String > v = new Vector<String>();
        for(int i=0;i<expression.length();i++)
        {

            if(expression.charAt(i)!=' ')word+=expression.charAt(i);
            else
            {
                System.out.println(word);
                v.add(word);
                word="";
            }


        }

        System.out.println(word);
        v.add(word);
        return v;
    }

    /**
     *
     * @param args Unused
     * @return Nothing
     */
    public static void main(String []args){

        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        extractWord(expression);
    }

}

