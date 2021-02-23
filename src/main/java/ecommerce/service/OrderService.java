package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.controller.OrderController;
import ecommerce.dao.OrderDao;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;

import java.sql.Connection;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for validating the user inputs
 *
 * @author Akash Gupta
 */
public class OrderService {
    /**
     * This is used for validation when order is deleted
     *
     * @param name name of the order
     * @param con  connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    private static Logger logger = java.util.logging.Logger.getLogger(OrderController.class.getName());
    OrderDao orderDao = new OrderDao();

    public static void deleteOrder(String name, Connection con) throws ApplicationRuntimeException, InvalidInputException {
        if (Validator.validateString(name)) {
            OrderDao.deleteOrderToDb(name, con);
        } else throw new InvalidInputException(400, "Check the name");
    }

    public UUID CheckEmailId(String email, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        if (Validator.validateEmailId(email)) {
            UUID cust_id = null;
            if (lru.find(email)) {
                cust_id = lru.get(email);
                logger.info("Customer id is retrieve from cache");
            } else {
                cust_id = orderDao.getCustomerId(email, con);

            }
            return cust_id;
        } else throw new InvalidInputException(400, "Check the email");
    }


//    public static void showOrder(String name) throws InvalidInputException {
//        if(Validate.validateString(name)){
//            OrderDao.showOrderToDb(name);
//        }
//        else throw new InvalidInputException(400, "Check the name");
//    }
}
