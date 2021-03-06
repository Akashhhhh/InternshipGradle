package javadictionary;
import java.util.Vector;
import java.util.logging.Logger;

public class JavaDictionaryTest {

     static Dictionary javaDictionary;
     private static Logger logger;
    static {


        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");

        logger = Logger.getLogger(Dictionary.class.getName());

    }
     public static boolean dictTest(){
         if(javaDictionary.getDict().isEmpty()){
             return true;
         }
         return false;
     }
     public static  boolean autoCorrectTest(){
         if(javaDictionary.autoCorrect("aa").equals("aash") && javaDictionary.autoCorrect("p").equals("")){
             return true;
         }
         return false;
     }
     public static boolean autoCompleteTest(){
         Vector<String> ans1=javaDictionary.autoComplete("ak");
         Vector<String> ans2=javaDictionary.autoComplete("pp");
         Vector<String> v1 = new Vector<String>();
         v1.add("akaaa");
         v1.add("aksd");
         v1.add("akash");
         if(ans1.equals(v1)&&ans2.isEmpty()){
             return true;
         }
         return false;
     }
     public static  boolean searchTest(){
         if(javaDictionary.search("akash")&&javaDictionary.search("pp")){
            return true;
         }
         return false;

     }
     public static boolean insertTest() {


        javaDictionary.insert("akash", "gupta");
        javaDictionary.insert("akaaa", "gupt");
        javaDictionary.insert("aksd", "gup");
        javaDictionary.insert("aash", "fdrd");
        if(javaDictionary.getDict().containsKey("akash")){

            return true;
        }

        return false;
    }

    public static void main(String[] args){
        javaDictionary = new Dictionary();
        if(dictTest()){
            System.out.println("Dictionary test passed");
            logger.info("Dictionary test passed");
            if(insertTest()){
                System.out.println("Insert test passed");
                logger.info("Insert test passed");
                if(searchTest()){
                    System.out.println("Search test passed");
                    logger.info("Search test passed");
                    if(autoCompleteTest()){
                        System.out.println("AutoComplete test passed");
                        logger.info("AutoComplete test passed");
                        if(autoCorrectTest()){
                            System.out.println("AutoCorrect test passed");
                            logger.info("AutoCorrect test passed");
                        }
                        else{
                            System.out.println("AutoCorrect test failed");
                            logger.info("AutoCorrect test failed");
                        }
                    }
                    else{
                        System.out.println("AutoComplete test failed");
                        logger.info("AutoComplete test failed");
                    }
                }
                else{
                    System.out.println("Search test failed");
                    logger.info("Search test failed");
                }
            }
            else{
                System.out.println("Insert test failed");
                logger.info("Insert test failed");
            }
        }
        else{
            System.out.println("Dictionary test failed");
            logger.info("Dictionary test failed");
        }




    }
}
