package ecommerce.dao;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.model.ProductDisplayResponseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Vector;
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
     * @param product object of product class
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void insertProductToDb(Product product, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "insert into product(prod_id,prod_name,sell_price,description,type,quantity) values(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, product.getProdId());
            pstmt.setString(2, product.getProdName());
            pstmt.setInt(3, product.getSellPrice());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getType());
            pstmt.setInt(6, product.getQuantity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product is not added", e.getCause());
        }
    }

    /**
     * This method is used for creating delete query
     *
     * @param prodId name of product
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void deleteProductToDb(UUID prodId, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "delete from product where prod_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, prodId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product is not deleted", e.getCause());
        }


    }

    /**
     * This method is used for creating delete query
     *
     * @param qt   unit of product that is updated
     * @param prodId name of product id
     * @param con  connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public void updateProductToDb(int qt, UUID prodId, Connection con) throws ApplicationRuntimeException {

        try {
            String q = "update product set quantity=? where prod_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, qt);
            pstmt.setObject(2, prodId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product is not updated", e.getCause());
        }


    }

    public Product getProductToDb(UUID prodId, Connection con) throws ApplicationRuntimeException {
        Product product=null;
        try {
            String q = "select * from product where prod_id=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setObject(1, prodId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                UUID prod_id = (UUID) resultSet.getObject(1);
                String prod_name = resultSet.getString(2);
                int sell_price = resultSet.getInt(3);
                String description = resultSet.getString(4);
                String type = resultSet.getString(5);
                int quantity = resultSet.getInt(6);
                product = new Product(prod_name, sell_price, description, type, quantity);

            }

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Cant display the product", e.getCause());
        }
        return product;
    }

    public Vector<ProductDisplayResponseModel> getMenuToDb(Connection con) throws ApplicationRuntimeException {

        Vector<ProductDisplayResponseModel> v = new Vector<ProductDisplayResponseModel>();

        try {
            String q = "select * from product";
            PreparedStatement stmt = con.prepareStatement(q);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                UUID prod_id = (UUID) resultSet.getObject(1);
                String prod_name = resultSet.getString(2);
                int sell_price = resultSet.getInt(3);
                String description = resultSet.getString(4);
                String type = resultSet.getString(5);
                int quantity = resultSet.getInt(6);
                ProductDisplayResponseModel productModel = new ProductDisplayResponseModel(prod_id,prod_name, sell_price, description, type, quantity);
                v.add(productModel);
            }

        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Menu is not displayed", e);

        }
        return v;
    }
}