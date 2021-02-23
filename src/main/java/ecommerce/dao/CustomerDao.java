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
 * @author Akash Gupta
 */
public class CustomerDao {
    private static Logger logger = java.util.logging.Logger.getLogger(CustomerDao.class.getName());

    /**
     * This method is used when we have to use insert query
     * @param obj object of customer class
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static void insertCustomerToDb(Customer obj, Connection con) throws ApplicationRuntimeException {

        try {


            String q = "insert into customer(cust_id,f_name,l_name,mobile_no,email_id,address,date_of_birth,date_created,date_last_modified) values(?,?,?,?,?,?,?,current_timestamp,current_timestamp)";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setObject(1, obj.getCustId());
            pstmt.setString(2, obj.getfName());
            pstmt.setString(3, obj.getlName());
            pstmt.setString(4, obj.getMobileNo());
            pstmt.setString(5, obj.getEmailId());
            pstmt.setString(6, obj.getAddress());
            pstmt.setString(7, obj.getDateOfBirth());

            //execute

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }
        logger.info("Customer added to Database");
    }

    /**
     * This method is used when we have to use delete query
     * @param email email id of customer
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static void deleteCustomerToDb(String email, Connection con) throws ApplicationRuntimeException {
        try {


            String q = "delete from customer where email_id=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1, email);


            //execute

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }
        logger.info("Customer deleted from  Database");


    }

    /**
     * This method is used when we have to use update query
     * @param email email of user
     * @param address addres that is updated
     * @param con connection
     */
    public static void updateCustomerToDb(String email, String address, Connection con) throws ApplicationRuntimeException {
        try {


            String q = "update customer set address=? where email_id=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1, address);
            pstmt.setString(2, email);


            //execute

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }

        logger.info("Customer updated in Database");

    }

    /**
     * This method is used when we need customer
     * @param email email id of customer
     * @param con connection
     * @return it return the customer id
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static UUID getCustIdtByEmailId(String email, Connection con) throws ApplicationRuntimeException {

        UUID id = null;
        try {


            String q = "select cust_id from customer where email_id=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1, email);


            ResultSet set = pstmt.executeQuery();



            while (set.next()) {
                id = (UUID) set.getObject(1);

            }


        } catch (SQLException e) {

            throw new ApplicationRuntimeException(e);
        }
        return id;


    }


}
