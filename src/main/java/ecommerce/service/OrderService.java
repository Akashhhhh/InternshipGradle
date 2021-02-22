package ecommerce.service;
import ecommerce.dao.OrderDao;
import ecommerce.exception.InvalidInputException;

import java.sql.Connection;

public class OrderService {
    public static void deleteOrder(String name, Connection con) throws InvalidInputException {
        if(Validate.validateString(name)){
            OrderDao.deleteOrderToDb(name,con);
        }
        else throw new InvalidInputException(400, "Check the name");
    }
//    public static void showOrder(String name) throws InvalidInputException {
//        if(Validate.validateString(name)){
//            OrderDao.showOrderToDb(name);
//        }
//        else throw new InvalidInputException(400, "Check the name");
//    }
}
