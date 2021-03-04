package ecommerce.dao;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
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
    }
    @Test
    public void testInsertProductToDb() throws SQLException, ApplicationRuntimeException {
        try{
            when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            productDao.insertProductToDb(product,con);
        }catch (ApplicationRuntimeException e){
            assertEquals("Product is not added",e.getErrorDesc());
        }

    }
    @Test
    public void testDeleteProductToDb() throws SQLException, ApplicationRuntimeException {
       try{
           when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
           when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
           productDao.deleteProductToDb("Aventus",con);
       }catch (ApplicationRuntimeException e){
           assertEquals("Product is not deleted",e.getErrorDesc());
       }

    }
    @Test
    public void testUpdateProductToDb() throws SQLException, ApplicationRuntimeException {
       try{
           when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
           when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
           productDao.updateProductToDb(12,"Aventus",con);
       }catch (ApplicationRuntimeException e){
           assertEquals("Product is not updated",e.getErrorDesc());
       }

    }

    @Test
    public void testGetMenu() throws SQLException, ApplicationRuntimeException {

       try{
           when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
           when(preparedStatement.executeQuery()).thenReturn(resultSet);
           when(preparedStatement.executeQuery()).thenThrow(new SQLException());
           productDao.getMenu(con);
       }catch (ApplicationRuntimeException e){
           assertEquals("Menu is not displayed",e.getErrorDesc());

       }


        productDao.getMenu(con);

    }

}
