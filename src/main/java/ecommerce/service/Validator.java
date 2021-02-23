package ecommerce.service;

import ecommerce.entity.*;
import ecommerce.exception.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for validating the inputs
 *
 * @author Akash Gupta
 */
public class Validator {
    /**
     * This method is used for validating the email id
     *
     * @param email email id
     * @return returns true or false
     */
    public static boolean validateEmailId(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * This method is used for validating strings
     *
     * @param s any string
     * @return return true or false
     */
    public static boolean validateString(String s) {
        if ((!s.equals(null)) && s.matches("^[a-zA-Z]*$"))
            return true;
        return false;

    }

    /**
     * This methos is used for validating string
     *
     * @param d date
     * @return return true or false
     */
    public static boolean validateDate(String d) {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) d);
        return matcher.matches();
    }

    /**
     * This method is used for validating mobile number
     *
     * @param s mobile number
     * @return returns true or false
     */
    public static boolean validateMobile(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

            } else return false;
        }
        if (s.length() == 10)
            return true;
        return false;
    }

    /**
     * @param obj
     * @throws InvalidInputException
     */
    public static void validateCustomer(Customer obj) throws InvalidInputException {
        String fName = obj.getfName();
        String lName = obj.getlName();
        String emailId = obj.getEmailId();
        String dateOfBirth = obj.getDateOfBirth();
        String mobileNo = obj.getMobileNo();
        if (!validateString(fName)) {
            throw new InvalidInputException(400, "First name should contain only alphabets");
        }
        if (!validateString(lName)) {
            throw new InvalidInputException(400, "Last name should contain only alphabets");
        }
        if (!validateEmailId(emailId)) {
            throw new InvalidInputException(400, "Check email id");
        }
        if (!validateDate(dateOfBirth)) {
            throw new InvalidInputException(400, "Check date of birth 'yyyy-mm-dd'");
        }
        if (!validateMobile(mobileNo)) {
            throw new InvalidInputException(400, "Check Mobile number");
        }


    }

    /**
     * This method is used for validating the object of product class
     * @param obj object of product class
     * @throws InvalidInputException for throwing user error
     */
    public static void validateProduct(Product obj) throws InvalidInputException {
        String name = obj.getProdName();
        String desc = obj.getDescription();
        String type = obj.getType();
        int qt = obj.getQuantity();

        if (!validateString(name)) {
            throw new InvalidInputException(400, "name should contain only alphabets");
        }
        if (!validateString(desc)) {
            throw new InvalidInputException(400, "Description should contain only alphabets");
        }

        if (qt <= 0) {
            throw new InvalidInputException(400, "Quantity should be atleast 1 ");
        }

    }

    /**
     * This method is used for validating object of order class
     * @param obj object of order class
     * @throws InvalidInputException for throwing user error
     */
    public static void validateOrder(Order obj) throws InvalidInputException {
        String qt = obj.getQuantity();
        String prodIds = obj.getProductIds();
        int flag = 0;
        for (int i = 0; i < qt.length(); i++) {
            if (qt.charAt(i) >= '1' && qt.charAt(i) <= '9' && qt.charAt(i) == ',') {

            } else
                flag = 1;
            break;


        }
        if (flag == 1)
            throw new InvalidInputException(400, "name should contain only alphabets");


        for (int i = 0; i < prodIds.length(); i++) {
            if (prodIds.charAt(i) >= '1' && prodIds.charAt(i) <= '9' && prodIds.charAt(i) == ',') {

            } else
                flag = 1;
            break;
        }

        if (flag == 1) {
            throw new InvalidInputException(400, "name should contain only alphabets");
        }

    }

}
