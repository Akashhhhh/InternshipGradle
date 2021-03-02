package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.CustomerDao;
import ecommerce.entity.Customer;
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

public class CustomerServiceTest {

    public static Connection con;
    public static Customer customer;
    public static LruCacheService lru;
    public static CustomerDao customerDao;
    public static Validator validator;
    public static Logger logger;
    static CustomerService customerService;

    @BeforeAll
    public static void setup() {
        con = Mockito.mock(Connection.class);
        customer = new Customer("Akash", "Gupta", "1234567890", "akash@gmail.com", "Agra", "1998-02-18");
        lru = Mockito.mock(LruCacheService.class);
        customerDao = Mockito.mock(CustomerDao.class);
        validator = Mockito.mock(Validator.class);
        logger = Mockito.mock(Logger.class);
        customerService = new CustomerService(customerDao, validator, logger);

    }

    @Test
    public void addNewCustomer() throws InvalidInputException, ApplicationRuntimeException {
        doNothing().when(validator).validateCustomer(customer);
        doNothing().when(customerDao).insertCustomerToDb(customer, con);

        when(lru.put(customer.getEmailId(), customer.getCustId())).thenReturn(true);
        customerService.addNewCustomer(customer, lru, con);

    }

    @Test
    public void testUpdateCustomerWithValidEmail() throws ApplicationRuntimeException, InvalidInputException {
        UUID custId = UUID.randomUUID();
        doNothing().when(validator).validateEmailId("akash@gmail.com");
        when(customerDao.getCustIdtByEmailId("akash@gmail.com", con)).thenReturn(custId);
        when(customerDao.updateCustomerToDb("akash@gmail.com", "agra", con)).thenReturn(true);

        when(lru.find("akash@gmail.com")).thenReturn(true);

        when(lru.put("akash@gmail.com", UUID.randomUUID())).thenReturn(true);

        customerService.updateCustomer("akash@gmail.com", "agra", lru, con);

    }

    @Test
    public void testUpdateCustomerWithInValidEmail() throws ApplicationRuntimeException, InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check email")).when(validator).validateEmailId("akasj.com");
            customerService.updateCustomer("akash.com", "agra", lru, con);

        }catch (InvalidInputException e){
              thrown = true;
        }

       assertTrue(true);


    }

    @Test
    public void testDeleteCustomerWithValidEmail() throws InvalidInputException, ApplicationRuntimeException {

        doNothing().when(validator).validateEmailId("akash@gmail.com");
        when(lru.find("akash@gmail.com")).thenReturn(true);
        when(lru.delete("akash@gmail.com")).thenReturn(true);

        customerService.deleteCustomer("akash@gmail.com", lru, con);

    }

    @Test
    public void testDeleteCustomerWithInValidEmail() throws InvalidInputException, ApplicationRuntimeException {

        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check email")).when(validator).validateEmailId("akasj");

            customerService.deleteCustomer("akasj", lru, con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }
}
