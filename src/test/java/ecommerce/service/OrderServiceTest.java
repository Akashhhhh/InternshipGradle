package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    public static Connection con;
    public static Order order;
    public static LruCacheService lru;
    public static OrderDao orderDao;
    public static Validator validator;
    public static Logger logger;
    static OrderService orderService;
    static Product product;

    @BeforeAll
    public static void setup() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        con = Mockito.mock(Connection.class);
        order = new Order(UUID.randomUUID(), 123, "12,21,34,", "uuid1,uuid2,");
        lru = Mockito.mock(LruCacheService.class);
        orderDao = Mockito.mock(OrderDao.class);
        validator = Mockito.mock(Validator.class);
        logger = Mockito.mock(Logger.class);
        orderService = new OrderService(orderDao, validator);
        product = new Product("Bike", 1234, "Sporst", "HArd", 12);
    }

    @Test
    public void testAddOrder() throws InvalidInputException, ApplicationRuntimeException {
        UUID custId = UUID.randomUUID();
        orderService.addOrder(order, custId, "Diwali", con);
    }

    @Test
    public void testDeleteOrder() throws ApplicationRuntimeException, InvalidInputException {

        doNothing().when(validator).validateString("Diwali");
        orderService.deleteOrder("Diwali", con);
    }

    @Test
    public void testDeleteOrderWhenException() throws ApplicationRuntimeException, InvalidInputException {

        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateString("kawasaki1");
            orderService.deleteOrder("kawasaki1", con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);


    }


    @Test
    public void testShowOrder() throws ApplicationRuntimeException, InvalidInputException {

        doNothing().when(validator).validateString("Diwali");
        orderService.showOrder("Diwali", con, order);

    }

    @Test
    public void testShowOrderWithException() throws ApplicationRuntimeException, InvalidInputException {

        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateString("kawasaki1");

            orderService.showOrder("kawasaki1", con, order);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testCheckEmailId() throws ApplicationRuntimeException, InvalidInputException {
        UUID custId = UUID.randomUUID();
        doNothing().when(validator).validateEmailId("akash@gmail.com");
        when(lru.find("akash@gmail.com")).thenReturn(true);
        when(lru.get("akash@gmail.com")).thenReturn(custId);
        orderService.CheckEmailId("akash@gmail.com", lru, con);

    }

    @Test
    public void testCheckEmailIdWithException() throws ApplicationRuntimeException, InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateEmailId("akaah");

            orderService.CheckEmailId("akaah", lru, con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }

}
