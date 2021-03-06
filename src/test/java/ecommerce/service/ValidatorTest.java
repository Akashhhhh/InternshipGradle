package ecommerce.service;

import ecommerce.entity.Order;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.CustomerModel;
import ecommerce.model.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    Validator validator;

    @BeforeEach
    public void setup() {
        validator = new Validator();
    }

    @Test
    public void testValidateValidEmailId() {
        assertEquals(true, validator.validateEmailId("akash@gmail.com"));

    }

    @Test
    public void testValidateInValidEmailId() {
        assertEquals(false, validator.validateEmailId(null));

    }

    @Test
    public void testValidateValidString() {
        assertEquals(true, validator.validateString("akash"));

    }

    @Test
    public void testValidateInValidString() {
        assertEquals(false, validator.validateString("akash1"));

    }

    @Test
    public void testValidateValidDate() {
        assertEquals(true, validator.validateDate("1998-02-19"));

    }

    @Test
    public void testValidateInValidDate() {
        assertEquals(false, validator.validateDate("1993/20/12"));

    }

    @Test
    public void testValidateValidMobile() {
        assertEquals(true, validator.validateMobile("1234567890"));

    }

    @Test
    public void testValidateInValidMobile() {
        assertEquals(false, validator.validateMobile("1234567a90"));

    }
    @Test
    public void testValidateValidCustomer() throws InvalidInputException {

        CustomerModel customerModel = new CustomerModel("akash", "gupta", "1234567890", "akash@gmail.com", "agra", "1998-02-14");
        assertEquals(true, validator.validateCustomer(customerModel));


    }

    @Test
    public void testValidateInValidCustomerFName() throws InvalidInputException {
        boolean thrown = false;
        CustomerModel customerModel = new CustomerModel("akash1", "gupta", "1234567890", "akash@gmail.com", "agra", "1998-02-14");
        try {
            validator.validateCustomer(customerModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidCustomerLName() throws InvalidInputException {
        boolean thrown = false;
        CustomerModel customerModel = new CustomerModel("akash", "gupta1", "1234567890", "akash@gmail.com", "agra", "1998-02-14");
        try {
            validator.validateCustomer(customerModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateInValidCustomerEmail() throws InvalidInputException {
        boolean thrown = false;
        CustomerModel customerModel = new CustomerModel("akash", "gupta", "1234567890", "akashgmail.com", "agra", "1998-02-14");
        try {
            validator.validateCustomer(customerModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidCustomerDob() throws InvalidInputException {
        boolean thrown = false;
        CustomerModel customerModel=new CustomerModel("akash", "gupta1", "1234567890", "akash@gmail.com", "agra", "1998/02/14");
        try {
            validator.validateCustomer(customerModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateInValidCustomerMoblie() throws InvalidInputException {
        boolean thrown = false;
        CustomerModel customerModel = new CustomerModel("akash", "gupta", "12345890", "akash@gmail.com", "agra", "1998-02-14");
        try {
            validator.validateCustomer(customerModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateValidProduct() throws InvalidInputException {

        ProductModel productModel =new ProductModel("Aventus",123,"Perfume","EDP",12);
        assertEquals(true, validator.validateProduct(productModel));


    }
    @Test
    public void testValidateInValidProductName() throws InvalidInputException {
        boolean thrown = false;
        ProductModel productModel =new ProductModel("Aventus1",123,"Perfume","EDP",12);
        try {
            validator.validateProduct(productModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateInValidProductDesc() throws InvalidInputException {
        boolean thrown = false;
        ProductModel productModel =new ProductModel("Aventus",123,"Perfume1","EDP",12);
        try {
            validator.validateProduct(productModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateInValidProductQuantity() throws InvalidInputException {
        boolean thrown = false;
        ProductModel productModel =new ProductModel("Aventus",123,"Perfume","EDP",-1);
        try {
            validator.validateProduct(productModel);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateValidOrder() throws InvalidInputException {

        Order order = new Order(UUID.randomUUID(),123,"12,21,","112,121,");
        assertEquals(true, validator.validateOrder(order));


    }

    @Test
    public void testValidateInValidOrderQuantity() throws InvalidInputException {
        boolean thrown = false;
        Order order = new Order(UUID.randomUUID(),123,"asdf","112,121,");
        try {
            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateInValidOrderQua() throws InvalidInputException {
        boolean thrown = false;
        Order order = new Order(UUID.randomUUID(),123,"12,1221","asdf");
        try {
            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}
