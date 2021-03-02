package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for managing place,delete and show order operation
 *
 * @author Akash gupta
 */
@RequestMapping("/user")
@RestController
public class OrderController {

    OrderDao orderDao = new OrderDao();
    OrderService orderService = new OrderService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @PostMapping("/addProduct")
    public void placeOrder(UUID custId, String name, Connection con) throws ApplicationRuntimeException, InvalidInputException {

        UUID cust_id = null;

        cust_id = orderService.CheckEmailId(email, lru, con);
        Map<UUID, Product> menu = null;

        menu = orderService.getMenu(con);

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
        Set<ConstraintViolation<Order>> constraintViolations = validator.validate(od);
        if(constraintViolations.size() > 0) {
            throw new InvalidInputException(400, constraintViolations.iterator().next().getMessage());
        }
        orderService.addOrder(od,custId,name,con);


    }

    /**
     * This class is used when order is deleted from database
     *
     * @param name name of product
     * @param con  connection
     */
    @DeleteMapping("/deleteOrder")
    public String deleteOrder(String name, Connection con) {

        try {
            orderService.deleteOrder(name, con);
            return "Order Deleted";
        } catch (ApplicationRuntimeException e) {
           e.logError();
        } catch (InvalidInputException e) {
            e.logError();
        }
       return "Order is not deleted";
    }

    /**
     * This class is used for showing order
     *
     * @param name product name
     * @param con  connection
     */
    public void showOrder(String name, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        Order order=null;
        orderService.showOrder(name, con,order);

    }



}

