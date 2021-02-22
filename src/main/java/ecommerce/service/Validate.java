package ecommerce.service;

import ecommerce.entity.*;
import ecommerce.exception.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
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

    public static boolean validateString(String s) {
        if ((!s.equals(null)) && s.matches("^[a-zA-Z]*$"))
            return true;
        return false;

    }

    public static boolean validateDate(String d) {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) d);
        return matcher.matches();
    }
   public static boolean validateMobile(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'){

            }
            else return false;
        }
        if(s.length()==10)
            return true;
        return false;
   }
    public static boolean validateCustomer(Customer obj) throws InvalidInputException {
        String fName = obj.getfName();
        String lName = obj.getlName();
        String emailId = obj.getEmailId();
        String dateOfBirth = obj.getDateOfBirth();
        String mobileNo= obj.getMobileNo();
        if(!validateString(fName)){
            throw new InvalidInputException(400, "First name should contain only alphabets");
        }
        if(!validateString(lName)){
            throw new InvalidInputException(400, "Last name should contain only alphabets");
        }
        if(!validateEmailId(emailId)){
            throw new InvalidInputException(400, "Check email id");
        }
        if(!validateDate(dateOfBirth)){
            throw new InvalidInputException(400, "Check date of birth 'yyyy-mm-dd'");
        }
        if(!validateMobile(mobileNo)){
            throw new InvalidInputException(400, "Check Mobile number");
        }

        return true;

    }

    public static boolean validateProduct(Product obj) throws InvalidInputException {
        String name = obj.getProdName();
        String desc = obj.getDescription();
        String type = obj.getType();
        int qt = obj.getQuantity();

        if(!validateString(name)){
            throw new InvalidInputException(400, "name should contain only alphabets");
        }
        if(!validateString(desc)){
            throw new InvalidInputException(400, "Description should contain only alphabets");
        }

        if(qt<=0){
            throw new InvalidInputException(400, "Quantity should be atleast 1 ");
        }

       return true;

    }


    public static boolean validateOrder(Order obj) throws InvalidInputException {
         String qt = obj.getQuantity();
         String prodIds = obj.getProductIds();
         int flag=0;
         for(int i=0;i<qt.length();i++){
             if(qt.charAt(i)>='1'&&qt.charAt(i)<='9'&&qt.charAt(i)==','){

             }
             else
                 flag=1;
                 break;


         }
         if(flag==1)
             throw new InvalidInputException(400, "name should contain only alphabets");


         for(int i=0;i<prodIds.length();i++){
             if(prodIds.charAt(i)>='1'&&prodIds.charAt(i)<='9'&&prodIds.charAt(i)==','){

             }
             else
                 flag=1;
             break;
         }

         if(flag==1){
             throw new InvalidInputException(400, "name should contain only alphabets");
         }
       return true;
    }

}
