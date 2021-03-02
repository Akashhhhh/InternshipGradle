package ecommerce.dao;

import ecommerce.entity.Customer;
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

public class CustomerDaoTest {
    static Connection con;
    static Customer customer;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static CustomerDao customerDao;
    public static Logger logger;

    @BeforeAll
    public static void setup() {
        con = Mockito.mock(Connection.class);
        customer = new Customer("Akash", "Gupta", "1234567890", "akash@gmail.com", "Agra", "1998-02-18");
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        customerDao = new CustomerDao();
        logger = Mockito.mock(Logger.class);
    }

    @Test
    public void testInsertCustomerToDb() throws SQLException, ApplicationRuntimeException {
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            customerDao.insertCustomerToDb(customer, con);

    }

    @Test
    public void testDeleteCustomerToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        customerDao.deleteCustomerToDb("akash@gmail.com", con);
    }

    @Test
    public void testUpdateCustomerToDb() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        customerDao.updateCustomerToDb("akash@gamail.com", "Agra", con);
    }

    @Test
    public void testGetCustIdByEmailId() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getObject(1)).thenReturn(UUID.randomUUID());
        customerDao.getCustIdtByEmailId("akash@gamail.com", con);
    }

    @Test
    public void testGetCustomerId() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        customerDao.getCustomerId("akash@gamail.com", con);
    }

}
