package ecommerce.dao;

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
public class ProductDao {
    private static Logger logger = java.util.logging.Logger.getLogger(ProductDao.class.getName());

    /**
     * This method is used for creating insert query
     *
     * @param obj object of product class
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void insertProductToDb(Product obj, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "insert into product(prod_id,prod_name,sell_price,description,type,quantity) values(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, obj.getProdId());
            pstmt.setString(2, obj.getProdName());
            pstmt.setFloat(3, obj.getSellPrice());
            pstmt.setString(4, obj.getDescription());
            pstmt.setString(5, obj.getType());
            pstmt.setInt(6, obj.getQuantity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product is not added", e.getCause());
        }
    }

    /**
     * This method is used for creating delete query
     *
     * @param name name of product
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteProductToDb(String name, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "delete from product where prod_name=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product is not deleted", e.getCause());
        }
    }

    /**
     * This method is used for creating delete query
     *
     * @param qt   unit of product that is updated
     * @param name name of product
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void updateProductToDb(int qt, String name, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "update product set quantity=? where prod_name=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, qt);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product is not updated", e.getCause());
        }


    }
    public Map<UUID, Product> getMenu(Connection con) throws ApplicationRuntimeException {
        boolean f = false;
        Map<UUID, Product> v = new HashMap<UUID, Product>();

        try {
            String q = "select * from product";
            PreparedStatement stmt = con.prepareStatement(q);
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


}