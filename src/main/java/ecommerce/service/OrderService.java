package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.controller.OrderController;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.OrderDisplayResponseModel;

import java.sql.Connection;
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
     * @param orderId name of the order
     * @param con  connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteOrder(String orderId, Connection con) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateUUID(orderId);
        orderDao.deleteOrderToDb(UUID.fromString(orderId), con);

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
    public UUID CheckEmailId(String email, LruCacheService lru, Connection con) throws ApplicationRuntimeException, InvalidInputException {
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
     * @param orderId name of order
     * @param con  connection
     * @throws InvalidInputException for throwing user error
     */
    public OrderDisplayResponseModel showOrder(String orderId, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateUUID(orderId);
        Order order=null;
        order=orderDao.showOrderToDb(UUID.fromString(orderId),con);
        if(order==null){
            throw new InvalidInputException(400,"order does not exist");
        }
        OrderDisplayResponseModel orderDisplayResponseModel = new OrderDisplayResponseModel(UUID.fromString(orderId),order.getCustId(),order.getTotalPrice(),order.getQuantity(),order.getProductIds());
      return orderDisplayResponseModel;
    }



    public void addOrder(Order order, UUID custId, String name, Connection con) throws ApplicationRuntimeException, InvalidInputException {
        if(validator.validateOrder(order)){
            orderDao.addOrderToDb(order, custId, name, con);
        }

    }

    public float getProductCost(UUID u, Connection con) throws ApplicationRuntimeException {
       return orderDao.getProductCostFromDb(u,con);

    }

    public void deleteOrderByCustId(UUID custId, Connection con) throws ApplicationRuntimeException {
        orderDao.deleteOrderByCustIdToDb(custId,con);
    }

    public void checkUUID(String s) throws InvalidInputException {
        validator.validateUUID(s);
    }


    public int getQuantity(UUID u, Connection con) throws ApplicationRuntimeException {
        return orderDao.getQuantityToDb(u,con);
    }

    public void checkQt(Integer n, int qt) throws InvalidInputException {
        validator.validateQt(n,qt);
    }
}
