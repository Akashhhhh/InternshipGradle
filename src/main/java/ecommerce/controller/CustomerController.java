package ecommerce.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ecommerce.cache.LruCacheService;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.CustomerService;
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
    public String add(@Valid @RequestBody Customer customer) {
        try {
            customerService.addNewCustomer(customer, lruCacheService, con);
            return "User added";
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
          e.logError();
        }
        return "Not addded";

    }

    @PutMapping("/updateUser")
    public String update(@RequestBody ObjectNode objectnode)  {

        String email = objectnode.get("emailId").asText();
        String address = objectnode.get("address").asText();
        try {
            customerService.updateCustomer(email, address, lruCacheService, con);
            return "User updated";
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
           e.logError();
        }
      return "User not updated";
    }


    @DeleteMapping("/deleteUser")
    public String delete(@RequestBody ObjectNode objectnode) {

        String email = objectnode.get("emailId").asText();
        try {
            customerService.deleteCustomer(email, lruCacheService, con);
            return "User Deleted";
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }
       return "User not deleted";
    }


}

