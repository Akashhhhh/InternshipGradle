package ecommerce.dao;

import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class is used for creating prepared statements
 *
 * @author Akash Gupta
 */
public class OrderDao {
    private static Logger logger = java.util.logging.Logger.getLogger(OrderDao.class.getName());

    /**
     * This method is used for creating query while placing order
     *
     * @param con connection
     * @return it returns the map of product
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static Map<UUID, Product> menu(Connection con) throws ApplicationRuntimeException {
        // TODO Auto-generated method stub
        boolean f = false;
        Map<UUID, Product> v = new HashMap<UUID, Product>();

        //jdbc code..
        try {


            String q = "select * from product";

            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery(q);

            while (set.next()) {
                UUID prod_id = (UUID) set.getObject(1);
                String prod_name = set.getString(2);
                Float sell_price = set.getFloat(3);
                String description = set.getString(4);
                String type = set.getString(5);
                int quantity = set.getInt(6);

                Product obj = new Product(prod_name, sell_price, description, type, quantity);
                v.put(prod_id, obj);

            }

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }
        return v;
    }

    /**
     * This method is used for fetching customer id
     *
     * @param email email of the customer
     * @param con   connection
     * @return it returns the customer id
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static UUID getCustomerId(String email, Connection con) throws ApplicationRuntimeException {

        UUID id = null;
        try {

            String q = "select cust_id from customer where email_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                id = (UUID) rs.getObject(1);

            }


        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }
        return id;
    }

    /**
     * This method is used for creating query while adding order to database
     *
     * @param obj    object of order class
     * @param custId customer id
     * @param name   name of order
     * @param con    connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static void addOrderToDb(Order obj, UUID custId, String name, Connection con) throws ApplicationRuntimeException {

        try {


            String q = "insert into orders(order_id,cust_id,order_name,order_time,total_price,quantity,product_ids) values(?,?,?,current_timestamp,?,?,?)";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setObject(1, obj.getOrderId());
            pstmt.setObject(2, custId);
            pstmt.setString(3, name);

            pstmt.setFloat(4, obj.getTotalPrice());
            pstmt.setString(5, obj.getQuantity());
            pstmt.setString(6, obj.getProductIds());

            //execute

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }
        logger.info("Order added to database");
    }

    /**
     * This method is used for creating query while deleting order from database
     *
     * @param name name of order
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static void deleteOrderToDb(String name, Connection con) throws ApplicationRuntimeException {
        boolean f = false;
        //jdbc code..
        try {


            String q = "delete from orders where order_name=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1, name);

            //execute

            pstmt.executeUpdate();
            f = true;
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(e);
        }
        logger.info("Order deleted from database");

    }
    public static boolean showOrderToDb(String name) {
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
        return true;
    }

}
