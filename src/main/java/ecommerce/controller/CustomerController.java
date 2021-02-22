package ecommerce.controller;

import ecommerce.cache.LruCache;
import ecommerce.entity.Customer;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.CustomerService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;


public class CustomerController {
    Scanner sc = new Scanner(System.in);

    public void add(Logger logger, LruCache lru, Connection con) throws InvalidInputException {
        logger.info("Enter First Name: ");
        String fname = sc.next();

        logger.info("Enter Last Name: ");
        String lname = sc.next();

        logger.info("Enter mobile number: ");
        String mob = sc.next();

        logger.info("Enter Email Id: ");;
        String email = sc.next();

       logger.info("Enter Address: ");
        String  address = sc.next();

       logger.info("Enter Date of Birth: ");
        String  dob = sc.next();

        Customer obj = new Customer(fname,lname,mob,email,address,dob);
        CustomerService.addNewCustomer(obj,lru,con);
    }
    public void update(Logger logger, LruCache lru, Connection con) throws InvalidInputException {
        logger.info("Enter Email Id: ");
        String email = sc.next();

        logger.info("Enter the address: ");
        String address = sc.next();

        CustomerService.updateCustomer(email,address,lru,con);

    }

    public  void delete(Logger logger, LruCache lru, Connection con) throws InvalidInputException {
        logger.info("Enter Email Id: ");
        String email = sc.next();
        CustomerService.deleteCustomer(email,lru,con);

    }
    public void customer(Logger logger, LruCache lru, Connection con) throws InvalidInputException {
        logger.info("\n" + "1.Add Customer" + "\n" +
                "2.Update an existing Customer" + "\n" +
                "3.Delete an exiting Customer" +"\n"+
                "4.Exit"
        );


        int n = sc.nextInt();
        switch (n) {
            case 1:
                add(logger,lru,con);
                break;
            case 2:
                update(logger,lru,con);
                break;
            case 3:
                delete(logger,lru,con);
                break;
            case 4:
                break;

        }
    }

}

