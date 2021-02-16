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
    private static Hashtable<String, String> dict = new Hashtable<String, String>();
    private static Logger logger;
    private final static Scanner scan = new Scanner(System.in);

    static {


        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");

        logger = Logger.getLogger(Dictionary.class.getName());

    }

    /**
     * This method is used to access the dictionary
     * @return It returns the object of hashtable type
     */
    public Hashtable<String, String> getDict(){
        return dict;
    }
    /**
     * This method is use for inserting word in dictionary
     *
     * @param word string that is added in dictionary
     * @param mean meaning of above word
     * @return It returns nothing
     */
    public static boolean insert(String word, String mean) {
        dict.put(word, mean);
        logger.info("Inserted Word: " + word + " Meaning: " + mean);
        return true;
    }

    /**
     * This method is use for searching the meaning of word in dictionary
     *
     * @param word string that is added in dictionary
     * @return It return the string
     */
    public static boolean search(String word) {
        if (dict.containsKey(word)) {
            logger.info("Meaning: " + dict.get(word));
            System.out.println("Meaning: " + dict.get(word));
            return true;
        }

        logger.info("Entered word is not found");
        System.out.println("Entered word is not found");
        return true;
    }

    /**
     * This method provide option for autoComplete
     *
     * @param s string whose autoComplete is requires
     * @return It return nothing.
     */
    public static Vector<String>  autoComplete(String s) {

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
            return ans;
        }

        logger.info("Suggestion are:");
        for (int i = 0; i < ans.size(); i++) {
            logger.info(ans.get(i));

        }
        return ans;

    }

    /**
     * This method corrects the give string
     *
     * @param s string which is needed to be corrected
     * @return It return nothing
     */
    public static String autoCorrect(String s) {
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

            if (dp[s.length()][i.length()] > temp) {
                ans.removeAllElements();
                temp = dp[s.length()][i.length()];
                ans.add(i);
            } else if (dp[s.length()][i.length()]>0&&dp[s.length()][i.length()] == temp) {
                ans.add(i);
            }

        }
        if (ans.isEmpty()) {
            logger.info("no autocorrection");
            return "";
        }

        Collections.sort(ans);
        logger.info("Auto Corrected word: " + ans.get(0));
        return ans.get(0);

    }

    /**
     * This main method is used to take user inout to create a dictionary
     *
     * @param args It is unused
     * @return it returns nothing
     */
    public static void main(String[] args) {

        boolean temp = true;

        Scanner sc = new Scanner(System.in);

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

                    insert(word1, mean);
                    break;
                case 2:
                    System.out.println("Enter the word: ");
                    String word2 = sc.next();

                    search(word2);
                    break;
                case 3:
                    System.out.println("Enter the word: ");
                    String word3 = sc.next();

                    autoComplete(word3);
                    break;
                case 4:
                    System.out.println("Enter the word: ");
                    String word4 = sc.next();

                    autoCorrect(word4);
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
