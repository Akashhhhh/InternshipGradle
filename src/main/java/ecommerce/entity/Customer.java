package ecommerce.entity;

import java.util.UUID;

public class Customer {
    UUID custId =UUID.randomUUID(); // customer id
    private String fName;
    private String lName;
    private String mobileNo;
    private String emailId;
    private String address;
    private String dateOfBirth;
    private String timeCreated; // when user creates its account
    private String timeLastUpdated; // when user updates it account
    public Customer(String fName, String lName, String mobileNo, String emailId, String address, String dateOfBirth){
        this.fName = fName;
        this.lName = lName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.dateOfBirth = dateOfBirth;

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
    public String getTimeCreated() {
        return timeCreated;
    }

    public String getTimeLastUpdated() {
        return timeLastUpdated;
    }
}
