package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ProductCreateRequestModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    public static Connection con;
    public static Order order;
    public static LruCacheService lru;
    public static OrderDao orderDao;
    public static Validator validator;
    public static Logger logger;
    static OrderService orderService;
    static Product product;
    static ProductCreateRequestModel productModel;

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
        productModel = new ProductCreateRequestModel();

    }

    @Test
    public void testAddOrder() throws InvalidInputException, ApplicationRuntimeException {

        UUID custId = UUID.randomUUID();
        orderService.addOrder(order, custId, "Diwali", con);
    }



    @Test
    public void testDeleteOrder() throws ApplicationRuntimeException, InvalidInputException {


        orderService.deleteOrder("akask", con);
    }
    @Test
    public void testDeleteOrderWhenException() throws ApplicationRuntimeException, InvalidInputException {


        boolean thrown = false;
        try {
            orderService.deleteOrder("akask1", con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);


    }

    @Test
    public void testShowOrder() throws ApplicationRuntimeException, InvalidInputException {

        orderService.showOrder("Diwali", con);

    }
    @Test
    public void testShowOrderWithException() throws ApplicationRuntimeException, InvalidInputException {

        boolean thrown = false;
        try {
            orderService.showOrder("akask1",con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testCheckEmailId() throws ApplicationRuntimeException, InvalidInputException {
        UUID custId = UUID.randomUUID();
        when(orderService.CheckEmailId("akash@gmail.com", lru, con)).thenReturn(custId);
        when(lru.find("akash@gmail.com")).thenReturn(true);
        orderService.CheckEmailId("akash@gmail.com", lru, con);

    }
    @Test
    public void testCheckEmailIdWithException() throws ApplicationRuntimeException, InvalidInputException {
        boolean thrown = false;
        try {
            orderService.CheckEmailId("akask",lru, con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }
    @Test
    public void testGetMenu() throws ApplicationRuntimeException, InvalidInputException {

        Vector<ProductCreateRequestModel>menu =new Vector<>();
        menu.add(productModel);
        when(orderService.getMenu(con)).thenReturn(menu);
        orderService.getMenu(con);

    }
}
