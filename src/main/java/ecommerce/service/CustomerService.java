package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.CustomerDao;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;

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

    public CustomerService(CustomerDao customerDao,Validator validator,Logger logger){
      this.customerDao = customerDao;
      this.validator = validator;
      this.logger = logger;
    }
    public CustomerService(){
        customerDao = new CustomerDao();
        validator = new Validator();
        logger=java.util.logging.Logger.getLogger(CustomerService.class.getName());
    }


    /**
     * This class is used for validation when customer is added to database
     *
     * @param obj object of customer class
     * @param lru cache
     * @param con connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void addNewCustomer(Customer obj, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        validator.validateCustomer(obj);

        customerDao.insertCustomerToDb(obj, con);

        lru.put(obj.getEmailId(), obj.getCustId());
        logger.info("Customer added into cache");

    }

    /**
     * This class is used for validation when customer information is updated in database
     *
     * @param email   email id of customer
     * @param address address that is updated
     * @param lru     cache
     * @param con     connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void updateCustomer(String email, String address, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        if (!validator.validateEmailId(email)) {
            throw new InvalidInputException(400, "Check the email id");
        }
        customerDao.updateCustomerToDb(email, address, con);

        // lru
        if (!lru.find(email)) {
            UUID custId = customerDao.getCustIdtByEmailId(email, con);
            lru.put(email, custId);
            logger.info("Customer info updated in cache");
        }


    }

    /**
     * This class is used for validation when customer information is deleted from database
     *
     * @param email email id of customer
     * @param lru   cache
     * @param con   connection
     * @throws InvalidInputException       for throwing user error
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteCustomer(String email, LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        if (!validator.validateEmailId(email)) {
            throw new InvalidInputException(400, "Check the inputs");

        }
        customerDao.deleteCustomerToDb(email, con);
        if (lru.find(email)) {
            lru.delete(email);
            logger.info("Customer deleted from cache");
        }

    }

}


