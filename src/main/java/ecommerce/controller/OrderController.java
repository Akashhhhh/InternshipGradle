package ecommerce.controller;

import ecommerce.cache.LruCache;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.OrderService;
import ecommerce.service.Validate;

import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderController {
    Scanner sc = new Scanner(System.in);

    public void placeOrder(Logger logger, UUID custId, String name, Connection con)  {
        Map<UUID, Product> menu = OrderDao.menu(con);
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
                logger.info("\n"+"Product name: " + prodName + "\n" +
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
        OrderDao.addOrderToDb(od, custId, name,con);
    }

    public void deleteOrder(Logger logger, String name, Connection con) throws InvalidInputException {
        OrderService.deleteOrder(name,con);
    }

    public void showOrder(Logger logger, String name, Connection con) throws InvalidInputException {
        // OrderService.showOrder(name);
    }

    public void order(Logger logger, LruCache lru, Connection con) throws InvalidInputException {
        logger.info("Enter your email id: ");
        String email = sc.next();

        if (Validate.validateEmailId(email)) {

            // lru
            UUID cust_id;
            if (lru.find(email)) {

                cust_id = lru.get(email);
            } else {
                cust_id = OrderDao.getCustomerId(email,con);

            }
            boolean temp = true;
            while (temp) {
                logger.info("\n" + "1.Place a new Order " + "\n" + "2.Delete an Exiting order" + "\n" +
                        "3.Show orders" + "\n" +
                        "4.Exit");
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        logger.info("Provide a name to your orders: " + "\n");
                        String name1 = sc.next();
                        placeOrder(logger, cust_id, name1,con);
                        break;
                    case 2:
                        logger.info("Enter the name of your order: " + "\n");
                        String name2 = sc.next();
                        deleteOrder(logger, name2,con);
                        break;
                    case 3:
                        logger.info("Enter the name of your order: " + "\n");
                        String name3 = sc.next();
                        showOrder(logger, name3,con);
                        break;
                    case 4:
                        temp = false;
                        break;
                }


            }
        } else throw new InvalidInputException(400, "Check the emil id");

    }
}

