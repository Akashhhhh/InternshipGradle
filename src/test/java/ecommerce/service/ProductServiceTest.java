package ecommerce.service;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.ProductDao;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ProductCreateRequestModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;

public class ProductServiceTest {
    public static Connection con;
    public static Product product ;
    public static ProductCreateRequestModel productModel ;
    public static LruCacheService lru ;
    public static ProductDao productDao ;
    public static Validator validator ;
    public static Logger logger ;
    public static InvalidInputException invalidInputException;
    public static ApplicationRuntimeException applicationRuntimeException;
    private static ProductService productService;

    @BeforeAll
    public static void setup(){
        con = Mockito.mock(Connection.class);
        product =new Product("Kawasaki",1234,"BS","Sport",12);
        lru = Mockito.mock(LruCacheService.class);
        productDao = Mockito.mock(ProductDao.class);
        validator = Mockito.mock(Validator.class);
        logger = Mockito.mock(Logger.class);
        invalidInputException=Mockito.mock(InvalidInputException.class);
        applicationRuntimeException=Mockito.mock(ApplicationRuntimeException.class);
        productService = new ProductService(productDao,validator);
        productModel =new ProductCreateRequestModel("Kawasaki",1234,"BS","Sport",12);

    }

    @Test
    public void testAddNewProduct() throws InvalidInputException, ApplicationRuntimeException {
        productService.addNewProduct(productModel,con);

    }
    @Test
    public void testAddNewProductWithInvalidName() throws ApplicationRuntimeException, InvalidInputException {
        try {

            doThrow(new InvalidInputException(400, "name should contain only alphabets")).when(validator).validateProduct(productModel);
           productService.addNewProduct(productModel,con);

        } catch (InvalidInputException e) {
            assertEquals("name should contain only alphabets", e.getErrorDesc());
        }

    }
    @Test
    public void testUpdateProductQuantityAtleastOne() throws InvalidInputException, ApplicationRuntimeException {
        productService.updateProduct(2,"akash",con);
    }
    @Test
    public void testUpdateProductWithValidName() throws InvalidInputException, ApplicationRuntimeException {
        productService.updateProduct(2,"akash",con);
    }

    @Test
    public void testUpdateProductWithInValidName() throws InvalidInputException, ApplicationRuntimeException {
        boolean thrown = false;
        try {
            productService.updateProduct(2,"akash1",con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }


    @Test
    public void testUpdateProductQuantityLessTanOne() throws InvalidInputException, ApplicationRuntimeException {
        boolean thrown = false;
        try {
            productService.updateProduct(-1,"akash",con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }


    @Test
    public void testDeleteProductWithValidName() throws InvalidInputException, ApplicationRuntimeException {
        productService.deleteProduct("akash",con);
    }
    @Test
    public void testDeleteProductWithInValidName() throws InvalidInputException, ApplicationRuntimeException {
        boolean thrown = false;
        try {
            productService.deleteProduct("akash1",con);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }

    @Test
    public void testCheckProdNameWithValidName() throws InvalidInputException {
        productService.checkProdName("Kawasaki");
    }
    @Test
    public void testCheckProdNameWithInValidName() throws InvalidInputException {
        boolean thrown = false;
        try {
            productService.checkProdName("Kawasaki1");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}
