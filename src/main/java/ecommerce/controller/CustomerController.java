package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.CustomerModel;
import ecommerce.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class manages add,update and delete operation of customer in database
 *
 * @author Akash Gupta
 */
@RequestMapping("/user")
@RestController
public class CustomerController {

    CustomerService customerService = new CustomerService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    LruCacheService lruCacheService = new LruCacheService();

    @PostMapping("/addUser")
    public ResponseEntity add(@Valid @RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer, lruCacheService, con);
            return  new ResponseEntity("User added to database", HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
          e.logError();
        }
        return  new ResponseEntity("User added not database", HttpStatus.BAD_REQUEST);


    }

    @PutMapping("/updateUser")
    public ResponseEntity update(@Valid @RequestBody CustomerModel customerModel)  {

        String email = customerModel.getEmailId();
        String address = customerModel.getAddress();
        try {
            customerService.updateCustomer(email, address, lruCacheService, con);
            return  new ResponseEntity("User updated to database", HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
           e.logError();
        }
        return  new ResponseEntity("User is not updated to database", HttpStatus.BAD_REQUEST);

    }


    @DeleteMapping("/deleteUser")
    public ResponseEntity delete(@Valid @RequestBody CustomerModel customerModel) {

        String email = customerModel.getEmailId();
        try {
            customerService.deleteCustomer(email, lruCacheService, con);
            return  new ResponseEntity("User deleted", HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }
        return  new ResponseEntity("User updated to database", HttpStatus.BAD_REQUEST);
    }


}

