package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.OrderService;

import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for managing place,delete and show order operation
 *
 * @author Akash gupta
 */
public class OrderController {
    Scanner sc = new Scanner(System.in);
    private static Logger logger = java.util.logging.Logger.getLogger(OrderController.class.getName());
    OrderDao orderDao = new OrderDao();
    OrderService orderService = new OrderService();

    /**
     * This class is used for placing orders
     *
     * @param custId customer id
     * @param name   name of product
     * @param con    connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void placeOrder(UUID custId, String name, Connection con) throws ApplicationRuntimeException{
        Map<UUID, Product> menu = orderDao.menu(con);
        float totalPrice = 0;
        String prodIds = "";
        String quantities = "";
        for (Map.Entry<UUID, Product> entry : menu.entrySet()) {
            Product p = entry.getValue();
            String prodName = p.getProdName();
            String desc = p.getDescription();
            String type = p.getType();
            float sell_price = p.getSellPrice();
            int qt = p.getQuantity();
            if (qt >= 1) {
                logger.info("\n" + "Product name: " + prodName + "\n" +
                        "Description: " + desc + "\n" +
                        "Type:" + type + "\n" + "\n" +
                        "Selling Price: " + sell_price + "\n"

                );
                logger.info("Do you want to buy: Y/N");
                String c = sc.next();
                if (c.equals("Y")) {
                    totalPrice += sell_price;
                    prodIds += p.getProdId() + ",";
                    quantities += p.getQuantity() + ",";
                }
            }

        }
        Order od = new Order(custId, totalPrice, quantities, prodIds);
        try {
            orderDao.addOrderToDb(od, custId, name, con);
        } catch (ApplicationRuntimeException e) {
            logger.info("Error Code: " + e.getErrorCode() + "|" + "Error Description: " + e.getErrorDesc());
        }
    }

    /**
     * This class is used when order is deleted from database
     *
     * @param name name of product
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteOrder(String name, Connection con) throws ApplicationRuntimeException {
        try {
            orderService.deleteOrder(name, con);
        } catch (InvalidInputException e) {
            logger.info("Error Code: " + e.getErroCode() + "|" + "Error Description: " + e.getErrorDesc());

        } catch (ApplicationRuntimeException e) {
            logger.info("Error Code: " + e.getErrorCode() + "|" + "Error Description: " + e.getErrorDesc());
        }
    }

    /**
     * This class is used for showing order
     *
     * @param name product name
     * @param con  connection
     * @throws InvalidInputException for throwing user error
     */
    public void showOrder(String name, Connection con) throws InvalidInputException {
        // OrderService.showOrder(name);
    }

    /**
     * This class provide an interface
     *
     * @param lru cache
     * @param con connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void order(LruCacheService lru, Connection con) throws ApplicationRuntimeException {
        logger.info("Enter your email id: ");
        String email = sc.next();
        UUID cust_id = null;
        try {
            cust_id = orderService.CheckEmailId(email, lru, con);

        } catch (InvalidInputException e) {
            logger.info("Error Code: " + e.getErroCode() + "|" + "Error Description: " + e.getErrorDesc());

        } catch (ApplicationRuntimeException e) {
            logger.info("Error Code: " + e.getErrorCode() + "|" + "Error Description: " + e.getErrorDesc());
        }

        boolean temp = true;
        while (temp) {
            logger.info("\n" + "1.Place a new Order " + "\n" + "2.Delete an Exiting order" + "\n" +
                    "3.Show orders" + "\n" +
                    "4.Exit");
            int n = sc.nextInt();
            try {
                switch (n) {

                    case 1:
                        logger.info("Provide a name to your orders: " + "\n");
                        String name1 = sc.next();
                        placeOrder(cust_id, name1, con);
                        break;
                    case 2:
                        logger.info("Enter the name of your order: " + "\n");
                        String name2 = sc.next();
                        deleteOrder(name2, con);
                        break;
                    case 3:
                        logger.info("Enter the name of your order: " + "\n");
                        String name3 = sc.next();
                        showOrder(name3, con);
                        break;
                    case 4:
                        temp = false;
                        break;


                }
            } catch (InvalidInputException e) {
                logger.info("Error Code: " + e.getErroCode() + "|" + "Error Description: " + e.getErrorDesc());

            } catch (ApplicationRuntimeException e) {
                logger.info("Error Code: " + e.getErrorCode() + "|" + "Error Description: " + e.getErrorDesc());
            }


        }


    }
}

