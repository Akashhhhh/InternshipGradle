package ecommerce.service;

import ecommerce.entity.*;
import ecommerce.exception.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for validating the inputs
 *
 * @author Akash Gupta
 */
public class Validator {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    javax.validation.Validator validator = factory.getValidator();

    /**
     * This method is used for validating the email id
     *
     * @param email email id
     * @return returns true or false
     */
    public void validateEmailId(String email) throws InvalidInputException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null || pat.matcher(email).matches() == false)
            throw new InvalidInputException(400, "Check email id");

        //return pat.matcher(email).matches();

    }

    /**
     * This method is used for validating strings
     *
     * @param s any string
     * @return return true or false
     */
    public void validateString(String s) throws InvalidInputException {
        if (!s.matches("^[a-zA-Z]*$"))
            throw new InvalidInputException(400, "Check ");

    }

    /**
     * This methos is used for validating string
     *
     * @param d date
     * @return return true or false
     */
    public void validateDate(String d) throws InvalidInputException {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) d);
        if (matcher.matches() == false) {
            throw new InvalidInputException(400, "Check");
        }
        // return matcher.matches();
    }

    /**
     * This method is used for validating mobile number
     *
     * @param s mobile number
     * @return returns true or false
     */
    public void validateMobile(String s) throws InvalidInputException {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

            } else throw new InvalidInputException(400, "Check ");
        }
        if (s.length() != 10)
            throw new InvalidInputException(400, "Check");
        //return true;
    }

    public void validateQt(int n) throws InvalidInputException {
        if (n <= 0) {
            throw new InvalidInputException(400, "Check the quantity");
        }
        //  return true;
    }

    /**
     * @param obj
     * @throws InvalidInputException
     */
    public void validateCustomer(Customer obj) throws InvalidInputException {

        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            throw new InvalidInputException(400, constraintViolations.iterator().next().getMessage());
        }
        String fName = obj.getfName();
        String lName = obj.getlName();
        String emailId = obj.getEmailId();
        String dateOfBirth = obj.getDateOfBirth();
        String mobileNo = obj.getMobileNo();

        validateString(fName);

        validateString(lName);

        validateEmailId(emailId);
        validateDate(dateOfBirth);

        validateMobile(mobileNo);


    }

    /**
     * This method is used for validating the object of product class
     *
     * @param obj object of product class
     * @throws InvalidInputException for throwing user error
     */
    public void validateProduct(Product obj) throws InvalidInputException {
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            throw new InvalidInputException(400, constraintViolations.iterator().next().getMessage());
        }
        String name = obj.getProdName();
        String desc = obj.getDescription();
        String type = obj.getType();
        int qt = obj.getQuantity();

        validateString(name);
        validateString(desc);

        if (qt <= 0) {
            throw new InvalidInputException(400, "Quantity should be atleast 1 ");
        }

    }

    /**
     * This method is used for validating object of order class
     *
     * @param obj object of order class
     * @throws InvalidInputException for throwing user error
     */
    public void validateOrder(Order obj) throws InvalidInputException {
        Set<ConstraintViolation<Order>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            throw new InvalidInputException(400, constraintViolations.iterator().next().getMessage());
        }
        String qt = obj.getQuantity();
        String prodIds = obj.getProductIds();
        int flag = 0;
        for (int i = 0; i < qt.length(); i++) {
            if (qt.charAt(i) >= '1' && qt.charAt(i) <= '9' || qt.charAt(i) == ',') {

            } else
                flag = 1;
            break;


        }
        if (flag == 1)
            throw new InvalidInputException(400, "name should contain only alphabets");


        for (int i = 0; i < prodIds.length(); i++) {
            if (prodIds.charAt(i) >= '1' && prodIds.charAt(i) <= '9' || prodIds.charAt(i) == ',') {

            } else
                flag = 1;
            break;
        }

        if (flag == 1) {
            throw new InvalidInputException(400, "name should contain only alphabets");
        }

    }
}
