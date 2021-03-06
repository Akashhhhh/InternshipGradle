package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.ProductDao;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    public static Connection con;
    public static Product product;
    public static LruCacheService lru;
    public static ProductDao productDao;
    public static Validator validator;
    public static Logger logger;
    public static InvalidInputException invalidInputException;
    public static ApplicationRuntimeException applicationRuntimeException;
    private static ProductService productService;

    @BeforeAll
    public static void setup() {
        con = Mockito.mock(Connection.class);
        product = new Product("Kawasaki", 1234, "BS", "Sport", 12);
        lru = Mockito.mock(LruCacheService.class);
        productDao = Mockito.mock(ProductDao.class);
        validator = Mockito.mock(Validator.class);
        logger = Mockito.mock(Logger.class);
        invalidInputException = Mockito.mock(InvalidInputException.class);
        applicationRuntimeException = Mockito.mock(ApplicationRuntimeException.class);
        productService = new ProductService(productDao, validator);

    }

    @Test
    public void testAddNewProduct() throws InvalidInputException, ApplicationRuntimeException {
        doNothing().when(validator).validateProduct(product);
        productService.addNewProduct(product, con);

    }

    @Test
    public void testUpdateProductQuantityAtleastOne() throws InvalidInputException, ApplicationRuntimeException {
        doNothing().when(validator).validateString("aventus");
        doNothing().when(validator).validateQt(2);
        productService.updateProduct(2, "aventus", con);
    }

    @Test
    public void testUpdateProductWithValidName() throws InvalidInputException, ApplicationRuntimeException {
        doNothing().when(validator).validateString("aventus");
        doNothing().when(validator).validateQt(2);
        productService.updateProduct(2, "aventus", con);
    }

    @Test
    public void testUpdateProductWithInValidName() throws InvalidInputException, ApplicationRuntimeException {

        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateString("akash1");
            productService.updateProduct(2, "akash1", con);
        } catch (InvalidInputException e) {
            assertEquals("check product name",e.getErrorDesc());
        }


    }


    @Test
    public void testUpdateProductQuantityLessTanOne() throws InvalidInputException, ApplicationRuntimeException {

        try {
            doThrow(new InvalidInputException(400,"check product quantity")).when(validator).validateQt(-1);
            productService.updateProduct(-1, "akash", con);
        } catch (InvalidInputException e) {
            assertEquals("check product quantity",e.getErrorDesc());

        }


    }


    @Test
    public void testDeleteProductWithValidName() throws InvalidInputException, ApplicationRuntimeException {
        doNothing().when(validator).validateString("aventus");
        productService.deleteProduct("aventus", con);
    }

    @Test
    public void testDeleteProductWithInValidName() throws InvalidInputException, ApplicationRuntimeException {

        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateString("aventus1");
            productService.deleteProduct("aventus1", con);
        } catch (InvalidInputException e) {
            assertEquals("check product name",e.getErrorDesc());

        }


    }

    @Test
    public void testCheckProdNameWithValidName() throws InvalidInputException {
        doNothing().when(validator).validateString("aventus");
        productService.checkProdName("aventus");
    }

    @Test
    public void testCheckProdNameWithInValidName() throws InvalidInputException {

        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateString("123");
            productService.checkProdName("123");
        } catch (InvalidInputException e) {
            assertEquals("check product name",e.getErrorDesc());

        }

    }

    @Test
    public void testGetMenu() throws InvalidInputException, ApplicationRuntimeException {
        Map<UUID, Product>m = new HashMap<>();
        when(productDao.getMenu(con)).thenReturn(m);
        productService.getMenu(con);
    }


}
