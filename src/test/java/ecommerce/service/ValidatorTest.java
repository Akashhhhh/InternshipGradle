package ecommerce.service;

import ecommerce.entity.Customer;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

public class ValidatorTest {
    Validator validator;

    @BeforeEach
    public void setup() {
        validator = Mockito.mock(Validator.class);

    }

    @Test
    public void testValidateValidEmailId() throws InvalidInputException {

            validator.validateEmailId("akas@gmail.com");



    }

    @Test
    public void testValidateInValidEmailId() throws InvalidInputException {

        try {

            doThrow(new InvalidInputException(400,"check email")).when(validator).validateEmailId("akas.com");
            validator.validateEmailId("akas.com");
        } catch (InvalidInputException e) {
            assertEquals("check email",e.getErrorDesc());
        }

    }


    @Test
    public void testValidateValidString() throws InvalidInputException {

            validator.validateString("akas");


    }

    @Test
    public void testValidateValidDate() throws InvalidInputException {

            validator.validateDate("1998-02-12");


    }


    @Test
    public void testValidateInValidDate() {


        try {
            doThrow(new InvalidInputException(400,"check date")).when(validator).validateDate("12/10/1222");
            validator.validateDate("12/10/1222");
        } catch (InvalidInputException e) {
            assertEquals("check date",e.getErrorDesc());
        }

    }

    @Test
    public void testValidateValidMobile() throws InvalidInputException {

            validator.validateMobile("1234567890");


    }

    @Test
    public void testValidateInValidMobile() {


        try {
            doThrow(new InvalidInputException(400,"check mobile")).when(validator).validateMobile("1234567");

            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            assertEquals("check mobile",e.getErrorDesc());

        }


    }

    @Test
    public void testValidateValidCustomer() throws InvalidInputException {

        Customer customer = new Customer("akash", "gupta", "1234567890", "akash@gmail.com", "agra", "1998-02-14");

        validator.validateCustomer(customer);


    }

    @Test
    public void testValidateInValidCustomerFName() throws InvalidInputException {


        try {
            doThrow(new InvalidInputException(400,"check first name")).when(validator).validateString("akash1");;

            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            assertEquals("check first name",e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidCustomerLName() throws InvalidInputException {

        try {
            doThrow(new InvalidInputException(400,"check last name")).when(validator).validateString("akash1");;

            validator.validateString("gupta1");
        } catch (InvalidInputException e) {
            assertEquals("check last name",e.getErrorDesc());

        }

    }

    @Test
    public void testValidateInValidCustomerEmail() throws InvalidInputException {

        try {
            doThrow(new InvalidInputException(400,"check the email")).when(validator).validateEmailId("akash1");;

            validator.validateEmailId("akash.com");
        } catch (InvalidInputException e) {
           assertEquals("check the email",e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidCustomerDob() throws InvalidInputException {

        try {
            doThrow(new InvalidInputException(400,"check the DOB")).when(validator).validateDate("12/10/1222");
            validator.validateDate("12/10/1222");
        } catch (InvalidInputException e) {
            assertEquals("check the DOB",e.getErrorDesc());

        }

    }

    @Test
    public void testValidateInValidCustomerMoblie() throws InvalidInputException {

        try {
            doThrow(new InvalidInputException(400,"check the mobile number")).when(validator).validateMobile("1234567");
            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            assertEquals("check the mobile number",e.getErrorDesc());

        }

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

        try {
            doThrow(new InvalidInputException(400,"check the mobile number")).when(validator).validateString("akash1");
            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            assertEquals("check the mobile number",e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidProductDesc() throws InvalidInputException {

        try {
            doThrow(new InvalidInputException(400,"check the description")).when(validator).validateString("akash1");
            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            assertEquals("check the description",e.getErrorDesc());

        }

    }

    @Test
    public void testValidateInValidProductQuantity() throws InvalidInputException {

        Product product = new Product("Aventus", 123, "Perfume", "EDP", -1);
        try {
            doThrow(new InvalidInputException(400,"check the quantity")).when(validator).validateProduct(product);
            validator.validateProduct(product);

        } catch (InvalidInputException e) {
           assertEquals("check the quantity",e.getErrorDesc());
        }

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

        Order order = new Order(UUID.randomUUID(), 123, "asdf", "112,121,");
        try {
            doThrow(new InvalidInputException(400,"check the quantity")).when(validator).validateOrder(order);
            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            assertEquals("check the quantity",e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidProductIds() throws InvalidInputException {

        Order order = new Order(UUID.randomUUID(), 123, "12,", "asdf");
        try {
            doThrow(new InvalidInputException(400,"check the ids")).when(validator).validateOrder(order);

            validator.validateOrder(order);

        } catch (InvalidInputException e) {
           assertEquals("check the ids",e.getErrorDesc());
        }

    }


}
