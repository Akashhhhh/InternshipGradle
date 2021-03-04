package ecommerce;

import ecommerce.cache.LruCacheService;
import ecommerce.controller.CustomerController;
import ecommerce.controller.OrderController;
import ecommerce.controller.ProductController;
import ecommerce.util.Connection;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is used to provide an interface to user
 *
 * @author Akash Gupta
 */
public class EcomApp {
    static Logger logger;
    static java.sql.Connection con;
    static LruCacheService lru;

    /**
     * This method is used to establish connection and create a cache
     */
    private static void initializeResources() {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");
        con = Connection.create();
        lru = new LruCacheService();
    }

    /**
     * This method is the entry point of our application
     *
     * @param args Unused
     */
    public static void main(String[] args)  {
        initializeResources();
        logger = Logger.getLogger(EcomApp.class.getName());
        boolean temp = true;
        Scanner sc = new Scanner(System.in);

        while (temp) {
            logger.info("\n" + "1.Enter as admin\n" +
                    "2.Enter as customer\n" +
                    "3.Place or  Delete order\n" +
                    "4.Exit"
            );
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    ProductController productController = new ProductController();
                    productController.admin(con);
                    break;
                case 2:
                    CustomerController customerController = new CustomerController();
                    customerController.customer(lru, con);
                    break;
                case 3:

                    OrderController orderController = new OrderController();
                    orderController.order(lru, con);
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

