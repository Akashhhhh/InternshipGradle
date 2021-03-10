package ecommerce.model;

import java.util.UUID;

public class CustomerAddressRequestModel {


    UUID customerId;
    String address;
    public CustomerAddressRequestModel() {
    }

    public CustomerAddressRequestModel(UUID customerId, String address) {

        this.customerId = customerId;
        this.address = address;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
