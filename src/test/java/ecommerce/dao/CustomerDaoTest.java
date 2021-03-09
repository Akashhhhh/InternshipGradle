package ecommerce.dao;

import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.model.CustomerModel;
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

public class CustomerDaoTest {
     Connection con;
     Customer customer;
     CustomerModel customerModel;
     PreparedStatement preparedStatement;
     ResultSet resultSet;
     CustomerDao customerDao;
     public static Logger logger;

    @BeforeEach
    public  void setup() {
        con = Mockito.mock(Connection.class);
        customer = new Customer("Akash", "Gupta", "1234567890", "akash@gmail.com", "Agra", "1998-02-18");
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        customerDao = new CustomerDao();
        logger = Mockito.mock(Logger.class);
        customerModel= new CustomerModel("Akash", "Gupta", "1234567890", "akash@gmail.com", "Agra", "1998-02-18");

    }

    @Test
    public void testInsertCustomerToDb() throws SQLException, ApplicationRuntimeException {
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            customerDao.insertCustomerToDb(customerModel, con);

    }
    @Test
    public void testInsertCustomerToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            customerDao.insertCustomerToDb(customerModel, con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Customer is not updated in database", e.getErrorDesc());
        }

    }

    @Test
    public void testDeleteCustomerToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        customerDao.deleteCustomerToDb("akash@gmail.com", con);
    }
    @Test
    public void testDeleteCustomerToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            customerDao.deleteCustomerToDb("akash@gmail.com", con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Customer is not deleted in database", e.getErrorDesc());
        }

    }

    @Test
    public void testUpdateCustomerToDb() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        customerDao.updateCustomerToDb("akash@gamail.com", "Agra", con);
    }

    @Test
    public void testUpdateCustomerToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            customerDao.updateCustomerToDb("akash@gmail.com","Agra", con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Customer is not updated in database", e.getErrorDesc());
        }

    }
    @Test
    public void testGetCustIdByEmailId() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getObject(1)).thenReturn(UUID.randomUUID());
        customerDao.getCustIdtByEmailId("akash@gamail.com", con);
    }
    @Test
    public void testGetCustIdByEmailIdWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            customerDao.getCustIdtByEmailId("akash@gmail.com",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Customer Id is not fetched", e.getErrorDesc());
        }

    }
    @Test
    public void testDisplayUserToDb() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        customerDao.displayUsersToDb("akash@gamail.com", con);
    }
    @Test
    public void testDisplayUserToDbWithException() throws SQLException, ApplicationRuntimeException {

        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            customerDao.displayUsersToDb("akash@gamail.com", con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Customers are not displayed", e.getErrorDesc());

        }
    }



}
