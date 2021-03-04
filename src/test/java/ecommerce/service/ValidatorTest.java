package ecommerce.service;

import ecommerce.entity.Customer;
import ecommerce.entity.Order;
import ecommerce.entity.Product;
import ecommerce.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {
     Validator validator;


    @BeforeEach
    public  void setup() {
        validator= new Validator();

    }

    @Test
    public void testValidateValidEmailId() throws InvalidInputException {

        validator.validateEmailId("akas@gmail.com");

    }

    @Test
    public void testValidateInValidEmailId(){

        try {

            validator.validateEmailId("akas.com");
        } catch (InvalidInputException e) {
            assertEquals("Check email id", e.getErrorDesc());

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
            validator.validateDate("12/10/1222");
        } catch (InvalidInputException e) {
            assertEquals("Check date", e.getErrorDesc());
        }

    }

    @Test
    public void testValidateValidMobile() throws InvalidInputException {

        validator.validateMobile("1234567890");


    }

    @Test
    public void testValidateInValidMobile() {
        try {

            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            assertEquals("Check mobile", e.getErrorDesc());

        }


    }

    @Test
    public void testValidateValidCustomer() throws InvalidInputException {

        Customer customer = new Customer("akash", "gupta", "1234567890", "akash@gmail.com", "agra", "1998-02-14");

        validator.validateCustomer(customer);


    }

    @Test
    public void testValidateInValidCustomerFName()  {
        try {
            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            assertEquals("check name", e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidCustomerLName(){

        try {
            validator.validateString("gupta1");
        } catch (InvalidInputException e) {
            assertEquals("check name", e.getErrorDesc());

        }

    }

    @Test
    public void testValidateInValidCustomerEmail()  {

        try {
            validator.validateEmailId("akash.com");
        } catch (InvalidInputException e) {
            assertEquals("Check email id", e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidCustomerDob()  {
        try {
            validator.validateDate("12/10/1222");
        } catch (InvalidInputException e) {
            assertEquals("Check date", e.getErrorDesc());

        }

    }

    @Test
    public void testValidateInValidCustomerMoblie() {
        try {
            validator.validateMobile("1234567");
        } catch (InvalidInputException e) {
            assertEquals("Check mobile", e.getErrorDesc());

        }

    }

    @Test
    public void testValidateValidProduct() throws InvalidInputException {

        Product product = new Product("Aventus", 123, "Perfume", "EDP", 12);


            validator.validateProduct(product);



    }

    @Test
    public void testValidateInValidProductName() {

        try {
            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            assertEquals("check name", e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidProductDesc()  {

        try {
            validator.validateString("akash1");
        } catch (InvalidInputException e) {
            assertEquals("check name", e.getErrorDesc());

        }

    }

    @Test
    public void testValidateInValidProductQuantity()  {

        Product product = new Product("Aventus", 123, "Perfume", "EDP", -1);
        try {
            validator.validateProduct(product);

        } catch (InvalidInputException e) {
            assertEquals("Quantity should be atleast 1", e.getErrorDesc());
        }

    }

    @Test
    public void testValidateValidOrder() throws InvalidInputException {

        Order order = new Order(UUID.randomUUID(), 123, "12", "112");

            validator.validateOrder(order);



    }

    @Test
    public void testValidateInValidOrderQuantity() {

        Order order = new Order(UUID.randomUUID(), 123, "asdf", "112,121,");
        try {
            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            assertEquals("name should contain only alphabets", e.getErrorDesc());
        }

    }

    @Test
    public void testValidateInValidProductIds()  {

        Order order = new Order(UUID.randomUUID(), 123, "12,", "asdf");
        try {
            validator.validateOrder(order);

        } catch (InvalidInputException e) {
            assertEquals("name should contain only alphabets", e.getErrorDesc());
        }

    }


}
