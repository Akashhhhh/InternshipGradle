package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.CustomerService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class manages add,update and delete operation of customer in database
 *
 * @author Akash Gupta
 */
public class CustomerController {
    Scanner sc = new Scanner(System.in);
    private static Logger logger = java.util.logging.Logger.getLogger(CustomerController.class.getName());
    CustomerService customerService = new CustomerService();



    /**
     * This method is used for adding custoner to database
     *
     * @param lru cache
     * @param con connection
     */
    public void add(LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        logger.info("Enter First Name: ");
        String fName = sc.next();

        logger.info("Enter Last Name: ");
        String lName = sc.next();

        logger.info("Enter mobile number: ");
        String mob = sc.next();

        logger.info("Enter Email Id: ");
        ;
        String email = sc.next();

        logger.info("Enter Address: ");
        String address = sc.next();

        logger.info("Enter Date of Birth: ");
        String dob = sc.next();

        Customer obj = new Customer(fName, lName, mob, email, address, dob);




            customerService.addNewCustomer(obj, lru, con);
            logger.info("Customer added successfully");


    }

    /**
     * This method is used for updating user information in database
     *
     * @param lru cache
     * @param con connection
     */
    public void update(LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        logger.info("Enter Email Id: ");
        String email = sc.next();

        logger.info("Enter the address: ");
        String address = sc.next();


        customerService.updateCustomer(email, address, lru, con);
        logger.info("Customer information updated successfully in database");


    }

    /**
     * This method is used for deleting user information form database;
     *
     * @param lru cache
     * @param con connection
     */
    public void delete(LruCacheService lru, Connection con) throws InvalidInputException, ApplicationRuntimeException {
        logger.info("Enter Email Id: ");
        String email = sc.next();

            customerService.deleteCustomer(email, lru, con);
            logger.info("Customer is deleted successfully from database");


    }

    /**
     * This method is used for providing interface
     *
     * @param lru cache
     * @param con connection
     */
    public void customer(LruCacheService lru, Connection con){
        logger.info("\n" + "1.Add Customer" + "\n" +
                "2.Update an existing Customer" + "\n" +
                "3.Delete an exiting Customer" + "\n" +
                "4.Exit"
        );


        int n = sc.nextInt();
        try {
            switch (n) {
                case 1:
                    add(lru, con);
                    break;
                case 2:
                    update(lru, con);
                    break;
                case 3:
                    delete(lru, con);
                    break;
                case 4:
                    break;

            }
        }catch (InvalidInputException e) {
            logger.info("Error Code: " + e.getErroCode() + "|" + "Error Description: " + e.getErrorDesc());

        } catch (ApplicationRuntimeException e) {
            logger.info("Error Code: " + e.getErrorCode() + "|" + "Error Description: " + e.getErrorDesc());
        }
    }

}

