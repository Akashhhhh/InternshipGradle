package ecommerce.model;

import java.util.UUID;

public class CustomerCreateRequestModel {

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

    public CustomerCreateRequestModel() {
    }

    public CustomerCreateRequestModel(UUID custId, String fName, String lName, String mobileNo, String emailId, String address, String dateOfBirth) {

        this.fName = fName;
        this.lName = lName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }


    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
