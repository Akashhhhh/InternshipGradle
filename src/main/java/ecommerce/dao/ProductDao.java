package ecommerce.dao;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This class is used for creating prepared statements
 * @author Akash Gupta
 */
public class ProductDao {
    private static Logger logger = java.util.logging.Logger.getLogger(ProductDao.class.getName());

    /**
     * This method is used for creating insert query
     * @param obj object of product class
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public  void insertProductToDb(Product obj, Connection con) throws ApplicationRuntimeException {

        try {

            String q = "insert into product(prod_id,prod_name,sell_price,description,type,quantity) values(?,?,?,?,?,?)";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setObject(1,obj.getProdId());
            pstmt.setString(2, obj.getProdName());
            pstmt.setFloat(3,obj.getSellPrice());
            pstmt.setString(4,obj.getDescription());
            pstmt.setString(5,obj.getType());
            pstmt.setInt(6, obj.getQuantity());

            //execute

            pstmt.executeUpdate();

        }
        catch(SQLException e)
        {
            throw new ApplicationRuntimeException(e);
        }
        logger.info("Product added to Database");
    }

    /**
     * This method is used for creating delete query
     * @param name name of product
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static void deleteProductToDb(String name, Connection con) throws ApplicationRuntimeException {

        try {


            String q = "delete from product where prod_name=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1,name);



            //execute

            pstmt.executeUpdate();

        }
        catch(SQLException e)
        {
            throw new ApplicationRuntimeException(e);
        }

       logger.info("Product Deleted from database");

    }

    /**
     * This method is used for creating delete query
     * @param qt unit of product that is updated
     * @param name name of product
     * @param con connection
     * @throws ApplicationRuntimeException for throwing application error
     */
    public static void updateProductToDb(int qt, String name, Connection con) throws ApplicationRuntimeException {

        try {


            String q = "update product set quantity=? where prod_name=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setInt(1,qt);
            pstmt.setString(2,name);

            //execute

            pstmt.executeUpdate();


        }
        catch(SQLException e)
        {
            throw new ApplicationRuntimeException(e);
        }
        logger.info("Product updated in database");

    }
}