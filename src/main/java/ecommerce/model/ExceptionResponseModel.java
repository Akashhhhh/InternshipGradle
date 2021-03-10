package ecommerce.model;

public class ExceptionResponseModel {
    private String erorDesc;
    private int erorCode;

    public ExceptionResponseModel() {
    }

    public ExceptionResponseModel(String erorDesc, int erorCode) {
        this.erorDesc = erorDesc;
        this.erorCode = erorCode;
    }

    public String getErorDesc() {
        return erorDesc;
    }

    public int getErorCode() {
        return erorCode;
    }
}
