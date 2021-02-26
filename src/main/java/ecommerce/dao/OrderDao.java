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
    private static Logger logger ;
    Product product;
    public OrderDao(){

    }

    public OrderDao(Logger logger) {
        this.logger = java.util.logging.Logger.getLogger(CustomerDao.class.getName());

    }
    /**
     * This method is used for creating query while placing order
     *
     * @param con connection
     * @return it returns the map of product
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  Map<UUID, Product> menu(Connection con) throws ApplicationRuntimeException {
        boolean f = false;
        Map<UUID, Product> v = new HashMap<UUID, Product>();

        try {
            String q = "select * from product";
            Statement stmt = con.prepareStatement(q);
            ResultSet resultSet = stmt.executeQuery(q);
            while (resultSet.next()) {
                UUID prod_id = (UUID) resultSet.getObject(1);
                String prod_name = resultSet.getString(2);
                Float sell_price = resultSet.getFloat(3);
                String description = resultSet.getString(4);
                String type = resultSet.getString(5);
                int quantity = resultSet.getInt(6);
                Product product = new Product(prod_name, sell_price, description, type, quantity);
                v.put(prod_id, product);
            }

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500,"Menu is not displayed",e);

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
    public  UUID getCustomerId(String email, Connection con) throws ApplicationRuntimeException {
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
            throw new ApplicationRuntimeException(500,"Customer Id is not fetched",e);

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
    public void addOrderToDb(Order obj, UUID custId, String name, Connection con) throws ApplicationRuntimeException {
        try {
            String q = "insert into orders(order_id,cust_id,order_name,order_time,total_price,quantity,product_ids) values(?,?,?,current_timestamp,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, obj.getOrderId());
            pstmt.setObject(2, custId);
            pstmt.setString(3, name);
            pstmt.setFloat( 4, obj.getTotalPrice());
            pstmt.setString(5, obj.getQuantity());
            pstmt.setString(6, obj.getProductIds());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500,"Order is not added to database",e);

        }
    }

    /**
     * This method is used for creating query while deleting order from database
     *
     * @param name name of order
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  void deleteOrderToDb(String name, Connection con) throws ApplicationRuntimeException {
        boolean f = false;
        try {
            String q = "delete from orders where order_name=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            f = true;
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500,"Order is not deleted in database",e);

        }

    }
    public boolean showOrderToDb(String name,Connection con,Order order) throws ApplicationRuntimeException {
        boolean f = false;
        try {
            String q = "insert into order(order_id,cust_id,order_name,order_time,total_price,quantity,product_ids) values(?,?,?,current_timestamp,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, order.getOrderId());
            pstmt.setObject(2, order.getCustId());
            pstmt.setString(3, name);
            pstmt.setFloat(5, order.getTotalPrice());
            pstmt.setString(6, order.getQuantity());
            pstmt.setString(7, order.getProductIds());

            pstmt.executeUpdate();
            f = true;
        } catch (SQLException e) {

            throw new ApplicationRuntimeException(500,"Order cannot be shown",e);

        }
        return f;

    }

}
