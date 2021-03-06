package ecommerce.model;

import java.util.UUID;

public class OrderModel {
    UUID orderId=UUID.randomUUID();
    UUID custId;

    // @InvalidNameAnnotation
    private String orderName;

    private float totalPrice;

    private String quantity; // list of units of every product
    private String productIds;
    private String emailId;

    public OrderModel(){

    }
    public OrderModel(UUID custId, float totalPrice, String quantity, String productIds,String emailId){
        this.custId=custId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.productIds = productIds;
        this.emailId = emailId;
    }
    public OrderModel(String orderName) {
        this.orderName = orderName;
    }
    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustId() {
        return custId;
    }

    public String getOrderName() {
        return orderName;
    }



    public float getTotalPrice() {
        return totalPrice;
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
