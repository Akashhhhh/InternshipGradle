package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.controller.OrderController;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;

import java.sql.Connection;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for validating the user inputs
 *
 * @author Akash Gupta
 */
public class OrderService {

    private static Logger logger = java.util.logging.Logger.getLogger(OrderController.class.getName());
    OrderDao orderDao;
    Validator validator;

    public OrderService(OrderDao orderDao, Validator validator){
        this.orderDao= orderDao;
        this.validator = validator;

    }
    public OrderService(){
        orderDao = new OrderDao();
        validator = new Validator();

    }

    /**
     * This is used for validation when order is deleted
     *
     * @param name name of the order
     * @param con  connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteOrder(String name, Connection con) throws ApplicationRuntimeException, InvalidInputException {
        if (validator.validateString(name)) {
            orderDao.deleteOrderToDb(name, con);
        } else throw new InvalidInputException(400, "Check the name");
    }

    /**
     * This method is used for validating email id
     *
     * @param email email id
     * @param lru   cache
     * @param con   connection
     * @return it returns the customer id
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public UUID CheckEmailId(String email, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        if (validator.validateEmailId(email)) {
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

    /**
     * This method is used for validating when order is shown
     *
     * @param name name of customer
     * @param con  connection
     * @param order
     * @throws InvalidInputException for throwing user error
     */
    public void showOrder(String name, Connection con, Order order) throws InvalidInputException, ApplicationRuntimeException {
        if (validator.validateString(name)) {
            orderDao.showOrderToDb(name,con,order);
        } else throw new InvalidInputException(400, "Check the name");
    }

    public Map<UUID, Product> getMenu(Connection con) throws ApplicationRuntimeException {
        Map<UUID, Product> menu=orderDao.menu(con);
        return menu;
    }

    public void addOrder(Order od, UUID custId, String name, Connection con) throws ApplicationRuntimeException {
        orderDao.addOrderToDb(od, custId, name, con);
    }
}
