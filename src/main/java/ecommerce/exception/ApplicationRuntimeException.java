package ecommerce.exception;

import java.sql.SQLException;

public class ApplicationRuntimeException extends Exception {

    int errorCode;
    String errorDesc;


    public ApplicationRuntimeException(SQLException e) {
        this.errorCode = e.getErrorCode();
        this.errorDesc = e.getMessage();

    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
