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
    public void testMenu() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        orderDao.menu(con);

    }
    @Test
    public void testMenuWithException() throws SQLException, ApplicationRuntimeException {
       try{
           when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
           when(preparedStatement.executeQuery()).thenThrow(new SQLException());
           when(resultSet.next()).thenReturn(true).thenReturn(false);
           orderDao.menu(con);
       }catch (ApplicationRuntimeException e){
           assertEquals("Menu is not displayed",e.getErrorDesc());
       }

    }



    @Test
    public void testGetCustomerId() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        orderDao.getCustomerId("akash@gmail.com",con);

    }
    @Test
    public void testGetCustomerIdWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            orderDao.getCustomerId("akash@gmail.com",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Customer Id is not fetched",e.getErrorDesc());
        }

    }
    @Test
    public void testAddOrderToDatabase() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.addOrderToDb(order,UUID.randomUUID(),"order",con);

    }
    @Test
    public void testAddOrderToDatabaseWithException() throws SQLException, ApplicationRuntimeException {
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
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.deleteOrderToDb("order",con);

    }
    @Test
    public void testDeleteOrderToDatabaseWithException() throws SQLException, ApplicationRuntimeException {
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
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        orderDao.showOrderToDb("order",con);

    }
    @Test
    public void testShowOrderToDatabaseWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            orderDao.showOrderToDb("order",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Order cannot be shown",e.getErrorDesc());
        }

    }

    @Test
    public void testGetProductCostFromDb()throws SQLException, ApplicationRuntimeException{
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.getProductCostFromDb(UUID.randomUUID(),con);
    }

    @Test
    public void testGetProductCostFromDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            orderDao.getProductCostFromDb(UUID.randomUUID(),con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Cant get cost",e.getErrorDesc());
        }

    }

    @Test
    public void testdeleteOrderByCustIdToDb()throws SQLException, ApplicationRuntimeException{
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.deleteOrderByCustIdToDb(UUID.randomUUID(),con);
    }

    @Test
    public void testdeleteOrderByCustIdToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            orderDao.deleteOrderByCustIdToDb(UUID.randomUUID(),con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Cant delete the orders",e.getErrorDesc());
        }

    }
}
