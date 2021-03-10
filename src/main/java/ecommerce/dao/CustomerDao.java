package ecommerce.dao;

import ecommerce.entity.Customer;
import ecommerce.exception.ApplicationRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for creating prepared statements
 *
 * @author Akash Gupta
 */
public class CustomerDao {
    private static Logger logger;

    public CustomerDao() {
        logger = java.util.logging.Logger.getLogger(CustomerDao.class.getName());
    }

    /**
     * This method is used when we have to use insert query
     *
     * @param customer object of customer class
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void insertCustomerToDb(Customer customer, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "insert into customer(cust_id,f_name,l_name,mobile_no,email_id,address,date_of_birth,date_created,date_last_modified) values(?,?,?,?,?,?,?,current_timestamp,current_timestamp)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, customer.getCustId());
            pstmt.setString(2, customer.getfName());
            pstmt.setString(3, customer.getlName());
            pstmt.setString(4, customer.getMobileNo());
            pstmt.setString(5, customer.getEmailId());
            pstmt.setString(6, customer.getAddress());
            pstmt.setString(7, customer.getDateOfBirth());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customer is not inserted to database", e);
        }

    }

    /**
     * This method is used when we have to use delete query
     *
     * @param custId id of customer
     * @param con   connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteCustomerToDb(UUID custId, Connection con) throws ApplicationRuntimeException {
        try {
            String q = "delete from customer where cust_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, custId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customer is not deleted in database", e);
        }


    }

    /**
     * This method is used when we have to use update query
     *
     * @param custId   email of user
     * @param address addres that is updated
     * @param con     connection
     */
    public void updateCustomerToDb(UUID custId, String address, Connection con) throws ApplicationRuntimeException {
        try {
            String q = "update customer set address=? where cust_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, address);
            pstmt.setObject(2, custId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customer is not updated in database", e);

        }


    }

    /**
     * This method is used when we need customer
     *
     * @param email email id of customer
     * @param con   connection
     * @return it return the customer id
     * @throws ApplicationRuntimeException for throwing application error
     */
    public UUID getCustIdtByEmailId(String email, Connection con) throws ApplicationRuntimeException {
        UUID id = null;
        try {
            String q = "select cust_id from customer where email_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, email);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                id = (UUID) set.getObject(1);
            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customer Id is not fetched", e);
        }
        return id;
    }

    public Customer displayUsersToDb(UUID custId, Connection con) throws ApplicationRuntimeException {
        Customer  customer = null;
        try {
            String q = "select * from customer where cust_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, custId);
            ResultSet set = pstmt.executeQuery();

            while (set.next()) {
                String fName = set.getString(2);
                String lName = set.getString(3);
                String mobile = set.getString(4);
                String emailId = set.getString(5);
                String address = set.getString(6);
                String dob = set.getString(7);
                customer = new Customer(fName,lName,mobile,emailId,address,dob);
                return  customer;

            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customers are not displayed", e);
        }
        //return customer;
        return customer;
    }

    public UUID getCustomerIdToDb(String email, Connection con) throws ApplicationRuntimeException {
        UUID custId=null;
        try {
            String q = "select cust_id from customer where email_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, email);
            ResultSet set = pstmt.executeQuery();

            while (set.next()) {
               custId = (UUID) set.getObject(1);

            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customers Id is not fetched", e);
        }
        return custId;
    }
}
