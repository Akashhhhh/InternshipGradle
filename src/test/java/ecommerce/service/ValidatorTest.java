package ecommerce.service;

import ecommerce.entity.Customer;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    Validator validator;

    @BeforeEach
    public void setup() {
        validator = new Validator();

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
            validator.validateDate("1998-02-12");

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }


    @Test
    public void testValidateInValidDate() {

        boolean thrown = false;
        try {

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
            validator.validateMobile("1234567890");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertFalse(thrown);

    }

    @Test
    public void testValidateInValidMobile() {

        boolean thrown = false;
        try {

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

            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateValidProduct() throws InvalidInputException {

        Product product = new Product("Aventus", 123, "Perfume", "EDP", 12);
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

            validator.validateProduct(product);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateValidOrder() throws InvalidInputException {

        Order order = new Order(UUID.randomUUID(), 123, "12", "112");
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

            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testValidateInValidProductIds() throws InvalidInputException {
        boolean thrown = false;
        Order order = new Order(UUID.randomUUID(), 123, "12,", "asdf");
        try {

            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }


}
