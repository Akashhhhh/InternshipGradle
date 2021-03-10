package ecommerce.dao;

import ecommerce.entity.Order;
import ecommerce.entity.Product;
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
public class OrderDao {
    private static Logger logger;
    Product product;

    public OrderDao() {

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


    /**
     * This method is used for fetching customer id
     *
     * @param email email of the customer
     * @param con   connection
     * @return it returns the customer id
     * @throws ApplicationRuntimeException for throwing application error
     */
    public UUID getCustomerId(String email, Connection con) throws ApplicationRuntimeException {
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
            throw new ApplicationRuntimeException(500, "Customer Id is not fetched", e);

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
            pstmt.setFloat(4, obj.getTotalPrice());
            pstmt.setString(5, obj.getQuantity());
            pstmt.setString(6, obj.getProductIds());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Order is not added to database", e);

        }
    }

    /**
     * This method is used for creating query while deleting order from database
     *
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteOrderToDb(UUID uuid, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "delete from orders where order_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, uuid);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Order is not deleted in database", e);

        }

    }

    public Order



    showOrderToDb(UUID orderId, Connection con) throws ApplicationRuntimeException {
        Order order = null;
        try {
            String q = "select * from orders where order_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, orderId);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
               // UUID orderID = (UUID) set.getObject(1);
                UUID custId = (UUID) set.getObject(2);
                String productName = set.getString(3);
                float totalPrice = set.getFloat(5);
                String quantityId = set.getString(6);
                String productId = set.getString(7);

                order = new Order(custId, totalPrice, quantityId, productId);
            }

        } catch (SQLException e) {

            throw new ApplicationRuntimeException(500, "Order cannot be shown", e);

        }

        return order;
    }

    public float getProductCostFromDb(UUID u, Connection con) throws ApplicationRuntimeException {
        float price = 0;
        try {
            String q = "select sell_price from product where prod_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, u);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                price = set.getFloat(1);

            }
        } catch (SQLException e) {

            throw new ApplicationRuntimeException(500, "Cant get cost", e);

        }

        return price;
    }


    public void deleteOrderByCustIdToDb(UUID custId, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "delete from orders where cust_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, custId);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            throw new ApplicationRuntimeException(500, "Cant delete the orders", e);

        }


    }

    public int getQuantityToDb(UUID prodId, Connection con) throws ApplicationRuntimeException {
        int qt=0;
        try {
            String q = "select quantity from product where prod_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, prodId);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                qt = set.getInt(1);

            }
        } catch (SQLException e) {

            throw new ApplicationRuntimeException(500, "Cant get the quantity", e);

        }
        return qt;
    }
}
