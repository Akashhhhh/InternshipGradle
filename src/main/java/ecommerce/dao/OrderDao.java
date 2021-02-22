package ecommerce.dao;

import ecommerce.entity.Order;
import ecommerce.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderDao {
    public static Map<UUID,Product>menu(Connection con) {
        // TODO Auto-generated method stub
        boolean f = false;
        Map<UUID,Product>v = new HashMap<UUID,Product>();

        //jdbc code..
        try {


            String q = "select * from product";

            Statement stmt = con.createStatement();

            ResultSet set =stmt.executeQuery(q);

            while(set.next())
            {
                UUID prod_id = (UUID) set.getObject(1);
                String prod_name = set.getString(2);
                Float  sell_price = set.getFloat(3);
                String description = set.getString(4);
                String type = set.getString(5);
                int quantity = set.getInt(6);

                Product obj = new Product(prod_name,sell_price,description,type,quantity);
                v.put(prod_id,obj);

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      return v;
    }
    public static UUID getCustomerId(String email, Connection con){
        boolean f = false;
        UUID id=null;
        try {

            String q = "select cust_id from customer where email_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1,email);

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
    public static boolean addOrderToDb(Order obj, UUID custId, String name, Connection con){
        boolean f = false;
        //jdbc code..
        try {


            String q = "insert into orders(order_id,cust_id,order_name,order_time,total_price,quantity,product_ids) values(?,?,?,current_timestamp,?,?,?)";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setObject(1,obj.getOrderId());
            pstmt.setObject(2,custId);
            pstmt.setString(3,name);

            pstmt.setFloat(4,obj.getTotalPrice());
            pstmt.setString(5,obj.getQuantity());
            pstmt.setString(6,obj.getProductIds());

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
    public static boolean deleteOrderToDb(String name, Connection con) {
        boolean f = false;
        //jdbc code..
            try {


                String q = "delete from orders where order_name=?";
                //PreparedStatement
                PreparedStatement pstmt = con.prepareStatement(q);

                //set the values of parameters
                pstmt.setString(1,name);

                //execute

                pstmt.executeUpdate();
                f=true;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return f;
    }
//    public static boolean showOrderToDb(String name) {
//        boolean f = false;
//        //jdbc code..
//        try {
//
//            Connection con = EcomApp.create();
//            String q = "insert into order(order_id,cust_id,order_name,order_time,total_price,quantity,product_ids) values(?,?,?,current_timestamp,?,?,?)";
//            //PreparedStatement
//            PreparedStatement pstmt = con.prepareStatement(q);
//
//            //set the values of parameters
//            pstmt.setObject(1, obj.getOrderId());
//            pstmt.setObject(2, custId);
//            pstmt.setString(3, name);
//
//            pstmt.setFloat(5, obj.getTotalPrice());
//            pstmt.setString(6, obj.getQuantity());
//            pstmt.setString(7, obj.getProductIds());
//
//            //execute
//
//            pstmt.executeUpdate();
//            f = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return f;
//    }
//
}
