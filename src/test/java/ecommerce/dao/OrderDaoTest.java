package ecommerce.dao;

import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class OrderDaoTest {
     Connection con;
     Order order;
     PreparedStatement preparedStatement;
     ResultSet resultSet;
     OrderDao orderDao;
     public static Logger logger;
     Product product;

    @BeforeEach
    public  void setup() {
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
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            orderDao.addOrderToDb(order,UUID.randomUUID(),"order",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Order is not added to database",e.getErrorDesc());

        }

    }
    @Test
    public void testDeleteOrderToDatabase() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            orderDao.deleteOrderToDb("order",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Order is not deleted in database",e.getErrorDesc());

        }

    }
    @Test
    public void testShowOrderToDatabase() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            orderDao.showOrderToDb("order",con,order);
        }catch (ApplicationRuntimeException e){
            assertEquals("Order cannot be shown",e.getErrorDesc());

        }

    }
}
