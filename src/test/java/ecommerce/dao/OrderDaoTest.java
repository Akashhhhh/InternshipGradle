package ecommerce.dao;

import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class OrderDaoTest {
    static Connection con;
    static Order order;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static OrderDao orderDao;
    public static Logger logger;
    static Product product;

    @BeforeAll
    public static void setup() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        con = Mockito.mock(Connection.class);
        order = new Order(UUID.randomUUID(),123,"12,13,",String.valueOf(u1)+","+String.valueOf(u2)+",");
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        orderDao =Mockito.mock(OrderDao.class);
        logger = Mockito.mock(Logger.class);
        product = new Product("Aventus",123,"EDP","Body Spry",12);

        orderDao = new OrderDao(logger);
    }


    @Test
    public void testAddOrderToDatabase() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.addOrderToDb(order,UUID.randomUUID(),"order",con);

    }
    @Test
    public void testDeleteOrderToDatabase() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.deleteOrderToDb("order",con);

    }
    @Test
    public void testShowOrderToDatabase() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.showOrderToDb("order",con,order);

    }
}
