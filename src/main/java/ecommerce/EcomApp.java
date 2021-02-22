package ecommerce;

import ecommerce.cache.LruCache;
import ecommerce.controller.CustomerController;
import ecommerce.controller.OrderController;
import ecommerce.controller.ProductController;
import ecommerce.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.Class.forName;
import ecommerce.util.CreateConnection;
import java.sql.Connection;
public class EcomApp {
    private static Logger logger;


    static {


        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");

        logger = Logger.getLogger(EcomApp.class.getName());

    }

    public static void main(String[] args) throws InvalidInputException {

        Connection con=CreateConnection.create();
        boolean temp = true;
        Scanner sc = new Scanner(System.in);
        LruCache lru = new LruCache();
        while (temp) {
            logger.info("\n" + "1.Enter as admin\n" +
                    "2.Enter as customer\n" +
                    "3.Place or  Delete order\n" +
                    "4.Exit"
            );
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    ProductController obj1 = new ProductController();
                    obj1.admin(logger,con);
                    break;
                case 2:
                  CustomerController obj2 = new CustomerController();
                    obj2.customer(logger,lru,con);
                    break;
                case 3:

                    OrderController obj3 = new OrderController();
                    obj3.order(logger,lru,con);
                    break;
                case 4:
                    logger.info("Shopping Ended");
                    temp = false;
                    break;
                default:
                    continue;

            }
        }

    }


}

