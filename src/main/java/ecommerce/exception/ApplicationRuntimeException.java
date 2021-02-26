package ecommerce.exception;

public class ApplicationRuntimeException extends Exception {

    int errorCode;
    String errorDesc;


    public ApplicationRuntimeException(int errorCode,String errorDesc,Throwable e) {
        super(e);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;

    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
