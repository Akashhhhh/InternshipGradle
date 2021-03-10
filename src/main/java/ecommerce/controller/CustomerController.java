package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.*;
import ecommerce.service.CustomerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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
    @PostMapping("/createUser")
    public ResponseEntity add(@Valid @RequestBody CustomerCreateRequestModel customerCreateRequestModel) {
        CustomerIdResponseModel customerIdResponseModel;
        Customer customer = new Customer(customerCreateRequestModel.getfName(),customerCreateRequestModel.getlName(),
                customerCreateRequestModel.getMobileNo(),customerCreateRequestModel.getEmailId(),customerCreateRequestModel.getAddress(),customerCreateRequestModel.getDateOfBirth());
        try {
            customerService.addNewCustomer(customer, lruCacheService, con);
            customerIdResponseModel= new CustomerIdResponseModel(customer.getCustId());
        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(customerIdResponseModel, HttpStatus.OK);


    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updateUser")
    public ResponseEntity update(@Valid @RequestBody CustomerAddressRequestModel customerAddressRequestModel) {

       UUID custId = customerAddressRequestModel.getCustomerId();
       String address = customerAddressRequestModel.getAddress();
        try {
            customerService.displayUsers(custId, con);
            customerService.updateCustomer(custId, address, lruCacheService, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("User updated", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/deleteUser/{custId}")
    public ResponseEntity delete(@Valid @PathVariable UUID custId){

        try {
            customerService.displayUsers(custId, con);
            customerService.deleteCustomer(custId, lruCacheService, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("User Deleted", HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @GetMapping("/readUser/{custId}")
    public ResponseEntity display(@Valid @PathVariable String custId) {
        CustomerDisplayResponseModel customerDisplayResponseModel;
        try {
            customerDisplayResponseModel=customerService.displayUsers(UUID.fromString(custId), con);
        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(customerDisplayResponseModel, HttpStatus.OK );

    }


}

