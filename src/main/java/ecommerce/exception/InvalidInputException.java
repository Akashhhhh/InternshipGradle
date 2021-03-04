package ecommerce.exception;

public class InvalidInputException extends Exception {
    int erroCode;
    String errorDesc;

    public InvalidInputException(int errorCode, String errorMessage) {
        this.erroCode  = errorCode;
        this.errorDesc = errorMessage;


    }


    public int getErroCode() {
        return erroCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
