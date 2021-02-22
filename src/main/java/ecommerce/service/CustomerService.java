package ecommerce.service;

import ecommerce.cache.LruCache;
import ecommerce.dao.CustomerDao;
import ecommerce.entity.Customer;
import ecommerce.exception.InvalidInputException;

import java.sql.Connection;
import java.util.UUID;

public class CustomerService {


    public static void addNewCustomer(Customer obj, LruCache lru, Connection con) throws InvalidInputException {
        if (Validate.validateCustomer(obj)) {
            boolean answer = CustomerDao.insertCustomerToDb(obj,con);
            lru.put(obj.getEmailId(), obj.getCustId());
            if (answer) {
                System.out.println("Customer is added successfully");
            } else {
                System.out.println("Something went wrong");
            }


        } else throw new InvalidInputException(400, "Check the inputs");
    }

        public static void updateCustomer(String email, String address, LruCache lru, Connection con) throws InvalidInputException {
            if (Validate.validateEmailId(email)) {
                boolean answer= CustomerDao.updateCustomerToDb(email,address,con);

                // lru
                if(!lru.find(email)){
                    UUID custId = CustomerDao.getCustIdtByEmailId(email,con);
                    lru.put(email,custId);
                }


                if(answer)
                {
                    System.out.println("Product is updated successfully");
                }
                else
                {
                    System.out.println("Something went wrong");
                }
            } else throw new InvalidInputException(400, "Check the inputs");
        }
        public static void deleteCustomer(String email, LruCache lru, Connection con) throws InvalidInputException {
            if (Validate.validateEmailId(email)) {
                boolean answer = CustomerDao.deleteCustomerToDb(email,con);
                if(lru.find(email)){
                    lru.delete(email);

                }

                if (answer) {
                    System.out.println("Customer is deleted sucessfully");
                } else {
                    System.out.println("Something went wrong");
                }

            } else throw new InvalidInputException(400, "Check the inputs");
        }

}


