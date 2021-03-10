package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.CustomerDao;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.CustomerDisplayResponseModel;

import java.sql.Connection;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for validating the inputs
 *
 * @author Akash Gupta
 */
public class CustomerService {
    private static Logger logger;
    CustomerDao customerDao;
    Validator validator ;
    OrderService orderService;
    public CustomerService(CustomerDao customerDao,Validator validator,Logger logger,OrderService orderService){
      this.customerDao = customerDao;
      this.validator = validator;
      this.logger = logger;
      this.orderService = orderService;
    }
    public CustomerService(){
        customerDao = new CustomerDao();
        validator = new Validator();
        logger=java.util.logging.Logger.getLogger(CustomerService.class.getName());
        orderService = new OrderService();
    }


    /**
     * This class is used for validation when customer is added to database
     *
     * @param customer object of customer class
     * @param lru cache
     * @param con connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void addNewCustomer(Customer customer, LruCacheService lru , Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateCustomer(customer);

        customerDao.insertCustomerToDb(customer, con);

        lru.put(customer.getEmailId(), customer.getCustId());
        logger.info("Customer added into cache");

    }

    /**
     * This class is used for validation when customer information is updated in database
     *
     * @param custId id of customer
     * @param address address that is updated
     * @param lru     cache
     * @param con     connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void updateCustomer(UUID custId, String address, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateUUID(custId.toString());

        customerDao.updateCustomerToDb(custId, address, con);



    }

    /**
     * This class is used for validation when customer information is deleted from database
     *
     * @param custId customer id of customer
     * @param lru   cache
     * @param con   connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteCustomer(UUID custId, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateUUID(custId.toString());
        orderService.deleteOrderByCustId(UUID.fromString(String.valueOf(custId)),con);
        customerDao.deleteCustomerToDb(custId, con);


    }
    public CustomerDisplayResponseModel displayUsers(UUID custId, Connection con) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateUUID(custId.toString());
        Customer customer =null;
        customer=customerDao.displayUsersToDb(custId,con);

        if(customer==null){
            throw new InvalidInputException(400,"User does not exist");
        }
        CustomerDisplayResponseModel customerDisplayResponseModel =new CustomerDisplayResponseModel(custId,customer.getfName(),customer.getlName(),customer.getMobileNo(),customer.getEmailId(),customer.getAddress(),customer.getDateOfBirth());

        return customerDisplayResponseModel;
    }


}


