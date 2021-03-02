package ecommerce.dao;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductDaoTest {
    static Connection con;
    static Product product;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static ProductDao productDao;
    public static Logger logger;

    @BeforeAll
    public static void setup() {
        con = Mockito.mock(Connection.class);
        product = new Product("Aventus",23,"perfume","EDP",34);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        productDao = new ProductDao();
        logger = Mockito.mock(Logger.class);
    }
    @Test
    public void testInsertProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.insertProductToDb(product,con);

    }
    @Test
    public void testDeleteProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.deleteProductToDb("Aventus",con);

    }
    @Test
    public void testUpdateProductToDb() throws SQLException, ApplicationRuntimeException {
        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.updateProductToDb(12,"Aventus",con);

    }

    @Test
    public void testGetMenu() throws SQLException, ApplicationRuntimeException {

        when(con.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);


        productDao.getMenu(con);

    }

}
