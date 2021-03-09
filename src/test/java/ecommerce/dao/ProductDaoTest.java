package ecommerce.dao;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.model.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductDaoTest {
     Connection con;
     Product product;
     ProductModel productModel;
     PreparedStatement preparedStatement;
     ResultSet resultSet;
     ProductDao productDao;
    public static Logger logger;

    @BeforeEach
    public  void setup() {
        con = Mockito.mock(Connection.class);
        product = new Product("Aventus",23,"perfume","EDP",34);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        productDao = new ProductDao();
        logger = Mockito.mock(Logger.class);
        productModel = new ProductModel("Aventus",23,"perfume","EDP",34);

    }
    @Test
    public void testInsertProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.insertProductToDb(productModel,con);

    }
    @Test
    public void testInsertProductToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            productDao.insertProductToDb(productModel,con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Product is not added", e.getErrorDesc());
        }

    }

    @Test
    public void testDeleteProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.deleteProductToDb("Aventus",con);

    }
    @Test
    public void  testDeleteProductToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            productDao.deleteProductToDb("Aventus",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Product is not deleted", e.getErrorDesc());
        }

    }
    @Test
    public void testUpdateProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.updateProductToDb(12,"Aventus",con);

    }

    @Test
    public void  testUpdateProductToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            productDao.updateProductToDb(12,"Aventus",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Product is not updated", e.getErrorDesc());
        }

    }

    @Test
    public void testGetProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        productDao.getProductToDb("Aventus",con);

    }
    @Test
    public void  testGetProductToDbWithException() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            productDao.getProductToDb("Aventus",con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Cant display the product", e.getErrorDesc());
        }

    }

}
