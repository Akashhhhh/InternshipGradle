package ecommerce;

import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is used to provide an interface to user
 *
 * @author Akash Gupta
 */
@SpringBootApplication
public class EcomApp {


    public static void main(String[] args) throws InvalidInputException, ApplicationRuntimeException {

        SpringApplication.run(EcomApp.class);


    }


}

