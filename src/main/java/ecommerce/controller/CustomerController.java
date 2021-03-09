package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.CustomerModel;
import ecommerce.service.CustomerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/addUser")
    public ResponseEntity add(@Valid @RequestBody CustomerModel customerModel) {
        try {
            customerService.addNewCustomer(customerModel, lruCacheService, con);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity("User Added", HttpStatus.OK);


    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updateUser")
    public ResponseEntity update(@Valid @RequestBody CustomerModel customerModel) {

        String email = customerModel.getEmailId();
        String address = customerModel.getAddress();

        CustomerModel customer = null;
        try {
            customer = customerService.displayUsers(email, con);
            if (customer != null) {
                customerService.updateCustomer(email, address, lruCacheService, con);

            } else {
                return new ResponseEntity("User not exist", HttpStatus.OK);

            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("User updated", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/deleteUser")
    public ResponseEntity delete(@Valid @RequestBody CustomerModel customerModel) {
        String email = customerModel.getEmailId();
        CustomerModel customer = null;
        try {
            customer = customerService.displayUsers(email, con);
            if (customer != null) {
                customerService.deleteCustomer(email, lruCacheService, con);
            } else {
                return new ResponseEntity("User not exist", HttpStatus.OK);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity("User Deleted", HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/displayUser")
    public ResponseEntity display(@Valid @RequestBody CustomerModel customerModel) {
        String email = customerModel.getEmailId();
        CustomerModel customer = null;
        try {
            customer = customerService.displayUsers(email, con);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        }

        if (customer != null) return new ResponseEntity(customer, HttpStatus.OK);
        return new ResponseEntity("User does not exist", HttpStatus.OK);

    }


}

