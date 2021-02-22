package ecommerce.exception;
import java.util.logging.Logger;

public class InvalidInputException extends Exception{
    private static Logger logger;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");
        logger= java.util.logging.Logger.getLogger(InvalidInputException.class.getName());
    }
    public InvalidInputException(int errorCode, String errorMessage){
        logger.info(errorMessage);
    }
}
