package ecommerce.model;

import java.util.UUID;

public class CustomerIdResponseModel {
    UUID customerId;

    public CustomerIdResponseModel() {
    }

    public CustomerIdResponseModel(UUID custId) {
        this.customerId = custId;
    }

    public UUID getCustId() {
        return customerId;
    }

    public void setCustId(UUID custId) {
        this.customerId = custId;
    }
}
