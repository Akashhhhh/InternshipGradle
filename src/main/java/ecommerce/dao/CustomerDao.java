package ecommerce.dao;

import ecommerce.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
public class CustomerDao {
    public static boolean insertCustomerToDb(Customer obj, Connection con)
    {
        boolean f = false;
        //jdbc code..
        try {


            String q = "insert into customer(cust_id,f_name,l_name,mobile_no,email_id,address,date_of_birth,date_created,date_last_modified) values(?,?,?,?,?,?,?,current_timestamp,current_timestamp)";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setObject(1,obj.getCustId());
            pstmt.setString(2, obj.getfName());
            pstmt.setString(3,obj.getlName());
            pstmt.setString(4,obj.getMobileNo());
            pstmt.setString(5,obj.getEmailId());
            pstmt.setString(6,obj.getAddress());
            pstmt.setString(7,obj.getDateOfBirth());

            //execute

            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean deleteCustomerToDb(String email, Connection con)
    {
        boolean f = false;
        //jdbc code..
        try {


            String q = "delete from customer where email_id=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1,email);



            //execute

            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;


    }
    public static boolean updateCustomerToDb(String email, String address, Connection con)
    {
        boolean f = false;
        //jdbc code..
        try {


            String q = "update customer set address=? where email_id=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1,address);
            pstmt.setString(2,email);


            //execute

            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;


    }
    public static UUID getCustIdtByEmailId(String email,Connection con)
    {
        boolean f = false;
        //jdbc code..
        UUID id= null;
        try {


            String q = "select cust_id from customer where email=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1,email);
           

            ResultSet set =pstmt.executeQuery(q);

            ResultSet rs= pstmt.executeQuery();

            while(rs.next())
            {
                id= (UUID) rs.getObject(1);

            }



            
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return id;


    }


}
