package ecommerce.service;

import ecommerce.entity.Customer;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;

public class ValidatorTest {
    Validator validator;

    @BeforeEach
    public void setup() {
        validator = Mockito.mock(Validator.class);

    }

    @Test
    public void testValidateValidEmailId() throws InvalidInputException {
        boolean thrown = false;
        try {
            validator.validateEmailId("akas@gmail.com");

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);

    }

    @Test
    public void testValidateInValidEmailId() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check string")).when(validator).validateEmailId("akas.com");
            validator.validateEmailId("akas.com");

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }




    @Test
    public void testValidateValidString() {
        boolean thrown = false;
        try {
            validator.validateString("akas");

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void testValidateValidDate() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check date")).when(validator).validateDate("1998-02-12");
            validator.validateDate("1998-02-1");

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }


    @Test
    public void testValidateInValidDate() {

        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check email")).when(validator).validateDate("12/10/1222");

            validator.validateDate("12/10/1222");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateValidMobile() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check email")).when(validator).validateMobile("1234567890");

            validator.validateDate("1234567890");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);

    }

    @Test
    public void testValidateInValidMobile() {

        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check email")).when(validator).validateMobile("1234567");

            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);


    }
    @Test
    public void testValidateValidCustomer() throws InvalidInputException {

        Customer customer = new Customer("akash", "gupta", "1234567890", "akash@gmail.com", "agra", "1998-02-14");
        boolean thrown = false;
        try {

            validator.validateCustomer(customer);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);


    }

    @Test
    public void testValidateInValidCustomerFName() throws InvalidInputException {

        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check email")).when(validator).validateString("akash1");

            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidCustomerLName() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check email")).when(validator).validateString("gupta1");

            validator.validateString("gupta1");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidCustomerEmail() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check email")).when(validator).validateEmailId("akash.com");

            validator.validateEmailId("akash.com");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidCustomerDob() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check email")).when(validator).validateDate("12/10/1222");

            validator.validateDate("12/10/1222");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidCustomerMoblie() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400, "check mobile")).when(validator).validateMobile("1234567");

            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

        @Test
    public void testValidateValidProduct() throws InvalidInputException {

        Product product =new Product("Aventus",123,"Perfume","EDP",12);
            boolean thrown = false;
            try {

                validator.validateProduct(product);
            } catch (InvalidInputException e) {
                thrown = true;
            }
            assertFalse(thrown);


    }
    @Test
    public void testValidateInValidProductName() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check product name")).when(validator).validateString("akash1");

            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidProductDesc() throws InvalidInputException {
        boolean thrown = false;
        try {
            doThrow(new InvalidInputException(400,"check description")).when(validator).validateString("akash1");

            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidProductQuantity() throws InvalidInputException {
        boolean thrown = false;
        Product product = new Product("Aventus", 123, "Perfume", "EDP", -1);
        try {
            doThrow(new InvalidInputException(400,"check Quantity")).when(validator).validateProduct(product);

            validator.validateProduct(product);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void testValidateValidOrder() throws InvalidInputException {

        Order order = new Order(UUID.randomUUID(),123,"12,21,","112,121,");
        boolean thrown = false;
        try {

            validator.validateOrder(order);
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);


    }

    @Test
    public void testValidateInValidOrderQuantity() throws InvalidInputException {
        boolean thrown = false;
        Order order = new Order(UUID.randomUUID(), 123, "asdf", "112,121,");
        try {
            doThrow(new InvalidInputException(400,"check Qunatity")).when(validator).validateOrder(order);

            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }



}
