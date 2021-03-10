package ecommerce.model;

import java.util.UUID;

public class OrderCreateRequestModel {

    UUID custId;

    // @InvalidNameAnnotation
    private String orderName;
    private String emailId;

    private String quantity; // list of units of every product
    private String productIds;


    public OrderCreateRequestModel() {

    }


    public OrderCreateRequestModel(UUID custId, String quantity, String productIds, String orderName, String emailId) {
        this.custId = custId;
        this.quantity = quantity;
        this.productIds = productIds;
        this.orderName = orderName;
        this.emailId = emailId;
    }


    public UUID getCustId() {
        return custId;
    }

    public String getOrderName() {
        return orderName;
    }


    public String getQuantity() {
        return quantity;
    }

    public String getProductIds() {
        return productIds;
    }
    public String getEmailId() {
        return emailId;
    }


}
