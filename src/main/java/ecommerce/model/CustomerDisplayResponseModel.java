package ecommerce.model;

import java.util.UUID;

public class CustomerDisplayResponseModel {

    private UUID custId;
    // @InvalidNameAnnotation
    private String fName;

    //@InvalidNameAnnotation
    private String lName;

    private String mobileNo;

    // @NotNullAnnotation
    private String emailId;

    // @NotNullAnnotation
    private String address;


    //@NotNullAnnotation
    private String dateOfBirth;




    public CustomerDisplayResponseModel(UUID custId, String fName, String lName, String mobileNo, String emailId, String address, String dateOfBirth) {
        this.custId = custId;
        this.fName = fName;
        this.lName = lName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    public CustomerDisplayResponseModel(){

    }



    public UUID getCustId() {
        return custId;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }


}
