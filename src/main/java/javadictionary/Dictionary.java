package javadictionary;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Logger;


/**
 * This class implement the basic insert,search,autocorrect and autocomplete feature of dictionary
 *
 * @author Akash Gupta
 */
public class Dictionary {
    private static Logger logger;
    private final static Scanner scan = new Scanner(System.in);
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");

        logger= Logger.getLogger(Dictionary.class.getName());

    }

    /**
     * This method is use for inserting word in dictionary
     *
     * @param word string that is added in dictionary
     * @param mean meaning of above word
     * @param dict hashtable which is used as dictionary
     * @return It returns nothing
     */
    public static void insert(String word, String mean, Hashtable<String, String> dict) {
        dict.put(word, mean);
        logger.info("Inserted Word: "+word+ " Meaning: "+mean);

    }

    /**
     * This method is use for searching the meaning of word in dictionary
     *
     * @param word string that is added in dictionary
     * @param dict hashtable which is used as dictionary
     * @return It return the string
     */
    public static String search(String word, Hashtable<String, String> dict) {
        if(dict.containsKey(word)){
            logger.info("Meaning: "+ dict.get(word));
        }
        else{
            logger.warning("Entered word is not found");
        }
        return dict.get(word);

    }

    /**
     * This method provide option for autoComplete
     *
     * @param s string whose autoComplete is required
     * @param dict hashtable which is used as dictionary
     * @return It return nothing.
     */
    public static void autoComplete(String s, Hashtable<String, String> dict) {

        int flag = 0;
        Vector<String> ans = new Vector<String>();
        for (String i : dict.keySet()) {
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == i.charAt(j)) count++;
                else break;
            }
            if (count == s.length()) {
                ans.add(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            logger.info("There are no suggestion");

        }
        else{
            logger.info("Suggestion are:");
            for(int i=0;i<ans.size();i++){
                logger.info(ans.get(i));
            }
        }

    }

    /**
     * This method corrects the give string
     *
     * @param s string which is needed to be corrected
     * @param dict hashtable which is used as dictionary
     * @return It return nothing
     */
    public static void autoCorrect(String s, Hashtable<String, String> dict) {
        int temp = 0;
        Vector<String> ans = new Vector<String>();
        for (String i : dict.keySet()) {
            int[][] dp = new int[s.length() + 1][i.length() + 1];
            for (int j = 0; j <= s.length(); j++) {
                for (int k = 0; k <= i.length(); k++) {
                    if (j == 0 || k == 0) {
                        dp[j][k] = 0;
                    } else if (s.charAt(j - 1) == i.charAt(k - 1)) {
                        dp[j][k] = dp[j - 1][k - 1] + 1;
                    } else
                        dp[j][k] = Math.max(dp[j - 1][k], dp[j][k - 1]);
                }
            }

            if(dp[s.length()][i.length()]>temp){
                ans.removeAllElements();
                temp = dp[s.length()][i.length()];
                ans.add(i);
            }
            else if(dp[s.length()][i.length()]==temp){
                ans.add(i);
            }

        }
        if(!ans.isEmpty()){
            Collections.sort(ans);
            logger.info("Auto Corrected word: "+ans.get(0));
        }


    }

    /**
     * This main method is used to take user inout to create a dictionary
     *
     * @param args It is unused
     * @return it returns nothing
     */
    public static void main(String[] args) {

        boolean temp = true;
        Hashtable<String, String> dict = new Hashtable<String, String>();
        Scanner sc = new Scanner(System.in);
        //Scanner sc1 = new Scanner(System.in);
        while (temp) {
            System.out.println("1.insert\n" +
                    "2.search\n" +
                    "3.autoComplete\n" +
                    "4.autoCorrect\n" +
                    "5.Exit"
            );

            int s = sc.nextInt();
            switch (s) {
                case 1:
                    System.out.println("Enter the word: ");
                    String word1 = sc.next();

                    System.out.println("Enter the meaning: ");
                    String mean = sc.next();

                    insert(word1, mean, dict);
                    break;
                case 2:
                    System.out.println("Enter the word: ");
                    String word2 = sc.next();

                    System.out.println("Meaning: " + search(word2, dict));
                    break;
                case 3:
                    System.out.println("Enter the word: ");
                    String word3 = sc.next();

                    autoComplete(word3, dict);
                    break;
                case 4:
                    System.out.println("Enter the word: ");
                    String word4 = sc.next();

                    autoCorrect(word4, dict);
                    break;
                case 5:
                    temp = false;
                    logger.info("Dictionary is ended");
                    break;
                default:
                    continue;

            }

        }
    }
}
