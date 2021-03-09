package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.CustomerDao;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.CustomerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

     private Connection con;
     private Customer customer;
     private CustomerModel customerModel;
     private LruCacheService lru;
     private CustomerDao customerDao;
    private Validator validator;
    private Logger logger;
    private CustomerService customerService;
    private OrderService orderService;
    private OrderDao orderDao;

    @BeforeEach
    public  void setup() {
        con = Mockito.mock(Connection.class);
        customer = new Customer("Akash", "Gupta", "1234567890", "akash@gmail.com", "Agra", "1998-02-18");
        lru = Mockito.mock(LruCacheService.class);
        customerDao = Mockito.mock(CustomerDao.class);
        orderDao  = Mockito.mock(OrderDao.class);
        validator = Mockito.mock(Validator.class);
        logger = Mockito.mock(Logger.class);
        orderService = Mockito.mock(OrderService.class);
        customerService = new CustomerService(customerDao, validator, logger,orderService);
        customerModel = new CustomerModel("Akash", "Gupta", "1234567890", "akash@gmail.com", "Agra", "1998-02-18");

    }

    @Test
    public void testAddNewCustomer() throws InvalidInputException, ApplicationRuntimeException {

        when(validator.validateCustomer(customerModel)).thenReturn(true);
        doNothing().when(customerDao).insertCustomerToDb(customerModel, con);
        when(lru.put(customer.getEmailId(), customer.getCustId())).thenReturn(true);
        customerService.addNewCustomer(customerModel, lru, con);

    }

    @Test
    public void testUpdateCustomerWithValidEmail() throws ApplicationRuntimeException, InvalidInputException {

        doNothing().when(customerDao).updateCustomerToDb("akash@gmail.com", "agra",con);
        when(lru.find("akash@gmail.com")).thenReturn(true);
        when(lru.put("akash@gmail.com", UUID.randomUUID())).thenReturn(true);
        customerService.updateCustomer("akash@gmail.com", "agra", lru, con);

    }

    @Test
    public void testUpdateCustomerWithInValidEmail() throws ApplicationRuntimeException, InvalidInputException {
        try {

            doThrow(new InvalidInputException(400, "Check the email id")).when(validator).validateEmailId("akash.com");
            customerService.updateCustomer("akash.com", "agra", lru, con);

        } catch (InvalidInputException e) {
            assertEquals("Check the email id", e.getErrorDesc());
        }

    }

    @Test
    public void testDeleteCustomerWithValidEmail() throws InvalidInputException, ApplicationRuntimeException {

        when(validator.validateCustomer(customerModel)).thenReturn(true);
        when(customerDao.getCustIdtByEmailId("akash@gmail.com",con)).thenReturn(UUID.randomUUID());

        doNothing().when(orderService).deleteOrderByCustId(UUID.randomUUID(),con);
        doNothing().when(customerDao).deleteCustomerToDb("akash@gmail.com",con);
        when(lru.find("akash@gmail.com")).thenReturn(true);
        when(lru.delete("akash@gmail.com")).thenReturn(true);
        customerService.deleteCustomer("akash@gmail.com", lru, con);

    }

    @Test
    public void testDeleteUserWithInValidEmail() throws InvalidInputException, ApplicationRuntimeException {
        try {

            doThrow(new InvalidInputException(400, "Check the email id")).when(validator).validateEmailId("akash.com");
            customerService.deleteCustomer("akash.com", lru, con);

        } catch (InvalidInputException e) {
            assertEquals("Check the email id", e.getErrorDesc());
        }

    }

    @Test
    public void testDisplayUsers() throws InvalidInputException, ApplicationRuntimeException {

        when(validator.validateCustomer(customerModel)).thenReturn(true);
        when(customerDao.displayUsersToDb("akash@gmail.com",con)).thenReturn(customerModel);

        customerService.displayUsers("akash@gmail.com", con);

    }

    @Test
    public void testDisplayUsersWithInValidEmail() throws InvalidInputException, ApplicationRuntimeException {
        try {

            doThrow(new InvalidInputException(400, "Check the email id")).when(validator).validateEmailId("akash");
            customerService.displayUsers("akash.com",con);

        } catch (InvalidInputException e) {
            assertEquals("Check the email id", e.getErrorDesc());
        }

    }

}
